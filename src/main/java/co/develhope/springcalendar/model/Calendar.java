package co.develhope.springcalendar.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "calendar")
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "calendar")
    private Set<Events> events;

    public Calendar() {
    }

    public Calendar(long id, String name, Set<Events> events) {
        this.id = id;
        this.name = name;
        this.events = events;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Events> getEvents() {
        return events;
    }

    public void setEvents(Set<Events> events) {
        this.events = events;
    }

  // public User getUser() {
  //     return user;
  // }

  // public void setUser(User user) {
  //     this.user = user;
  // }
}