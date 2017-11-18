package com.jedakah.drunkards.entity;

import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class User extends AbstractEntity {

  private int age;
  private String telephoneNumber;

}
