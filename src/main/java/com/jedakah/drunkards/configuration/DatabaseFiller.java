package com.jedakah.drunkards.configuration;

import com.jedakah.drunkards.manager.EventManager;
import com.jedakah.drunkards.manager.UserManager;
import com.jedakah.drunkards.to.event.CreateEventRequest;
import com.jedakah.drunkards.to.user.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DatabaseFiller implements CommandLineRunner {

  public static final String HOST = "host";
  public static final String GUEST = "guest";

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  UserManager userManager;

  @Autowired
  EventManager eventManager;

  @Override
  public void run(String... strings) throws Exception {

    fillUsers();
    fillEvents();
  }

  private void fillUsers() {

    CreateUserRequest user1 = new CreateUserRequest();
    user1.setName(HOST);
    user1.setPassword(encoder.encode(HOST));
    user1.setTelephoneNumber("88005553535");
    user1.setAge(18);
    CreateUserRequest user2 = new CreateUserRequest();
    user2.setName(GUEST);
    user2.setPassword(encoder.encode(GUEST));
    user2.setTelephoneNumber("4242");
    user2.setAge(90);

    userManager.createUser(user1);
    userManager.createUser(user2);
  }

  private void fillEvents() {

    CreateEventRequest event1 = new CreateEventRequest();
    event1.setLatitude("59.9633625");
    event1.setLongitude("30.3190594");
    CreateEventRequest event2 = new CreateEventRequest();
    event2.setLatitude("59.9672719");
    event2.setLongitude("30.3349381");

    eventManager.createEvent(event1);
    eventManager.createEvent(event2);
  }

}
