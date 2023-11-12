package co.develhope.springcalendar.controller;

import co.develhope.springcalendar.model.Events;
import co.develhope.springcalendar.service.EventsService;
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
    public String createNewEvent(@RequestBody Events events) {
        eventsService.createEvents(events);
        return "A new event has been created!";
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
    public void deleteOneEvent(@PathVariable long id) {
        eventsService.deleteEvent(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Events> updateOneEvent(@PathVariable long id, @RequestBody Events events) {
        Events modifiedEvent = eventsService.updateEvents(id, events);
        if (modifiedEvent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(modifiedEvent);
    }
}