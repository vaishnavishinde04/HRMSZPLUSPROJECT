package com.HRMS.service;

import java.util.List;

import com.HRMS.model.AllowanceMaster;

public interface AllowanceService {

	List<AllowanceMaster> getAllAllowances();

	AllowanceMaster saveAllowance(AllowanceMaster allowancemaster);

	AllowanceMaster findAllowanceById(int id);

	AllowanceMaster updateAllowance(AllowanceMaster allowancemaster);

	void deleteAllowance(int id);
}
