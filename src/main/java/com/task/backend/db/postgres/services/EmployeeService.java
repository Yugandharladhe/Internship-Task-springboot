package com.task.backend.db.postgres.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.task.backend.db.mongodb.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.matcher.LatentMatcher.ForFieldToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.backend.db.mongodb.entities.Department;
import com.task.backend.db.mongodb.exceptions.ResourceNotFoundException;
import com.task.backend.db.mongodb.repositories.DepartmentRepo;
import com.task.backend.db.postgres.dto.EmployeeDto;
import com.task.backend.db.postgres.dto.EmployeeWithDeptDto;
import com.task.backend.db.postgres.entities.Employee;
import com.task.backend.db.postgres.repositories.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo repo;
	
	@Autowired
	private DepartmentRepo deptRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EmployeeDto createNewEmployee(EmployeeDto empDto)
	{
		Employee emp=this.modelMapper.map(empDto, Employee.class);
		emp.setCreatedAt(new Date());
		emp.setUpdatedAt(new Date());
		Employee saved=this.repo.save(emp);
		return this.modelMapper.map(saved,EmployeeDto.class);
	}
	
	public EmployeeDto updateEmployee(EmployeeDto empDto, long id)
	{
		Employee empOut=this.repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", Long.toString(id)));
		empOut.setUpdatedAt(new Date());
		this.modelMapper.map(empDto, empOut);
		Employee updated=repo.save(empOut);
		return this.employeeToDto(updated);
	}
	
	public List<EmployeeWithDeptDto> getAllEmployees()
	{
		List<Employee> allEmp=this.repo.findAll();
		List<Department> allDept=this.deptRepo.findAll();
		
		List<EmployeeWithDeptDto> allDtos = new ArrayList<>();
		allEmp.forEach(emp -> {
			EmployeeWithDeptDto dto=this.modelMapper.map(emp, EmployeeWithDeptDto.class);
			allDept.forEach(dept->{
				if(emp.getDepartmentId().equals(dept.getDepartmentId()))
				{
					dto.setDept(dept);
				}
			});
			allDtos.add(dto);
		});
		
		return allDtos;
	}
	
	public EmployeeWithDeptDto getParticularEmployee(long id)
	{
		Employee emp=this.repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", Long.toString(id)));
		String dId=emp.getDepartmentId();
		Department dept=this.deptRepo.findById(dId).orElseThrow();
		EmployeeWithDeptDto dto=new EmployeeWithDeptDto();
		this.modelMapper.map(emp,dto);
		dto.setDept(dept);
		return dto;
	}
	
	public void deleteEmployee(long id)
	{
		Employee emp=repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department", "id", Long.toString(id)));
		repo.delete(emp);
	}
	
	public EmployeeDto employeeToDto(Employee employee) {

		EmployeeDto employeeDto = this.modelMapper.map(employee, EmployeeDto.class);
		return employeeDto;
	}

	public Employee dtoToEmployee(EmployeeDto employeeDto) {
		Employee employee = this.modelMapper.map(employeeDto, Employee.class);
		
		return employee;
	}
}
