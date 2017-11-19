package com.jedakah.drunkards.to.event;

import com.jedakah.drunkards.entity.Event.EventStatus;
import com.jedakah.drunkards.to.user.GetUserResponse;
import java.util.List;
import lombok.Data;

@Data
public class GetEventResponse extends EventTO {

  private Long id;
  private String hostUserName;
  private GetUserResponse host;
  private List<String> guestList;
  private EventStatus eventStatus;
}
