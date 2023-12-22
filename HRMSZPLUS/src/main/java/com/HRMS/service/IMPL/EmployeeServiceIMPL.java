package com.HRMS.service.IMPL;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMS.dao.EmployeeDAO;
import com.HRMS.model.EmployeeMaster;
import com.HRMS.service.EmployeeService;

@Service
public class EmployeeServiceIMPL implements EmployeeService{

	@Autowired
	private EmployeeDAO employeedao;
	
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceIMPL.class);

	
	@Override
	public List<EmployeeMaster> getAllEmployees() {
		try {
			List<EmployeeMaster> findAll = (List<EmployeeMaster>) employeedao.findAll();
			return findAll;
		} catch (Exception e) {
			log.error("No Employee Availabe For Retrival");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public EmployeeMaster addEmployee(EmployeeMaster employee) {
		
		try {
			EmployeeMaster save = employeedao.save(employee);
			return save;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public EmployeeMaster findById(long id) {
		try {
			return employeedao.findById(id).orElse(null);
			
		}catch(Exception e)
		{
			log.error("Unable To Find Employee with id"+id);
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public boolean deleteEmployee(long id) {
		try
		{
			employeedao.deleteById(id);
			return true;
		}
		catch(Exception e)
		{
			log.error("Unable to Delete Employee With ID"+id);
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public void updateEmployee(EmployeeMaster employee) {
		try {
			employeedao.save(employee);
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

}
