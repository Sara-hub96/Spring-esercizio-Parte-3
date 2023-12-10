package co.develhope.springcalendar.events;

import co.develhope.springcalendar.events.Events;
import co.develhope.springcalendar.events.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventsController {

    @Autowired
    EventsService eventsService;

    @PostMapping
    public Events createNewEvent(@RequestBody Events events) {
        eventsService.createEvents(events);
        return events;
    }

    @GetMapping
    public List<Events> viewAllEvents() {
        return eventsService.viewAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Events> viewEvent(@PathVariable long id) {
        Optional<Events> event = eventsService.viewOneEvent(id);
        if (event.isPresent()) {
            return new ResponseEntity<>(event.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public String deleteOneEvent(@PathVariable long id) {
        eventsService.deleteEvent(id);
        return String.format("Event with id %d has been deleted!", id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Events> updateOneEvent(@PathVariable long id, @RequestBody Events events) {
        Optional<Events> modifiedEvent = eventsService.updateEvents(id, events);
        if (modifiedEvent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(modifiedEvent.get());
    }
}