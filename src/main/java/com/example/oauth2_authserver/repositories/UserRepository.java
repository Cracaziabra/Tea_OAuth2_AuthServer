package com.example.oauth2_authserver.repositories;

import com.example.oauth2_authserver.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

}
