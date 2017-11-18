package com.jedakah.drunkards.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Event extends AbstractEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "host_id")
  private User host;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "event_user",
      joinColumns = @JoinColumn(name = "event_id"),
      inverseJoinColumns = @JoinColumn(name = "drunkard_id")
  )
  private List<User> guests;
  @Embedded
  private Location location;
  private Date createdAt;
  private EventStatus eventStatus;

  @Data
  @Embeddable
  public static class Location {
    private String latitude;
    private String longitude;
  }

  public enum EventStatus {
    ACTIVE,
    COMPLETED
  }

}

