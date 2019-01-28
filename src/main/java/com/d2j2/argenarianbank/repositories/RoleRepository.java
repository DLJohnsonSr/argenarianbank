package com.d2j2.argenarianbank.repositories;

import com.d2j2.argenarianbank.models.AppRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<AppRole, Long> {

    AppRole findByRoleName(String role);
}
