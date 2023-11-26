package co.develhope.springcalendar.events;

import co.develhope.springcalendar.calendar.Calendar;
import co.develhope.springcalendar.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "event")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private LocalDateTime eventDate;
    @ManyToMany
    @JoinTable(
            name = "events_partecipants",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> partecipants;
    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Events() {
    }

    public Events(String title, LocalDateTime eventDate, Set<User> partecipants) {
        this.title = title;
        this.eventDate = eventDate;
        this.partecipants = partecipants;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public Set<User> getPartecipants() {
        return partecipants;
    }

    public void setPartecipants(Set<User> partecipants) {
        this.partecipants = partecipants;
    }
}
