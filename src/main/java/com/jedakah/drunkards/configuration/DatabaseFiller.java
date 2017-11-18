package com.jedakah.drunkards.configuration;

import com.jedakah.drunkards.entity.Event;
import com.jedakah.drunkards.entity.Event.Location;
import com.jedakah.drunkards.entity.User;
import com.jedakah.drunkards.repository.EventRepository;
import com.jedakah.drunkards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DatabaseFiller implements CommandLineRunner {

  public static final String HOST = "host";
  public static final String HOST2 = HOST + "2";
  public static final String HOST3 = HOST + "3";
  public static final String GUEST = "guest";

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private EventRepository eventRepository;

  @Override
  public void run(String... strings) throws Exception {

    fillUsers();
    fillEvents();
  }

  private void fillUsers() {

    User user1 = new User();
    user1.setName(HOST);
    user1.setPassword(encoder.encode(HOST));
    user1.setTelephoneNumber("88005553535");
    user1.setAge(18);

    User user2 = new User();
    user2.setName(HOST2);
    user2.setPassword(encoder.encode(HOST2));
    user2.setTelephoneNumber("89992232110");
    user2.setAge(20);

    User user3 = new User();
    user3.setName(HOST3);
    user3.setPassword(encoder.encode(HOST3));
    user3.setTelephoneNumber("89992232111");
    user3.setAge(22);

    User user4 = new User();
    user4.setName(GUEST);
    user4.setPassword(encoder.encode(GUEST));
    user4.setTelephoneNumber("4242");
    user4.setAge(90);

    userRepository.save(user1);
    userRepository.save(user2);
    userRepository.save(user3);
    userRepository.save(user4);
  }

  private void fillEvents() {

    Location location1 = new Location();
    location1.setLatitude("59.9633625");
    location1.setLongitude("30.3190594");

    Location location2 = new Location();
    location2.setLatitude("59.9672719");
    location2.setLongitude("30.3349381");

    Event event1 = new Event();
    event1.setHost(userRepository.findByName(HOST2));
    event1.setDescription("4 этаж, квартира 52");
    event1.setLocation(location1);
    event1.setEventStatus(Event.EventStatus.ACTIVE);

    Event event2 = new Event();
    event2.setHost(userRepository.findByName(HOST3));
    event2.setLocation(location2);
    event2.setEventStatus(Event.EventStatus.COMPLETED);

    eventRepository.save(event1);
    eventRepository.save(event2);
  }

}
