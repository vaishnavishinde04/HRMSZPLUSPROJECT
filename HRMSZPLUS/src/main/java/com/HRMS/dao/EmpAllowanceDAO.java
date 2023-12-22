package com.HRMS.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.HRMS.model.EmpAllowanceMaster;


public interface EmpAllowanceDAO extends CrudRepository<EmpAllowanceMaster, Long> {

	EmpAllowanceMaster findById(int id);
    
	List<EmpAllowanceMaster> findByEmployeeEmpId(Long employeeId);
	
	EmpAllowanceMaster findByAllowanceAllowanceIdAndEmployeeEmpId(long allowanceId, long employeeId);
	
	EmpAllowanceMaster findByAllowanceAllowanceNameAndEmployeeEmpId(String allowanceName, long employeeId);

	
//	void deleteById(int id);
}
