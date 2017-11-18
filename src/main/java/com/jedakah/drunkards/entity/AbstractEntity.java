package com.jedakah.drunkards.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity {

  @Id
  @GeneratedValue
  private Long id;
  private String description;

}
