package com.task.backend.db.postgres.dto;

import java.util.Date;

import com.task.backend.db.mongodb.entities.Department;

public class EmployeeWithDeptDto {

private long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Department dept;
	
	private Date createdAt;
	
	private Date updatedAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public EmployeeWithDeptDto(long id, String firstName, String lastName, String email, Department dept,
			Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dept = dept;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public EmployeeWithDeptDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
