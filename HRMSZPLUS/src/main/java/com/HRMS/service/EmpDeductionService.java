package com.HRMS.service;

import java.util.List;

import com.HRMS.model.EmpDeductionMaster;

public interface EmpDeductionService {
	EmpDeductionMaster findById(long id);

	List<EmpDeductionMaster> getEmployeeDeductionsById(long id);

	EmpDeductionMaster saveEmployee(EmpDeductionMaster empDeduction);

	EmpDeductionMaster getDataWithDeductionidAndEmployeeid(long deductionId, long empId);

	EmpDeductionMaster editEmployeeData(EmpDeductionMaster employeedata);

	void deleteEmpDeduction(Long id);

}
