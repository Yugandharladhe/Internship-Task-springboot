package com.task.backend.db.postgres.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.backend.db.mongodb.responses.ApiResponse;
import com.task.backend.db.postgres.dto.EmployeeDto;
import com.task.backend.db.postgres.dto.EmployeeWithDeptDto;
import com.task.backend.db.postgres.repositories.EmployeeRepo;
import com.task.backend.db.postgres.services.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	EmployeeService service;
	
	@PostMapping("/create")
	public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto dto)
	{
		EmployeeDto emp=this.service.createNewEmployee(dto);
		return new ResponseEntity<>(emp,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("empId") Long empId)
	{
		this.service.deleteEmployee(empId);
		return new ResponseEntity<>(new ApiResponse("employee deleted successfully",true),HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<EmployeeWithDeptDto>> getAllEmployee()
	{
		List<EmployeeWithDeptDto> allDto=this.service.getAllEmployees();
		return new ResponseEntity<>(allDto,HttpStatus.OK);
	}
	
	@GetMapping("/{empId}")
	public ResponseEntity<EmployeeWithDeptDto> getEmployee(@PathVariable("empId") Long id)
	{
		EmployeeWithDeptDto dto=this.service.getParticularEmployee(id);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
}
