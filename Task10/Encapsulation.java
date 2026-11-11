import parking.model.*;import parking.service.*;
public class Encapsulation {
 public static void main(String[] args){
  Vehicle v=new Car("TN03C3");
  try{v.calculateFee(-1);throw new AssertionError();}catch(IllegalArgumentException e){System.out.println("PASS negative duration rejected at model boundary");}
  Integer a=Integer.valueOf(1000),b=Integer.valueOf(1000);
  Bench.require(a.equals(b),"wrapper value comparison");
  System.out.println("Packages: parking.model, parking.service; demo in Task10");
 }
}
