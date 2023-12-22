package com.HRMS.service;

import java.io.ByteArrayInputStream;

public interface SalarySlipService {

	ByteArrayInputStream generateSalarySlip(Long empId);
	

}
