package com.HRMS.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_login")

public class LoginMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@OneToOne
	@JoinColumn(name = "employee")
	private EmployeeMaster employee;
	
	private String username;
	private String password;
	private String role;
	private String email;
	
	
	public EmployeeMaster getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeMaster employee) {
		this.employee = employee;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LoginMaster() {
		super();
	}
	public LoginMaster(int userId, EmployeeMaster employee, String username, String password, String role,
			String email) {
		super();
		this.userId = userId;
		this.employee = employee;
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
	}
	
	
	

	
}
