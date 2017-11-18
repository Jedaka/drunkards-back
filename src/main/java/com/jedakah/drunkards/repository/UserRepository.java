package com.jedakah.drunkards.repository;

import com.jedakah.drunkards.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

}
