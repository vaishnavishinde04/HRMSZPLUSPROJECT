package com.HRMS.dao;

import org.springframework.data.repository.CrudRepository;

import com.HRMS.model.AllowanceMaster;

public interface AllowanceDAO extends CrudRepository<AllowanceMaster, Integer>
{
	
}
