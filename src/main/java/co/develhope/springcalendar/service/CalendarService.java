package co.develhope.springcalendar.service;

import co.develhope.springcalendar.model.Calendar;
import co.develhope.springcalendar.repository.CalendarRepository;
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

    public Calendar updateCalendar(long id, Calendar calendar) {
        return calendarRepository.findById(id).map(calendar1 -> {
            calendar1.setName(calendar.getName());
            calendar1.setEvents(calendar.getEvents());
          //  calendar1.setUser(calendar.getUser());

            return calendarRepository.save(calendar1);
        })
        .orElse(null);
    }
}
