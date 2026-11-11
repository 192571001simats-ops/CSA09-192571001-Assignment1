import java.time.*;
import parking.model.*;
import parking.service.*;

public class Adaptation {
  static class EBike extends Vehicle {
    EBike(String reg) {
      super(reg);
    }

    @Override
    protected double ratePerHour() {
      return 15;
    }

    @Override
    public double calculateFee(int minutes) {
      if (minutes < 0) throw new IllegalArgumentException();
      return minutes == 0 ? 0 : 15;
    }
  }

  static class DeliveryVan extends Car {
    DeliveryVan(String reg) {
      super(reg);
    }
  }

  static double revisedQuote(ParkingTicket ticket, LocalDateTime out) {
    if (out.isBefore(ticket.entry())) throw new IllegalArgumentException();
    if (ticket.freeParking(out)) return 0;
    if (!(ticket.vehicle() instanceof DeliveryVan))
      return ticket.vehicle().calculateFee(ticket.entry(), out);
    long seconds = Duration.between(ticket.entry(), out).toSeconds(),
        hours = (seconds + 3599) / 3600;
    double total = 0;
    for (long h = 0; h < hours; h++) {
      int hour = ticket.entry().plusHours(h).getHour();
      total += hour >= 9 && hour < 17 ? 60 : 30;
    }
    return total;
  }

  public static void main(String[] args) {
    LocalDateTime at = LocalDateTime.of(2026, 11, 11, 9, 0);
    var p = new ParkingService(new int[] {3, 4, 2, 5, 3}, Adaptation::revisedQuote);
    var e = p.enter(new EBike("TN20E1"), at, "VISITOR", null, false);
    Bench.require(
        p.exit(e[0], e[1], at.plusMinutes(130), 0).doubleValue() == 15, "e-bike flat fee");
    var v = p.enter(new DeliveryVan("TN20V1"), at, "VISITOR", null, false);
    Bench.require(
        p.exit(v[0], v[1], at.plusHours(2), 0).doubleValue() == 120, "daytime van double rate");
    var evening = at.withHour(17);
    v = p.enter(new DeliveryVan("TN20V2"), evening, "VISITOR", null, false);
    Bench.require(
        p.exit(v[0], v[1], evening.plusHours(2), 0).doubleValue() == 60,
        "17:00 is outside peak band");
    v = p.enter(new DeliveryVan("TN20V3"), at.withHour(8), "VISITOR", null, false);
    Bench.require(
        p.exit(v[0], v[1], at.plusHours(1), 0).doubleValue() == 90, "van crossing 09:00 boundary");
    var day = new Pass.Day(at.toLocalDate());
    var d = p.enter(new Car("TN20D1"), at, "VISITOR", day, false);
    Bench.require(
        p.exit(d[0], d[1], at.plusHours(2), 0).doubleValue() == 0, "visitor one-day pass");
    Bench.require(
        !day.valid("VISITOR", at.toLocalDate().plusDays(1)), "day pass expires at date boundary");
    Bench.require(
        !new Pass.Monthly(at.toLocalDate(), at.toLocalDate().plusMonths(1))
            .valid("VISITOR", at.toLocalDate()),
        "monthly pass restricted to student or staff");
    System.out.println(
        "Existing model/service source files modified: 0; additions: EBike, DeliveryVan, revised"
            + " pricing composition");
  }
}
