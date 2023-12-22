package com.HRMS.service;

import java.util.List;

import com.HRMS.model.BankMaster;

public interface BankService {
	
	List<BankMaster> findAllBanks();
	BankMaster addBank(BankMaster Bank);
	BankMaster findById(int id);
	BankMaster updateBank(BankMaster Bank,int id);
	void deleteBank(int id);

}
