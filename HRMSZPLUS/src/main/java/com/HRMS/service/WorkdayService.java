package com.HRMS.service;

import java.util.List;

import com.HRMS.model.WorkdayMaster;

public interface WorkdayService {

	List<WorkdayMaster> getAllWorkdays();

	WorkdayMaster saveWorkday(WorkdayMaster workdaymaster);

	WorkdayMaster findWorkdayById(int id);

	WorkdayMaster updateWorkday(WorkdayMaster workdaymaster);

	void deleteWorkday(int id);
	
	List<WorkdayMaster> findByYear(int year);

}
