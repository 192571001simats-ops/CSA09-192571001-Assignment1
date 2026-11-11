package parking.model;
public interface Chargeable {
    double chargingFee(int kWh);
    default String chargingUnit(){return "kWh";}
    static void validateEnergy(int kWh){if(kWh<0)throw new IllegalArgumentException("Negative energy");}
}
