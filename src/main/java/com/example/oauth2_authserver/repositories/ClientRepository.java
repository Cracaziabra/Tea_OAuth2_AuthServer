package com.example.oauth2_authserver.repositories;

import com.example.oauth2_authserver.entities.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Optional<Client> findClientByClientId(String clientId);

}
