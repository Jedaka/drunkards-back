package com.jedakah.drunkards.entity;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class AbstractEntity {

  @Id
  @GeneratedValue
  @ApiModelProperty(hidden = true)
  private Long id;
  private String description;

}
