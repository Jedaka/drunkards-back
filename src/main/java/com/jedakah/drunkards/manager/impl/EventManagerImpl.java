package com.jedakah.drunkards.manager.impl;

import com.jedakah.drunkards.entity.Event;
import com.jedakah.drunkards.manager.EventManager;
import com.jedakah.drunkards.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Profile("prod")
@Component
public class EventManagerImpl implements EventManager {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event getEvent(Long eventId) {

        log.debug("Get Event by id: {}", eventId);
        Event event = eventRepository.findOne(eventId);
        log.debug("Event was found: {}", event);

        return event;
    }

    @Override
    public List<Event> getAllEvents() {

        log.debug("Get All Events");
        List<Event> events = eventRepository.findAll();
        log.debug("Found {} Events", events.size());

        return events;
    }

    @Override
    public Event createEvent(Event event) {

        log.debug("Create Event: {}", event);
        eventRepository.save(event);
        log.debug("Event successfully saved");
        return event;
    }

    @Override
    public Event updateEvent(Event event) {

        log.debug("Update Event: {}", event);
        // TODO: 11/18/17 update logic
        log.debug("Event successfully updated");
        return event;
    }
}
