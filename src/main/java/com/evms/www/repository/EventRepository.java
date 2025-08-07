package com.evms.www.repository;

import com.evms.www.enums.Status;
import com.evms.www.model.Event;
import com.evms.www.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> getEventByCreator (User creator);
    List<Event> getEventsByEvent_status (Status status);
}
