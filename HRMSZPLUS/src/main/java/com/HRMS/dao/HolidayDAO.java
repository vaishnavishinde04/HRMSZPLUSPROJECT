package com.HRMS.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.HRMS.model.HolidayMaster;

public interface HolidayDAO extends CrudRepository<HolidayMaster, Integer>{
	
	@Query(value = "SELECT COUNT(*) FROM tbl_holiday WHERE YEAR(holiday_date) = :year AND MONTH(holiday_date) = :month", nativeQuery = true)
    int countHolidaysByYearAndMonth(@Param("year") int year, @Param("month") int month);


}
