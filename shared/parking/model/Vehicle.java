package parking.model;
import java.time.*;
import java.util.*;
public abstract class Vehicle {
    private final String regNo;
    protected Vehicle(String regNo) {
        if (regNo==null || !regNo.trim().matches("[A-Za-z0-9]{3,15}")) throw new IllegalArgumentException("Invalid registration");
        this.regNo=regNo.trim().toUpperCase(Locale.ROOT);
    }
    public final String getRegNo(){return regNo;}
    protected abstract double ratePerHour();
    public double calculateFee(int minutes){
        if(minutes<0)throw new IllegalArgumentException("Negative duration");
        long hours=(minutes+59L)/60;
        long days=hours/24,remaining=hours%24;
        return (days*8+Math.min(remaining,8))*ratePerHour();
    }
    public final double calculateFee(LocalDateTime in,LocalDateTime out){
        if(out.isBefore(in))throw new IllegalArgumentException("Exit precedes entry");
        return calculateFee(Math.toIntExact((Duration.between(in,out).toSeconds()+59)/60));
    }
    @Override public final boolean equals(Object other){return other instanceof Vehicle v && regNo.equals(v.regNo);}
    @Override public final int hashCode(){return regNo.hashCode();}
    @Override public String toString(){return getClass().getSimpleName()+" "+regNo;}
}
