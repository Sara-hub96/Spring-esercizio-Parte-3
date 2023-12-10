package co.develhope.springcalendar.calendar;

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
    public Calendar createNewCalendar(@RequestBody Calendar calendar) {
        calendarService.createCalendar(calendar);
        return calendar;
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
    public String deleteOneCalendar(@PathVariable long id) {
        calendarService.deleteCalendar(id);
        return String.format("Calendar with id %d has been deleted!", id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Calendar> updateOneCalendar(@PathVariable long id, @RequestBody Calendar calendar){
        Optional<Calendar> modifiedCalendar = calendarService.updateCalendar(id, calendar);
        if (modifiedCalendar.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(modifiedCalendar.get());
    }
}
