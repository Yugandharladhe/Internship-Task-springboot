package com.task.backend.db.mongodb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.task.backend.db.mongodb.dto.DepartmentDto;
import com.task.backend.db.mongodb.responses.ApiResponse;
import com.task.backend.db.mongodb.services.DepartmentService;

@RestController
@RequestMapping("/api")
public class DepartmentController {

	@Autowired
	DepartmentService service;
	
	@PostMapping("/create")
	public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto dto)
	{
		DepartmentDto deptDto=service.createDepartment(dto);
		return new ResponseEntity<DepartmentDto>(deptDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{deptId}")
	public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("deptId") String deptId,@RequestBody DepartmentDto dto)
	{
		DepartmentDto deptDto=service.updateDepartment(dto, deptId);
		return new ResponseEntity<DepartmentDto>(deptDto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{deptId}")
	public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable("deptId") String deptId)
	{
		this.service.deleteDepartment(deptId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("department deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/allDepartments")
	public ResponseEntity<?> getAllDepartment()
	{
		List<DepartmentDto> allDepts=this.service.getAllDepartment();
		return new ResponseEntity<List<DepartmentDto>>(allDepts,HttpStatus.FOUND);
	}
	
	@GetMapping("/department/{deptId}")
	public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("deptId") String deptId)
	{
		DepartmentDto dto=this.service.getDepartment(deptId);
		return new ResponseEntity<DepartmentDto>(dto,HttpStatus.OK);
	}
}
