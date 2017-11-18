package com.jedakah.drunkards.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity {

  private Long id;
  private String description;

}
