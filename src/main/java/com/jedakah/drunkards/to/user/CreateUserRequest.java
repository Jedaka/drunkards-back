package com.jedakah.drunkards.to.user;

import lombok.Data;

@Data
public class CreateUserRequest extends UserTO {

  private String password;

}
