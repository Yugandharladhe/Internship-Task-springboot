package com.task.backend.db.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.task.backend.db.mongodb.entities.Department;

public interface DepartmentRepo extends MongoRepository<Department,String>{

}
