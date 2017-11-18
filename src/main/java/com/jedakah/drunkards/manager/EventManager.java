package com.jedakah.drunkards.manager;

import com.jedakah.drunkards.entity.Event;
import com.jedakah.drunkards.to.event.CreateEventRequest;
import com.jedakah.drunkards.to.event.GetEventResponse;

import java.util.List;

public interface EventManager {

    GetEventResponse getEvent(Long eventId);
    List<GetEventResponse> getAllEvents();
    GetEventResponse createEvent(CreateEventRequest createEventRequest);
    Event leaveEvent(Event event);
    Event stopEvent(Event event);
    Event joinEvent(Event event);


}
