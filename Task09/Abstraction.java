import parking.model.*;
public class Abstraction {
 public static void main(String[] args){
  Vehicle vehicle=new ElectricCar("TN03C3");Chargeable charging=(Chargeable)vehicle;
  Bench.require(vehicle.calculateFee(61)==60,"abstract vehicle fee contract");
  Bench.require(charging.chargingFee(10)==120,"independent charging capability");
  System.out.println("Charging unit: "+charging.chargingUnit());
 }
}
