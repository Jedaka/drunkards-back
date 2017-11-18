package com.jedakah.drunkards.converters;

import com.jedakah.drunkards.entity.Event;
import com.jedakah.drunkards.entity.Event.Location;
import com.jedakah.drunkards.to.event.CreateEventRequest;
import com.jedakah.drunkards.to.event.GetEventResponse;
import org.springframework.stereotype.Component;

@Component
public class EventConverter {

  public Event convertRequest(CreateEventRequest createEventRequest) {

    Event event = new Event();
    Location location = new Location();
    location.setLatitude(createEventRequest.getLatitude());
    location.setLongitude(createEventRequest.getLongitude());
    event.setLocation(location);
    event.setDescription(createEventRequest.getDescription());

    return event;
  }

  public GetEventResponse convertEvent(Event event) {

    GetEventResponse getEventResponse = new GetEventResponse();
    getEventResponse.setId(event.getId());
    getEventResponse.setEventStatus(event.getEventStatus());
    getEventResponse.setDescription(event.getDescription());
    getEventResponse.setLatitude(event.getLocation().getLatitude());
    getEventResponse.setLongitude(event.getLocation().getLongitude());

    return getEventResponse;
  }

}
