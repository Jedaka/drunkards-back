package com.jedakah.drunkards.repository;

import com.jedakah.drunkards.entity.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {

}
