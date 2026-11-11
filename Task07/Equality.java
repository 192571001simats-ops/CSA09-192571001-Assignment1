import parking.model.*;import java.util.*;
public class Equality {
 public static void main(String[] args){
  Vehicle car=new Car("tn02b2"),ev=new ElectricCar("TN02B2");
  Bench.require(car.equals(ev)&&ev.equals(car)&&car.hashCode()==ev.hashCode(),"registration is normalized cross-type identity");
  Set<Vehicle> parked=new HashSet<>(List.of(car,ev));Bench.require(parked.size()==1,"same registration deduplicated");
  System.out.println(car);System.out.println("Rates: "+car.calculateFee(130)+" and "+new TwoWheeler("TN01A1").calculateFee(130));
 }
}
