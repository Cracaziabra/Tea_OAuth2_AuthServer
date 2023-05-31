package com.example.oauth2_authserver.repositories;

import com.example.oauth2_authserver.entities.GrantType;
import org.springframework.data.repository.CrudRepository;

public interface GrantTypeRepository extends CrudRepository<GrantType, Long> {

}
