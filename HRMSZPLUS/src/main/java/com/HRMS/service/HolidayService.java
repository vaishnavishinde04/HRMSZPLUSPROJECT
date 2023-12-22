package com.HRMS.service;

import java.util.List;

import com.HRMS.model.HolidayMaster;

public interface HolidayService {
	
	List<HolidayMaster> getAllHolidays();
	
	HolidayMaster saveHoliday(HolidayMaster holidaymaster);
	
	HolidayMaster findHolidayById(int id);
	
	HolidayMaster updateHoliday(HolidayMaster holidaymaster);
	
	void deleteHoliday(int id);

	int countHolidays(int year, int month);


}
