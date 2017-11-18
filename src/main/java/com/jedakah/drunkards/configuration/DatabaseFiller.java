package com.jedakah.drunkards.configuration;

import com.jedakah.drunkards.entity.User;
import com.jedakah.drunkards.manager.UserManager;
import com.jedakah.drunkards.to.user.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DatabaseFiller implements CommandLineRunner{

  public static final String HOST = "host";
  public static final String GUEST = "guest";

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  UserManager userManager;

  @Override
  public void run(String... strings) throws Exception {


    CreateUserRequest user1 = new CreateUserRequest();
    user1.setName(HOST);
    user1.setPassword(encoder.encode(HOST));
    CreateUserRequest user2 = new CreateUserRequest();
    user2.setName(GUEST);
    user2.setPassword(encoder.encode(GUEST));

    userManager.createUser(user1);
    userManager.createUser(user2);

  }
}
