package com.evms.www.controller;

import com.evms.www.dto.EventDto;
import com.evms.www.model.Event;
import com.evms.www.service.EventService;
import com.evms.www.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evms/event")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents () {
        return ApiResponse.ok("Successfully obtained all events!!! 🎉🎉🎉", eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Event>> getEventById (@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        if (event != null) {
            return ApiResponse.ok("Successfully obtained an event!!! 🎉🎉🎉", event);
        }
        return ApiResponse.notFound("Failed to obtain an event not found!!! 💔😔😔", null);
    }

    @GetMapping("/creator/{creator_id}")
    public ResponseEntity<ApiResponse<List<Event>>> getAllEventsByCreator (@PathVariable Long creator_id) {
        List<Event> events = eventService.getEventsByCreator(creator_id);
        if (!events.isEmpty()) {
            return ApiResponse.ok("Successfully obtained all events by a user!!! 🎉🎉🎉", events);
        }
        return ApiResponse.notFound("Failed to obtain events by a user!!! 💔😔😔", null);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Event>> createEvent (@RequestBody EventDto eventDto) {
        Event event = eventService.createEvent(eventDto);
        if (event != null) {
            return ApiResponse.created("Successfully created an event!!! 🎉🎉🎉", event);
        }
        return ApiResponse.badRequest("Failed to create an event bad request!!! 💔😔😔", null);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Event>> updateEvent (@PathVariable Long id, @RequestBody EventDto eventDto) {
        Event event = this.eventService.updateEvent(id, eventDto);
        if (event != null) {
            return ApiResponse.ok("Successfully updated an event!!! 🎉🎉🎉", event);
        }
        return ApiResponse.notFound("Failed to update an event not found!!! 💔😔😔", null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteEvent (@PathVariable Long id) {
        return this.eventService.deleteEvent(id) ?
                ApiResponse.ok("Successfully deleted an event!!! 🎉🎉🎉", true) :
                ApiResponse.notFound("Failed to delete an event not found!!! 💔😔😔", false);
    }
}
