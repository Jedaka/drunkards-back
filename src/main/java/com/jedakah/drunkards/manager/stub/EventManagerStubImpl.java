package com.jedakah.drunkards.manager.stub;

import com.jedakah.drunkards.entity.Event;
import com.jedakah.drunkards.entity.User;
import com.jedakah.drunkards.manager.EventManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Profile(value = "stub")
@Component
public class EventManagerStubImpl implements EventManager {

    @Override
    public Event getEvent(String eventId) {

        Event event = new Event();
        event.setEventStatus(Event.EventStatus.ACTIVE);

        User user = new User();
        user.setAge(40);
        user.setTelephoneNumber("8-800-555-35-35");
        event.setHost(user);
        return event;
    }

    @Override
    public List<Event> getAllEvents() {

        Event event = new Event();
        event.setEventStatus(Event.EventStatus.ACTIVE);

        User user = new User();
        user.setAge(40);
        user.setTelephoneNumber("8-800-555-35-35");
        event.setHost(user);
        return Collections.singletonList(event);
    }

    @Override
    public Event createEvent(Event event) {
        return event;
    }

    @Override
    public Event updateEvent(Event event) {
        return event;
    }
}
