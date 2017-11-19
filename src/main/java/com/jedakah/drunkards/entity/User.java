package com.jedakah.drunkards.entity;

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

  @Column(nullable = false)
  private int age;
  @Column(nullable = false)
  private String telephoneNumber;
  @Column(nullable = false, unique = true)
  private String name;
  private String password;
  @OneToMany(
      mappedBy = "host",
      fetch = FetchType.EAGER)
  private List<Event> hostEvents;
  @ManyToMany(
      mappedBy = "guests",
      fetch = FetchType.EAGER)
  private List<Event> guestEvents;

  private String firstName;
  private String lastName;

}
