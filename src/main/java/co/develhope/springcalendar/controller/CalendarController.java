package co.develhope.springcalendar.controller;

import co.develhope.springcalendar.model.Calendar;
import co.develhope.springcalendar.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
    @Autowired
    CalendarService calendarService;

    @PostMapping
    public String createNewCalendar(@RequestBody Calendar calendar) {
        calendarService.createCalendar(calendar);
        return "A new calendar has been created!";
    }

    @GetMapping
    public List<Calendar> viewAllCalendars() {
        return calendarService.viewAllCalendars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calendar> viewOneCalendar(@PathVariable long id) {
        Optional<Calendar> calendar = calendarService.viewCalendar(id);
        if (calendar.isPresent()) {
            return new ResponseEntity<>(calendar.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteOneCalendar(@PathVariable long id) {
        calendarService.deleteCalendar(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Calendar> updateOneCalendar(@PathVariable long id, @RequestBody Calendar calendar){
        Calendar modifiedCalendar = calendarService.updateCalendar(id, calendar);
        if (modifiedCalendar == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(modifiedCalendar);
    }
}
