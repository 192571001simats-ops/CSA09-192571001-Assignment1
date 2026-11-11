package parking.model;
public class ElectricCar extends Car implements Chargeable {
    public ElectricCar(String regNo){super(regNo);}
    @Override public double chargingFee(int kWh){Chargeable.validateEnergy(kWh);return 12.0*kWh;}
}
