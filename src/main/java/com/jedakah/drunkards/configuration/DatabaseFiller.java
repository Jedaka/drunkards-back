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

        User host = new User();
        host.setName(HOST);
        host.setPassword(encoder.encode(HOST));
        host.setTelephoneNumber("8 (800) 555-35-35");
        host.setFirstName("Georgiy");
        host.setLastName("Gorohov");
        host.setAge(18);

        User guest = new User();
        guest.setName(GUEST);
        guest.setPassword(encoder.encode(GUEST));
        guest.setTelephoneNumber("8 (800) 555-35-35");
        guest.setFirstName("Vasya");
        guest.setLastName("Vas");
        guest.setAge(90);

        User[] users = new User[10];

        for (int i = 0; i < 10; i++) {
            User anotherHost = new User();
            anotherHost.setName(HOST + i);
            anotherHost.setPassword(encoder.encode(HOST));
            anotherHost.setTelephoneNumber("8 (800) 555-22-33");
            anotherHost.setFirstName("Artur");
            anotherHost.setLastName("Arturov");
            anotherHost.setAge(18);

            userRepository.save(anotherHost);
        }

        userRepository.save(host);
        userRepository.save(guest);
    }

    private void fillEvents() {

        Double baseLat = 59.9;
        Double baseLng = 30.3;

        Location[] locations = new Location[10];

        for (int i = 0; i < 10; i++) {
            Location location = new Location();
            location.setLatitude(String.valueOf(baseLat + Math.random() / 10));
            location.setLongitude(String.valueOf(baseLng + Math.random() / 10));

            Event event = new Event();
            event.setHost(userRepository.findByName(HOST + i));
            event.setLocation(location);
            event.setEventStatus(Event.EventStatus.ACTIVE);
            event.setDescription("4 этаж, квартира " + i);

            eventRepository.save(event);
        }
    }

}
