package com.HRMS.service.IMPL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMS.dao.EmpDeductionDAO;
import com.HRMS.model.EmpAllowanceMaster;
import com.HRMS.model.EmpDeductionMaster;
import com.HRMS.service.EmpAllowanceService;
import com.HRMS.service.EmpDeductionService;

@Service
public class EmpDeductionServiceIMPL implements EmpDeductionService {

	@Autowired
	private EmpAllowanceService empAllowanceService;
	
	@Autowired
	private EmpDeductionDAO empDedDao;

	@Override
	public EmpDeductionMaster findById(long id) {
		try {
			return empDedDao.findById(id).orElse(null);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<EmpDeductionMaster> getEmployeeDeductionsById(long id) {
		List<EmpDeductionMaster> deductions;
		try {
			deductions = empDedDao.findByEmployeeEmpId(id);
		} catch (Exception e) {
			e.printStackTrace();
			deductions = new ArrayList<>();
		}
		return deductions;
	}

	@Override
	public void deleteEmpDeduction(Long id) {
		try {
			empDedDao.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Failed todelete empDeduction with ID" + id + ".please try again later.");
		}
	}

	@Override
	public EmpDeductionMaster saveEmployee(EmpDeductionMaster empDeduction) {
		try {
			EmpAllowanceMaster dataWithAllowanceidAndEmployeeid = empAllowanceService.getDataWithAllowanceidAndEmployeeid(1, empDeduction.getEmployee().getEmpId());
			double totalamount=dataWithAllowanceidAndEmployeeid.getAmount()*(empDeduction.getAmount()/100);
			empDeduction.setAmount(totalamount);
			return empDedDao.save(empDeduction);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public EmpDeductionMaster getDataWithDeductionidAndEmployeeid(long deductionId, long empId) {
		try {
			EmpDeductionMaster employeedata = empDedDao.findByDeductionDeductionIdAndEmployeeEmpId(deductionId, empId);
			return employeedata;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public EmpDeductionMaster editEmployeeData(EmpDeductionMaster employeedata) {
		try {
			EmpAllowanceMaster dataWithAllowanceidAndEmployeeid = empAllowanceService.getDataWithAllowanceidAndEmployeeid(1, employeedata.getEmployee().getEmpId());
			double totalamount=dataWithAllowanceidAndEmployeeid.getAmount()*(employeedata.getAmount()/100);
			
			employeedata.setAmount(totalamount);
			EmpDeductionMaster savedEmployeeData = empDedDao.save(employeedata);
			return savedEmployeeData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
