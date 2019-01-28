package com.d2j2.argenarianbank.repositories;

import com.d2j2.argenarianbank.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
