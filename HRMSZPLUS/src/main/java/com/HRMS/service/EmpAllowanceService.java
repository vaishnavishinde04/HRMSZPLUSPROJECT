package com.HRMS.service;

import java.util.List;

import com.HRMS.model.EmpAllowanceMaster;

public interface EmpAllowanceService {

	EmpAllowanceMaster findById(long id);

	List<EmpAllowanceMaster> getEmployeeAllowancesById(long id);

	EmpAllowanceMaster saveEmployee(EmpAllowanceMaster empallowance);

	EmpAllowanceMaster getAllowanceById(long id);

	EmpAllowanceMaster getDataWithAllowanceidAndEmployeeid(long allowanceId, long employeeId);

	EmpAllowanceMaster editEmployeeData(EmpAllowanceMaster employeedata);

	void deleteEmpAllowance(Long id);
	
	double calculatePercentage(double d,long l);
	
	EmpAllowanceMaster findByAllowanceAllowanceNameAndEmployeeEmpId(String allowanceName, long employeeId );

}
