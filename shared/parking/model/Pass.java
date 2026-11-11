package parking.model;
import java.time.*;
public interface Pass {
    boolean valid(String role,LocalDate date);
    record Monthly(LocalDate start,LocalDate end) implements Pass {
        public Monthly{if(end.isBefore(start))throw new IllegalArgumentException("Invalid dates");}
        public boolean valid(String role,LocalDate d){return (role.equals("STAFF")||role.equals("STUDENT"))&&!d.isBefore(start)&&!d.isAfter(end);}
    }
    record Day(LocalDate date) implements Pass {
        public boolean valid(String role,LocalDate d){return role.equals("VISITOR")&&date.equals(d);}
    }
}
