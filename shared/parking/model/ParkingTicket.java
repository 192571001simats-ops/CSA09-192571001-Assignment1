package parking.model;

import java.time.*;
import java.util.concurrent.atomic.AtomicLong;

public final class ParkingTicket {
  private static final AtomicLong sequence = new AtomicLong();
  private static final String issuer = java.util.UUID.randomUUID().toString();
  private final String ticketNumber;
  private final long id;
  private final Vehicle vehicle;
  private final LocalDateTime entry;
  private final String role;
  private final Pass pass;

  public ParkingTicket(Vehicle vehicle, LocalDateTime entry) {
    this(vehicle, entry, "VISITOR", null);
  }

  public ParkingTicket(Vehicle vehicle, LocalDateTime entry, String role, Pass pass) {
    this.id = sequence.incrementAndGet();
    this.ticketNumber = issuer + "-" + id;
    this.vehicle = java.util.Objects.requireNonNull(vehicle);
    this.entry = java.util.Objects.requireNonNull(entry);
    this.role = java.util.Objects.requireNonNull(role);
    this.pass = pass;
    if (!java.util.Set.of("VISITOR", "STAFF", "STUDENT").contains(role))
      throw new IllegalArgumentException("Unknown role");
  }

  public ParkingTicket(ParkingTicket original) {
    this.id = original.id;
    this.ticketNumber = original.ticketNumber;
    this.vehicle = original.vehicle;
    this.entry = original.entry;
    this.role = original.role;
    this.pass = original.pass;
  }

  public long id() {
    return id;
  }

  public String ticketNumber() {
    return ticketNumber;
  }

  public Vehicle vehicle() {
    return vehicle;
  }

  public LocalDateTime entry() {
    return entry;
  }

  public boolean freeParking(LocalDateTime exit) {
    if (pass == null) return false;
    for (LocalDate d = entry.toLocalDate(); !d.isAfter(exit.toLocalDate()); d = d.plusDays(1))
      if (!pass.valid(role, d)) return false;
    return true;
  }
}
