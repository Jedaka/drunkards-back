package com.jedakah.drunkards.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.jedakah.drunkards.manager.EventManager;
import com.jedakah.drunkards.to.event.CreateEventRequest;
import com.jedakah.drunkards.to.event.EventFilter;
import com.jedakah.drunkards.to.event.EventHolder;
import com.jedakah.drunkards.to.event.GetEventResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    EventManager eventManager;

    @RequestMapping(value = "", method = RequestMethod.POST,
        consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetEventResponse> createEvent(@RequestBody CreateEventRequest request) {

        GetEventResponse eventResponse = eventManager.createEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventResponse);
    }

    @RequestMapping(value = "/{eventId}/leave", method = RequestMethod.POST,
        produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetEventResponse> leaveEvent(@PathVariable("eventId") Long eventId) {

        GetEventResponse getEventResponse = eventManager.leaveEvent(eventId);
        return ResponseEntity.status(HttpStatus.OK).body(getEventResponse);
    }

    @RequestMapping(value = "/{eventId}/stop", method = RequestMethod.POST,
        produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetEventResponse> stopEvent(@PathVariable("eventId") Long eventId) {

        GetEventResponse getEventResponse = eventManager.stopEvent(eventId);
        return ResponseEntity.status(HttpStatus.OK).body(getEventResponse);
    }

    @RequestMapping(value = "/{eventId}/join", method = RequestMethod.POST,
        produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetEventResponse> joinEvent(@PathVariable("eventId") Long eventId) {

        GetEventResponse getEventResponse = eventManager.joinEvent(eventId);
        return ResponseEntity.status(HttpStatus.OK).body(getEventResponse);
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET,
        produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetEventResponse> getEvent(@PathVariable("eventId") Long eventId) {

        GetEventResponse eventResponse = eventManager.getEvent(eventId);

        return new ResponseEntity<>(eventResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET,
        produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EventHolder> getAllEvents() {

        EventHolder events = eventManager.getAllEvents();

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetEventResponse>> getEventsByFilter(@RequestParam(value = "lat") String lat,
                                                                    @RequestParam(value = "lng") String lng,
                                                                    @RequestParam(value = "radiusInMeters") String radiusInMeters) {

        EventFilter eventFilter = new EventFilter(lat, lng, radiusInMeters);

        List<GetEventResponse> eventResponseList = eventManager.getEventsByFilter(eventFilter);

        return new ResponseEntity<>(eventResponseList, HttpStatus.OK);
    }

}
