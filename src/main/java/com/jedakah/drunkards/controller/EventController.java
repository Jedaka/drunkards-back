package com.jedakah.drunkards.controller;

import com.jedakah.drunkards.entity.Event;
import com.jedakah.drunkards.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/api/events")
public class EventController {


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Event> createEvent(Event eventTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(value = "/{eventId}/leave", method = RequestMethod.POST)
    public ResponseEntity<Event> leaveEvent() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/{eventId}/stop", method = RequestMethod.POST)
    public ResponseEntity<Event> stopEvent() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


    @RequestMapping(value = "/{eventId}/join", method = RequestMethod.POST)
    public ResponseEntity<Event> joinEvent() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public ResponseEntity<Event> getEvent() {

        Event event = new Event();
        event.setEventStatus(Event.EventStatus.ACTIVE);

        User user = new User();
        user.setAge(40);
        user.setTelephoneNumber("8-800-555-35-35");
        event.setHost(user);

        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List> getAllEvents() {

        Event event = new Event();
        event.setEventStatus(Event.EventStatus.ACTIVE);

        User user = new User();
        user.setAge(40);
        user.setTelephoneNumber("8-800-555-35-35");
        event.setHost(user);

        return new ResponseEntity<>(Collections.singletonList(event), HttpStatus.OK);
    }

}
