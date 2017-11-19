package com.jedakah.drunkards.manager;

import com.jedakah.drunkards.to.event.CreateEventRequest;
import com.jedakah.drunkards.to.event.EventFilter;
import com.jedakah.drunkards.to.event.EventHolder;
import com.jedakah.drunkards.to.event.GetEventResponse;
import java.util.List;

public interface EventManager {

    GetEventResponse getEvent(Long eventId);
    EventHolder getAllEvents();
    List<GetEventResponse> getEventsByFilter(EventFilter eventFilter);
    GetEventResponse createEvent(CreateEventRequest createEventRequest);
    GetEventResponse leaveEvent(Long eventId);
    GetEventResponse stopEvent(Long eventId);
    GetEventResponse joinEvent(Long eventId);

}
