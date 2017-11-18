package com.jedakah.drunkards.repository;

import com.jedakah.drunkards.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
