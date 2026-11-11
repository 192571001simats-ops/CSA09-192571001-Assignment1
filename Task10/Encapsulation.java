import parking.model.*;
import parking.service.*;

public class Encapsulation {
  static final class EnergyReading {
    private int kWh;
    public int getKWh(){return kWh;}
    public void setKWh(int value){if(value<0)throw new IllegalArgumentException("Negative energy reading");kWh=value;}
  }
  public static void main(String[] args) {
    Vehicle v = new Car("TN03C3");
    EnergyReading meter=new EnergyReading();meter.setKWh(10);Bench.require(meter.getKWh()==10,"validated mutable energy reading");
    try{meter.setKWh(-1);throw new AssertionError();}catch(IllegalArgumentException e){System.out.println("PASS setter rejects negative energy");}
    try {
      v.calculateFee(-1);
      throw new AssertionError();
    } catch (IllegalArgumentException e) {
      System.out.println("PASS negative duration rejected at model boundary");
    }
    Integer a = Integer.valueOf(1000), b = Integer.valueOf(1000);
    Bench.require(a.equals(b), "wrapper value comparison");
    System.out.println("Packages: parking.model, parking.service; demo in Task10");
  }
}
