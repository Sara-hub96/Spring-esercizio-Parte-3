package co.develhope.springcalendar.events;

import co.develhope.springcalendar.calendar.Calendar;
import co.develhope.springcalendar.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime eventDateStart;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime eventDateEnd;
    private Recurrence recurrence;
    @JsonIgnore
    private Integer recInterval;
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

    public Events(long id, String title, LocalDateTime eventDateStart, LocalDateTime eventDateEnd,
                  Recurrence recurrence, Integer recInterval, Set<User> partecipants) {
        this.id = id;
        this.title = title;
        this.eventDateStart = eventDateStart;
        this.eventDateEnd = eventDateEnd;
        this.recurrence = recurrence;
        this.recInterval = recInterval;
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

    public LocalDateTime getEventDateStart() {
        return eventDateStart;
    }

    public void setEventDateStart(LocalDateTime eventDateStart) {
        this.eventDateStart = eventDateStart;
    }

    public LocalDateTime getEventDateEnd() {
        return eventDateEnd;
    }

    public void setEventDateEnd(LocalDateTime eventDateEnd) {
        this.eventDateEnd = eventDateEnd;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
    }

    public int getRecInterval() {
        return recInterval;
    }

    public void setInterval(int interval) {
        this.recInterval = interval;
    }

    public Set<User> getPartecipants() {
        return partecipants;
    }

    public void setPartecipants(Set<User> partecipants) {
        this.partecipants = partecipants;
    }

    public void invitePartecipants(User user) throws Exception {
        if (partecipants.contains(user)) {
            throw new Exception("User already invited");
        }
        partecipants.add(user);
        user.getEvents().add(this);
    }
}
