package com.HRMS.dao;

import org.springframework.data.repository.CrudRepository;

import com.HRMS.model.DepartmentMaster;

public interface DepartmentDAO extends CrudRepository<DepartmentMaster, Integer>{

}
