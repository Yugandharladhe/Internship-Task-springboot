package com.task.backend.db.mongodb.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.task.backend.db.mongodb.dto.DepartmentDto;
import com.task.backend.db.mongodb.entities.Department;
import com.task.backend.db.mongodb.repositories.DepartmentRepo;
import com.task.backend.db.mongodb.exceptions.ResourceNotFoundException;
@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepo deptRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	public DepartmentDto createDepartment(DepartmentDto dept)
	{
		Department dept2=this.dtoToDepartment(dept);
		dept2.setCreatedAt(new Date());
		dept2.setUpdatedAt(new Date());
		Department saved=this.deptRepo.save(dept2);
		return this.departmentToDto(saved);		
	}
	
	public List<DepartmentDto> getAllDepartment()
	{
		List<Department> allDept=this.deptRepo.findAll();
		List<DepartmentDto> dto=new ArrayList<>();
		allDept.forEach(dept->{
			dto.add(this.departmentToDto(dept));
		});
		
		return dto;
	}
	
	public DepartmentDto updateDepartment(DepartmentDto dto,String dId)
	{
		Department dept=this.deptRepo.findById(dId).orElseThrow(() -> new ResourceNotFoundException("Department", "id", dId));
		dept.setDepartmentName(dto.getDepartmentName());
		dept.setUpdatedAt(new Date());
		Department updated=this.deptRepo.save(dept);
		return this.departmentToDto(updated);
	}
	
	public void deleteDepartment(String deptId)
	{
		Department dept=this.deptRepo.findById(deptId).orElseThrow(() -> new ResourceNotFoundException("Department", "id", deptId));
		this.deptRepo.delete(dept);
	}
	
	public DepartmentDto getDepartment(String deptId)
	{
		Department dept=this.deptRepo.findById(deptId).orElseThrow();
		return this.mapper.map(dept, DepartmentDto.class);
	}
	
	public DepartmentDto departmentToDto(Department dept)
	{
		return this.mapper.map(dept, DepartmentDto.class);
	}
	
	public Department dtoToDepartment(DepartmentDto dept)
	{
		return this.mapper.map(dept, Department.class);
	}
}
