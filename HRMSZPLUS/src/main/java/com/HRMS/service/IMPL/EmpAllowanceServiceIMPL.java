package com.HRMS.service.IMPL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMS.dao.EmpAllowanceDAO;
import com.HRMS.model.EmpAllowanceMaster;
import com.HRMS.service.EmpAllowanceService;

@Service
public class EmpAllowanceServiceIMPL implements EmpAllowanceService {

	@Autowired
	private EmpAllowanceDAO empAllowanceDAO;

	@Override
	public EmpAllowanceMaster findById(long id) {
		try {
			return empAllowanceDAO.findById(id).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<EmpAllowanceMaster> getEmployeeAllowancesById(long id) {
		List<EmpAllowanceMaster> allowances;
		try {
			allowances = empAllowanceDAO.findByEmployeeEmpId(id);
		} catch (Exception e) {
			// Handle the exception appropriately
			e.printStackTrace();
			allowances = new ArrayList<>(); // Initialize the list to avoid returning null
		}
		return allowances;
	}

	@Override
	public void deleteEmpAllowance(Long id) {
		try {
			empAllowanceDAO.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Failed to delete empAllowance with ID " + id + ". Please try again later.");
		}
	}

	@Override
	public EmpAllowanceMaster saveEmployee(EmpAllowanceMaster empallowance) {
		try {

			if (empallowance.getAllowance().getAllowanceName().equals("Basic")) {
				return empAllowanceDAO.save(empallowance);
			} else {
				EmpAllowanceMaster dataWithAllowanceidAndEmployeeid = getDataWithAllowanceidAndEmployeeid(1,
						empallowance.getEmployee().getEmpId());
				double finalAmount = dataWithAllowanceidAndEmployeeid.getAmount() * (empallowance.getAmount() / 100);
				empallowance.setAmount(finalAmount);
				return empAllowanceDAO.save(empallowance);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public EmpAllowanceMaster getAllowanceById(long id) {
		try {
			return empAllowanceDAO.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public EmpAllowanceMaster getDataWithAllowanceidAndEmployeeid(long allowanceId, long employeeId) {
		try {
			EmpAllowanceMaster findByAllowanceIdAndEmployeeId = empAllowanceDAO
					.findByAllowanceAllowanceIdAndEmployeeEmpId(allowanceId, employeeId);
			return findByAllowanceIdAndEmployeeId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public EmpAllowanceMaster editEmployeeData(EmpAllowanceMaster employeedata) {
		if (employeedata.getAllowance().getAllowanceName().equals("Basic")) {
			return empAllowanceDAO.save(employeedata);
		} else {
			EmpAllowanceMaster dataWithAllowanceidAndEmployeeid = getDataWithAllowanceidAndEmployeeid(1,
					employeedata.getEmployee().getEmpId());
			double finalAmount = dataWithAllowanceidAndEmployeeid.getAmount() * (employeedata.getAmount() / 100);
			employeedata.setAmount(finalAmount);
			return empAllowanceDAO.save(employeedata);
		}
	}

	@Override
	public double calculatePercentage(double amount,long empId) {
		EmpAllowanceMaster dataWithAllowanceidAndEmployeeid = getDataWithAllowanceidAndEmployeeid(1,
				empId);
		double percentage=(amount/dataWithAllowanceidAndEmployeeid.getAmount())*100;
		return percentage;
	}

	@Override
	public EmpAllowanceMaster findByAllowanceAllowanceNameAndEmployeeEmpId(String allowanceName, long employeeId) {
		try {
			
			return empAllowanceDAO.findByAllowanceAllowanceNameAndEmployeeEmpId(allowanceName,employeeId);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
