package com.HRMS.service;

import java.util.List;

import com.HRMS.model.LoginMaster;

public interface LoginService {
	
	List<LoginMaster> getAllUser();
	boolean newUser(LoginMaster loginmaster);
	LoginMaster findUserLoginsById(int id);
	
	LoginMaster findByUsername(String username);

	
	LoginMaster checklogin(LoginMaster loginmaster);
	void updateLogins(LoginMaster loginmaster);
	void deleteUser(int id);

	
}
