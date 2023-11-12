package co.develhope.springcalendar.repository;

import co.develhope.springcalendar.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
