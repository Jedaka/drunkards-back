package com.jedakah.drunkards.to.event;

import java.util.List;
import lombok.Data;

@Data
public class EventHolder {

  private boolean isActive;
  private boolean isHost;
  private List<GetEventResponse> events;

}
