package com.jedakah.drunkards.manager.impl;

import com.jedakah.drunkards.converters.EventConverter;
import com.jedakah.drunkards.entity.Event;
import com.jedakah.drunkards.entity.Event.EventStatus;
import com.jedakah.drunkards.entity.User;
import com.jedakah.drunkards.manager.EventManager;
import com.jedakah.drunkards.repository.EventRepository;
import com.jedakah.drunkards.repository.UserRepository;
import com.jedakah.drunkards.security.SecurityUtils;
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
    private final UserRepository userRepository;
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

        String userName = SecurityUtils.getUserNameFromSession();
        User currentUser = userRepository.findByName(userName);

        event.setHost(currentUser);
        event.setEventStatus(EventStatus.ACTIVE);
        Event savedEvent = eventRepository.save(event);
        GetEventResponse getEventResponse = eventConverter.convertEvent(savedEvent);
        log.debug("Event successfully saved: {}", getEventResponse);
        return getEventResponse;
    }

    @Override
    public GetEventResponse leaveEvent(Long eventId) {

        Event event = eventRepository.findOne(eventId);
        String userName = SecurityUtils.getUserNameFromSession();
        User currentUser = userRepository.findByName(userName);

        event.getGuests().remove(currentUser);
        eventRepository.save(event);
        return eventConverter.convertEvent(event);
    }

    @Override
    public GetEventResponse stopEvent(Long eventId) {

        Event event = eventRepository.findOne(eventId);
        event.setEventStatus(EventStatus.COMPLETED);

        //TODO: validate that this event is the current user one's.

        return eventConverter.convertEvent(event);
    }

    @Override
    public GetEventResponse joinEvent(Long eventId) {

        Event event = eventRepository.findOne(eventId);
        String userName = SecurityUtils.getUserNameFromSession();
        User currentUser = userRepository.findByName(userName);

        event.getGuests().add(currentUser);
        eventRepository.save(event);
        return eventConverter.convertEvent(event);
    }
}
