package com.HRMS.dao;

import org.springframework.data.repository.CrudRepository;

import com.HRMS.model.LoginMaster;

public interface LoginDAO extends CrudRepository<LoginMaster, Integer>{
	
	LoginMaster findByUsername(String username);
}
