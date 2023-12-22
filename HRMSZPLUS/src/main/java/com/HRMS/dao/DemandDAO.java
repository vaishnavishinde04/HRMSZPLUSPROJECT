package com.HRMS.dao;

import org.springframework.data.repository.CrudRepository;

import com.HRMS.model.DemandMaster;

public interface DemandDAO extends CrudRepository<DemandMaster, Integer> {

}
