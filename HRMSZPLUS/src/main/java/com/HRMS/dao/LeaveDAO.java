package com.HRMS.dao;

import org.springframework.data.repository.CrudRepository;

import com.HRMS.model.LeaveMaster;

public interface LeaveDAO  extends CrudRepository<LeaveMaster, Integer>{

}
