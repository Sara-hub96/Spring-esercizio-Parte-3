package co.develhope.springcalendar.events;

import co.develhope.springcalendar.events.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<Events, Long> {
}
