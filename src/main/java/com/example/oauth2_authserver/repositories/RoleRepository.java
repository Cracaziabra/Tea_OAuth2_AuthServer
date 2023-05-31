package com.example.oauth2_authserver.repositories;

import com.example.oauth2_authserver.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
