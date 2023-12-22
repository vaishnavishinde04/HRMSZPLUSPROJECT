package com.HRMS.service;

import java.util.List;

import com.HRMS.model.EmployeeMaster;

public interface EmployeeService {
	
	List<EmployeeMaster> getAllEmployees();
	EmployeeMaster addEmployee(EmployeeMaster employee);
	EmployeeMaster findById(long id);
	boolean deleteEmployee(long id);
	void updateEmployee(EmployeeMaster employee);

}
