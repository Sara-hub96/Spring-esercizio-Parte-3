package co.develhope.springcalendar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private LocalDateTime eventDate;
    private Set<User> partecipants;

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
