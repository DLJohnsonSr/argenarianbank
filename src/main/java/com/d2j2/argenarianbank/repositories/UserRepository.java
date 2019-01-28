package com.d2j2.argenarianbank.repositories;

import com.d2j2.argenarianbank.models.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<AppUser, Long> {

    AppUser findByUsername(String username);
}
