import java.time.*;
import java.util.*;
import parking.model.*;

public class Lifecycle {
  public static void main(String[] args) {
    var north = new ParkingTicket(new Car("TN01N1"), LocalDateTime.now());
    var south = new ParkingTicket(new Car("TN01S1"), LocalDateTime.now());
    Bench.require(north.id() != south.id(), "process-wide unique ticket sequence");
    List<ParkingTicket> active = new ArrayList<>();
    active.add(north);
    active.remove(north);
    north = null;
    System.out.println(
        "North ticket reference removed; collection is eligible only after all strong references"
            + " disappear");
    System.out.println("No claim that garbage collection has already run");
  }
}
