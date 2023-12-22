package com.HRMS.service;

import java.util.List;

import com.HRMS.model.DepartmentMaster;

public interface DepartmentService {
	
	List<DepartmentMaster> findAllDepartments();
	DepartmentMaster addDepartment(DepartmentMaster Department);
	DepartmentMaster findById(int id);
	DepartmentMaster updateDepartment(DepartmentMaster Department,int id);
	void deleteDepartment(int id);

}
