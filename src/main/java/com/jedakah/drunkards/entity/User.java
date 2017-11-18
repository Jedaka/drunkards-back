package com.jedakah.drunkards.entity;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "drunkard")
public class User extends AbstractEntity {

  private int age;
  private String telephoneNumber;
  @Column(nullable = false, unique = true)
  private String name;
  private String password;
  @OneToMany(
      mappedBy = "host",
      fetch = FetchType.LAZY)
  @ApiModelProperty(hidden = true)
  private List<Event> hostEvents;
  @ManyToMany(
      mappedBy = "guests",
      fetch = FetchType.LAZY)
  @ApiModelProperty(hidden = true)
  private List<Event> guestEvents;

}
