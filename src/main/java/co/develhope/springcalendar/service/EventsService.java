package co.develhope.springcalendar.service;

import co.develhope.springcalendar.model.Events;
import co.develhope.springcalendar.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventsService {
    @Autowired
    EventsRepository eventsRepository;

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

    public Events updateEvents(long id, Events events) {
        return eventsRepository.findById(id)
                .map(events1 -> {
                    events1.setTitle(events.getTitle());
                    events1.setPartecipants(events.getPartecipants());
                    events1.setEventDate(events.getEventDate());

                    return eventsRepository.save(events1);
                })
                .orElse(null);
    }
}
