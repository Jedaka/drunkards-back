package com.jedakah.drunkards.to.user;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class UserTO {

  @NotEmpty
  private String name;

  @NotNull
  @Min(18)
  private int age;

  @NotEmpty
  @Pattern(regexp="\\+?1?\\s*\\(?-*\\.*(\\d{3})\\)?\\.*-*\\s*(\\d{3})\\.*-*\\s*(\\d{4})$")
  private String telephoneNumber;

}
