package com.jedakah.drunkards.converters;

import com.jedakah.drunkards.entity.Event;
import com.jedakah.drunkards.entity.Event.Location;
import com.jedakah.drunkards.entity.User;
import com.jedakah.drunkards.to.event.CreateEventRequest;
import com.jedakah.drunkards.to.event.GetEventResponse;
import com.jedakah.drunkards.to.user.GetUserResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@RequiredArgsConstructor
public class EventConverter {

  private final UserConverter userConverter;

  public Event convertRequest(CreateEventRequest createEventRequest) {

    Event event = new Event();
    Location location = new Location();
    location.setLatitude(createEventRequest.getLatitude());
    location.setLongitude(createEventRequest.getLongitude());
    event.setLocation(location);
    event.setDescription(createEventRequest.getDescription());
    return event;
  }

  public GetEventResponse convertEvent(Event event) {

    GetEventResponse getEventResponse = new GetEventResponse();
    getEventResponse.setId(event.getId());
    getEventResponse.setEventStatus(event.getEventStatus());
    getEventResponse.setDescription(event.getDescription());
    getEventResponse.setLatitude(event.getLocation().getLatitude());
    getEventResponse.setLongitude(event.getLocation().getLongitude());
    getEventResponse.setGuestList(new ArrayList<>());

    if (event.getHost() != null) {
      getEventResponse.setHostUserName(event.getHost().getName());

      GetUserResponse host = userConverter.convertUser(event.getHost());
      getEventResponse.setHost(host);
    }

    if (!CollectionUtils.isEmpty(event.getGuests())) {
      List<String> guestNames = event.getGuests().stream()
          .map(User::getName)
          .collect(Collectors.toList());

      getEventResponse.setGuestList(guestNames);
    }

    return getEventResponse;
  }

}
