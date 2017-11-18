package com.jedakah.drunkards.controller;

import com.jedakah.drunkards.entity.Event;
import com.jedakah.drunkards.manager.EventManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    EventManager eventManager;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Event> createEvent(Event event) {

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(value = "/{eventId}/leave", method = RequestMethod.POST)
    public ResponseEntity<Event> leaveEvent(@PathVariable("eventId") String eventId) {

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/{eventId}/stop", method = RequestMethod.POST)
    public ResponseEntity<Event> stopEvent(@PathVariable("eventId") String eventId) {

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/{eventId}/join", method = RequestMethod.POST)
    public ResponseEntity<Event> joinEvent(@PathVariable("eventId") String eventId) {

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public ResponseEntity<Event> getEvent(@PathVariable("eventId") String eventId) {

        Event event = eventManager.getEvent(eventId);

        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List> getAllEvents() {

        List<Event> allEvents = eventManager.getAllEvents();

        return new ResponseEntity<>(allEvents, HttpStatus.OK);
    }

}
