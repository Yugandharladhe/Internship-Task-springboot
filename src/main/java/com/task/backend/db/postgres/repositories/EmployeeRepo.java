package com.task.backend.db.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.backend.db.postgres.entities.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Long>{

}
