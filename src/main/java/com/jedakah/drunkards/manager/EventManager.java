package com.jedakah.drunkards.manager;

import com.jedakah.drunkards.entity.Event;

import java.util.List;

public interface EventManager {

    Event getEvent(Long eventId);
    List<Event> getAllEvents();
    Event createEvent(Event event);
    Event updateEvent(Event event);

}
