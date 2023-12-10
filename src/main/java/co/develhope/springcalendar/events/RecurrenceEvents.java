package co.develhope.springcalendar.events;

import java.time.LocalDateTime;

public class RecurrenceEvents {
    public void setRecurrenceTimeAndDuration(Events event, LocalDateTime startTime, Integer interval){

        event.setEventDateStart(startTime);
        event.setInterval(interval);

        switch (event.getRecurrence()) {
            case DAILY -> event.setEventDateEnd(startTime.plusDays(interval));
            case WEEKLY -> event.setEventDateEnd(startTime.plusWeeks(interval));
            case MONTHLY -> event.setEventDateEnd(startTime.plusMonths(interval));
            case YEARLY -> event.setEventDateEnd(startTime.minusYears(interval));
        }
    }
}
