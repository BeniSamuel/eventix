package com.evms.www.service;

import com.evms.www.dto.EventDto;
import com.evms.www.enums.Status;
import com.evms.www.model.Event;
import com.evms.www.model.User;
import com.evms.www.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final UserService userService;

    public List<Event> getAllEvents () {
        return this.eventRepository.findAll();
    }

    public Event getEventById (Long id) {
        return this.eventRepository.findById(id).orElse(null);
    }

    public List<Event> getEventsByCreator (Long user_id) {
        User creator = userService.getUserById(user_id);
        if (creator != null) {
            return this.eventRepository.getEventByCreator (creator);
        }
        return null;
    }

    public Event createEvent (EventDto eventDto) {
        User creator = userService.getUserById(eventDto.getCreator_id());
        if (creator == null) {
            return null;
        }

        Event newEvent = new Event(eventDto.getName(), eventDto.getDescription(), creator, eventDto.getLocation(), eventDto.getOverview(), eventDto.getStart_time(), eventDto.getEnd_time(), Status.OPENED);
        return eventRepository.save(newEvent);
    }

    public Event updateEvent (Long id, EventDto eventDto) {
        Event event = getEventById(id);
        if (event == null) {
            return null;
        }

        User creator = userService.getUserById(eventDto.getCreator_id());
        if (creator == null) {
            return null;
        }

        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        event.setOverview(eventDto.getOverview());
        event.setLocation(eventDto.getLocation());
        event.setRating(eventDto.getRating());
        event.setStart_time(eventDto.getStart_time());
        event.setEnd_time(eventDto.getEnd_time());

        return eventRepository.save(event);
    }

    public Boolean deleteEvent (Long id) {
        Event event = getEventById(id);
        if (event != null) {
            eventRepository.delete(event);
            return true;
        }
        return false;
    }

    @Scheduled(fixedRate = 3600000)
    public void updateEventStatusWhenExpired () {
        for (Event event: eventRepository.getEventsByStatus(Status.OPENED)) {
            if (event.getEnd_time().isBefore(LocalDateTime.now())) {
                event.setStatus(Status.CLOSED);
                eventRepository.save(event);
            }
        }
    }
}
