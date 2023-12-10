    package co.develhope.springcalendar.user;

    import co.develhope.springcalendar.calendar.Calendar;
    import co.develhope.springcalendar.events.Events;
    import jakarta.persistence.*;

    import java.util.Set;

    @Entity
    @Table(name = "user")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String fullName;
        private String email;
        @ManyToMany(mappedBy = "partecipants")
        private Set<Events> events;
        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "calendar_id")
        private Calendar calendar;

        public User() {
        }

        public User(long id, String fullName, String email) {
            this.id = id;
            this.fullName = fullName;
            this.email = email;

        }

        public Set<Events> getEvents() {
            return events;
        }

        public void setEvents(Set<Events> events) {
            this.events = events;
        }

        public long getId() {
        return id;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
