import java.time.*;
import parking.model.*;

public class Constructors {
  public static void main(String[] args) {
    var ticket = new ParkingTicket(new Car("TN02B2"), LocalDateTime.now());
    var duplicate = new ParkingTicket(ticket);
    Bench.require(
        ticket != duplicate && ticket.id() == duplicate.id(),
        "duplicate document preserves issued ticket ID");
    Bench.require(
        ticket.vehicle() == duplicate.vehicle(), "immutable vehicle reference safely shared");
    try {
      new Car(" ");
      throw new AssertionError();
    } catch (IllegalArgumentException e) {
      System.out.println("PASS invalid constructor argument");
    }
  }
}
