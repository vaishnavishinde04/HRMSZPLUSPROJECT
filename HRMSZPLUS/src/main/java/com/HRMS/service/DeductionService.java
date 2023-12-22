package com.HRMS.service;

import java.util.List;

import com.HRMS.model.DeductionMaster;

public interface DeductionService {
	

	List<DeductionMaster> getAllDeduction();

	DeductionMaster saveDeduciton(DeductionMaster deductionMaster);

	DeductionMaster findDeductionById(int id);

	DeductionMaster updateDeduction(DeductionMaster deductionMaster);

	void deleteDeduction(int id);

}
