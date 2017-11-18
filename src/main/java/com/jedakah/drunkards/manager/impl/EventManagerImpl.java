package com.jedakah.drunkards.manager.impl;

import com.jedakah.drunkards.converters.EventConverter;
import com.jedakah.drunkards.entity.Event;
import com.jedakah.drunkards.entity.Event.EventStatus;
import com.jedakah.drunkards.manager.EventManager;
import com.jedakah.drunkards.repository.EventRepository;
import com.jedakah.drunkards.to.event.CreateEventRequest;
import com.jedakah.drunkards.to.event.GetEventResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("prod")
@Component
@RequiredArgsConstructor
public class EventManagerImpl implements EventManager {

    private final EventRepository eventRepository;
    private final EventConverter eventConverter;

    @Override
    public GetEventResponse getEvent(Long eventId) {

        log.debug("Get Event by id: {}", eventId);
        Event event = eventRepository.findOne(eventId);
        GetEventResponse response = eventConverter.convertEvent(event);
        log.debug("Event was found: {}", response);

        return response;
    }

    @Override
    public List<GetEventResponse> getAllEvents() {

        log.debug("Get All Events");
        List<Event> events = eventRepository.findAll();
        List<GetEventResponse> getEventList = events.stream()
            .map(eventConverter::convertEvent)
            .collect(Collectors.toList());
        log.debug("Found Events: {}", getEventList);

        return getEventList;
    }

    @Override
    public GetEventResponse createEvent(CreateEventRequest createEventRequest) {

        log.debug("Create Event: {}", createEventRequest);
        Event event = eventConverter.convertRequest(createEventRequest);
        event.setEventStatus(EventStatus.ACTIVE);
        Event savedEvent = eventRepository.save(event);
        GetEventResponse getEventResponse = eventConverter.convertEvent(savedEvent);
        log.debug("Event successfully saved: {}", getEventResponse);
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
