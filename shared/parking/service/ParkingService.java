package parking.service;
import parking.model.*;
import java.time.*;
import java.math.*;
import java.util.*;
public final class ParkingService {
    public interface PricingRule {double quote(ParkingTicket ticket,LocalDateTime exit);}
    private final ParkingTicket[][] slots;
    private final PricingRule pricing;
    private BigDecimal collection=BigDecimal.ZERO;
    public ParkingService(int[] floors){this(floors,(t,out)->t.freeParking(out)?0:t.vehicle().calculateFee(t.entry(),out));}
    public ParkingService(int[] floors,PricingRule pricing){
        if(floors.length==0)throw new IllegalArgumentException("No floors");
        slots=new ParkingTicket[floors.length][];
        for(int f=0;f<floors.length;f++){if(floors[f]<1)throw new IllegalArgumentException("Empty floor");slots[f]=new ParkingTicket[floors[f]];}
        this.pricing=Objects.requireNonNull(pricing);
    }
    public synchronized int[] enter(Vehicle v,LocalDateTime time,String role,Pass pass,boolean charging){
        Objects.requireNonNull(v);Objects.requireNonNull(time);
        if(charging&&!(v instanceof Chargeable))throw new IllegalArgumentException("Charging requires EV");
        for(ParkingTicket[] row:slots)for(ParkingTicket t:row)if(t!=null&&t.vehicle().equals(v))throw new IllegalArgumentException("Duplicate registration");
        for(int f=0;f<slots.length;f++)for(int s=0;s<slots[f].length;s++){
            boolean chargingSlot=s==0;
            if(chargingSlot!=charging||slots[f][s]!=null)continue;
            slots[f][s]=new ParkingTicket(v,time,role,pass);return new int[]{f,s};
        }
        return null;
    }
    public synchronized BigDecimal exit(int floor,int slot,LocalDateTime time,int kWh){
        if(floor<0||floor>=slots.length||slot<0||slot>=slots[floor].length||slots[floor][slot]==null)throw new IllegalArgumentException("Invalid or empty slot");
        ParkingTicket t=slots[floor][slot];
        if(time.isBefore(t.entry())||kWh<0)throw new IllegalArgumentException("Invalid exit");
        if(kWh>0&&(slot!=0||!(t.vehicle() instanceof Chargeable)))throw new IllegalArgumentException("No charging session");
        double amount=pricing.quote(t,time);
        if(t.vehicle() instanceof Chargeable c)amount+=c.chargingFee(kWh);
        if(!Double.isFinite(amount)||amount<0)throw new IllegalStateException("Invalid quote");
        BigDecimal paid=BigDecimal.valueOf(amount).setScale(2,RoundingMode.HALF_UP);
        slots[floor][slot]=null;collection=collection.add(paid);return paid;
    }
    public synchronized BigDecimal collection(){return collection;}
    public synchronized int occupancy(){int n=0;for(var row:slots)for(var t:row)if(t!=null)n++;return n;}
    public static Vehicle create(String type,String regNo){return switch(type){case "CAR"->new Car(regNo);case "TWO_WHEELER"->new TwoWheeler(regNo);case "EV"->new ElectricCar(regNo);default->throw new IllegalArgumentException("Unknown type");};}
}
