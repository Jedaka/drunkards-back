package com.jedakah.drunkards.manager.impl;

import com.jedakah.drunkards.converters.EventConverter;
import com.jedakah.drunkards.entity.Event;
import com.jedakah.drunkards.entity.Event.EventStatus;
import com.jedakah.drunkards.entity.User;
import com.jedakah.drunkards.exceptions.ValidationException;
import com.jedakah.drunkards.manager.EventManager;
import com.jedakah.drunkards.repository.EventRepository;
import com.jedakah.drunkards.repository.UserRepository;
import com.jedakah.drunkards.security.SecurityUtils;
import com.jedakah.drunkards.to.event.CreateEventRequest;
import com.jedakah.drunkards.to.event.EventsFilter;
import com.jedakah.drunkards.to.event.GetEventResponse;
import java.util.ArrayList;
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

        if (haveNotCompletedEvents(currentUser)) {
            throw new ValidationException("User can not create event cause he has not completed events");
        }

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

        if (!event.getGuests().contains(currentUser)) {
            throw new ValidationException("User can not leave this event cause he is not a guest");
        }
        if (event.getEventStatus() == EventStatus.COMPLETED) {
            throw new ValidationException("User can not leave this event cause it is completed");
        }

        event.getGuests().remove(currentUser);
        eventRepository.save(event);
        return eventConverter.convertEvent(event);
    }

    @Override
    public GetEventResponse stopEvent(Long eventId) {

        Long currentUserId = SecurityUtils.getCurrentUserFromSession().getUser().getId();

        Event event = eventRepository.findOne(eventId);
        if (!currentUserId.equals(event.getHost().getId())) {
            throw new ValidationException("Current user is not an owner of requested event");
        }


        String userName = SecurityUtils.getUserNameFromSession();
        User currentUser = userRepository.findByName(userName);
        if (!currentUser.getHostEvents().contains(event)) {
            throw new ValidationException("Current user is not a host of this event");
        }

        if (event.getEventStatus() == EventStatus.COMPLETED) {
            throw new ValidationException("Event is already in completed state");
        }

        event.setEventStatus(EventStatus.COMPLETED);

        Event savedEvent = eventRepository.save(event);

        return eventConverter.convertEvent(savedEvent);
    }

    @Override
    public GetEventResponse joinEvent(Long eventId) {

        Event event = eventRepository.findOne(eventId);
        String userName = SecurityUtils.getUserNameFromSession();
        User currentUser = userRepository.findByName(userName);

        if (event.getHost().equals(currentUser)) {
            throw new ValidationException("Current user can not join his own event as guest");
        }

        if (event.getGuests().contains(currentUser)) {
            throw new ValidationException("Current user is already a guest in this event");
        }

        if (haveNotCompletedEvents(currentUser)) {
            throw new ValidationException("Current user have not completed events");
        }

        event.getGuests().add(currentUser);
        eventRepository.save(event);
        return eventConverter.convertEvent(event);
    }

    @Override
    public List<GetEventResponse> getEventsByFilter(EventsFilter eventsFilter) {

        log.debug("Get Events By Filter: {}", eventsFilter);
        List<Event> events = eventRepository.findAll();
        List<GetEventResponse> getEventList = events.stream()
                .filter(eventsFilter::isEventInRadius)
                .map(eventConverter::convertEvent)
                .collect(Collectors.toList());
        log.debug("Found Events: {}", getEventList);

        return getEventList;
    }

    private boolean haveNotCompletedEvents(User user) {

        List<Event> allEvents = new ArrayList<>();
        allEvents.addAll(user.getGuestEvents());
        allEvents.addAll(user.getHostEvents());

        long countOfNotCompletedEvents = allEvents.stream()
            .filter(event -> event.getEventStatus() == EventStatus.ACTIVE)
            .count();

        return countOfNotCompletedEvents > 0;
    }
}
