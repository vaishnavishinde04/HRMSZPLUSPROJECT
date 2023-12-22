package com.HRMS.dao;

import org.springframework.data.repository.CrudRepository;

import com.HRMS.model.WorkdayMaster;
import java.util.List;


public interface WorkdayDAO extends CrudRepository<WorkdayMaster, Integer> {
	
List<WorkdayMaster> findByYearOfWorkday(int yearOfWorkday);
}
