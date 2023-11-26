package co.develhope.springcalendar.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarService {
    @Autowired
    CalendarRepository calendarRepository;

    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    public void createCalendar(Calendar calendar) {
        calendarRepository.save(calendar);
    }

    public void deleteCalendar(long id) {
        calendarRepository.deleteById(id);
    }

    public Optional<Calendar> viewCalendar(long id) {
        return calendarRepository.findById(id);
    }

    public List<Calendar> viewAllCalendars() {
        return calendarRepository.findAll();
    }

    public Optional<Calendar> updateCalendar(long id, Calendar calendar) {
        return calendarRepository.findById(id).map(calendar1 -> {
            calendar1.setName(calendar.getName());
            calendar1.setEvents(calendar.getEvents());

            return calendarRepository.save(calendar1);
        });
    }
}
