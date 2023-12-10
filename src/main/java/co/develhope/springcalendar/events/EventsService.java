package co.develhope.springcalendar.events;

import co.develhope.springcalendar.user.User;
import co.develhope.springcalendar.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventsService {
    @Autowired
    EventsRepository eventsRepository;
    @Autowired
    UserRepository userRepository;

    public EventsService(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public void createEvents(Events events) {
        eventsRepository.save(events);
    }

    public void deleteEvent(long id) {
        eventsRepository.deleteById(id);
    }

    public Optional<Events> viewOneEvent(long id) {
        return eventsRepository.findById(id);
    }

    public List<Events> viewAllEvents() {
        return eventsRepository.findAll();
    }

    public Optional<Events> updateEvents(long id, Events events) {
        return eventsRepository.findById(id)
                .map(events1 -> {
                    events1.setTitle(events.getTitle());
                    events1.setPartecipants(events.getPartecipants());
                    events1.setEventDateStart(events.getEventDateStart());
                    events1.setEventDateEnd(events.getEventDateEnd());

                    return eventsRepository.save(events1);
                });
    }

    public void inviteUserToEvent(long eventId, long userId) {
        Events event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        try {
            event.invitePartecipants(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        eventsRepository.save(event);
    }
}
