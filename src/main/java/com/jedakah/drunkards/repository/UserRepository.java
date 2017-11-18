package com.jedakah.drunkards.repository;

import com.jedakah.drunkards.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByName(String name);
}
