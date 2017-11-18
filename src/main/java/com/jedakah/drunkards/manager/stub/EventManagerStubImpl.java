package com.jedakah.drunkards.manager.stub;

import com.jedakah.drunkards.entity.Event;
import com.jedakah.drunkards.manager.EventManager;
import com.jedakah.drunkards.to.event.CreateEventRequest;
import com.jedakah.drunkards.to.event.GetEventResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Profile(value = "stub")
@Component
public class EventManagerStubImpl implements EventManager {

    @Override
    public GetEventResponse getEvent(Long eventId) {

        GetEventResponse getEventResponse = new GetEventResponse();
        getEventResponse.setEventStatus(Event.EventStatus.ACTIVE);
        getEventResponse.setHostUserName("Host name");
        getEventResponse.setHostUserName("Host name");
        getEventResponse.setDescription("Description");
        getEventResponse.setLatitude("66.66666");
        getEventResponse.setLongitude("33.33333");

        return getEventResponse;
    }

    @Override
    public List<GetEventResponse> getAllEvents() {

        GetEventResponse getEventResponse = new GetEventResponse();
        getEventResponse.setEventStatus(Event.EventStatus.ACTIVE);
        getEventResponse.setHostUserName("Host name");
        getEventResponse.setHostUserName("Host name");
        getEventResponse.setDescription("Description");
        getEventResponse.setLatitude("66.66666");
        getEventResponse.setLongitude("33.33333");

        return Collections.singletonList(getEventResponse);
    }

    @Override
    public GetEventResponse createEvent(CreateEventRequest createEventRequest) {

        GetEventResponse getEventResponse = new GetEventResponse();
        getEventResponse.setEventStatus(Event.EventStatus.ACTIVE);
        getEventResponse.setHostUserName("Host name");
        getEventResponse.setDescription(createEventRequest.getDescription());
        getEventResponse.setLatitude(createEventRequest.getLatitude());
        getEventResponse.setLongitude(createEventRequest.getLongitude());

        return getEventResponse;
    }

    @Override
    public Event leaveEvent(Event event) {
        return null;
    }

    @Override
    public Event stopEvent(Event event) {
        return null;
    }

    @Override
    public Event joinEvent(Event event) {
        return null;
    }
}
