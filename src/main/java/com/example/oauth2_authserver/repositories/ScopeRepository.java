package com.example.oauth2_authserver.repositories;

import com.example.oauth2_authserver.entities.Scope;
import org.springframework.data.repository.CrudRepository;

public interface ScopeRepository extends CrudRepository<Scope, Long> {
}
