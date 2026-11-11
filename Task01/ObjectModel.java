import parking.model.*;
import parking.service.*;
import java.time.*;
public class ObjectModel {
 public static void main(String[] args){
  ParkingService facility=new ParkingService(new int[]{3,2});
  int[] slot=facility.enter(new Car("TN01AB1111"),LocalDateTime.of(2026,11,11,9,0),"VISITOR",null,false);
  Bench.require(slot[0]==0&&slot[1]==1,"first suitable regular slot");
  try{facility.enter(new Car("TN01AB1111"),LocalDateTime.now(),"VISITOR",null,false);throw new AssertionError();}catch(IllegalArgumentException e){System.out.println("PASS duplicate registration rejected");}
  System.out.println("Objects: Vehicle, ParkingTicket, ParkingService, Pass, PricingRule");
 }
}
