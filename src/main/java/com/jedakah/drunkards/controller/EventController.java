package com.jedakah.drunkards.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.jedakah.drunkards.entity.Event;
import com.jedakah.drunkards.manager.EventManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    EventManager eventManager;

    @RequestMapping(value = "", method = RequestMethod.POST,
        consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {

        Event createdEvent = eventManager.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    @RequestMapping(value = "/{eventId}/leave", method = RequestMethod.POST,
        produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> leaveEvent(@PathVariable("eventId") Long eventId) {

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/{eventId}/stop", method = RequestMethod.POST,
        produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> stopEvent(@PathVariable("eventId") Long eventId) {

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/{eventId}/join", method = RequestMethod.POST,
        produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> joinEvent(@PathVariable("eventId") Long eventId) {

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET,
        produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> getEvent(@PathVariable("eventId") Long eventId) {

        Event event = eventManager.getEvent(eventId);

        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET,
        produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getAllEvents() {

        List<Event> allEvents = eventManager.getAllEvents();

        return new ResponseEntity<>(allEvents, HttpStatus.OK);
    }

}
