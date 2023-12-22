package com.HRMS.service.IMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMS.dao.HolidayDAO;
import com.HRMS.model.HolidayMaster;
import com.HRMS.service.HolidayService;

@Service
public class HolidayServiceIMPL implements HolidayService {

	@Autowired
	private HolidayDAO holidaydao;

	@Override
	public List<HolidayMaster> getAllHolidays() {
		try {
			return (List<HolidayMaster>) holidaydao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public HolidayMaster saveHoliday(HolidayMaster holidaymaster) {
		HolidayMaster holidmaster = null;

		try {
			holidaymaster = holidaydao.save(holidaymaster);
			return holidmaster;
		} catch (Exception e) {
			e.printStackTrace();
			return holidmaster;
		}
	}

	@Override
	public HolidayMaster findHolidayById(int id) {
		try {
			return holidaydao.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public HolidayMaster updateHoliday(HolidayMaster holidaymaster) {
		HolidayMaster holidmaster = null;

		try {
			holidmaster = holidaydao.save(holidaymaster);
			return holidmaster;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteHoliday(int id) {

		try {
			holidaydao.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Failed to delete holiday. Please try again later.");
		}
	}

	@Override
	public int countHolidays(int year, int month) {
		System.out.print("YEARRRRRRRRRRRR:"+year);
		System.out.print("YEARRRRRRRRRRRR:"+month);
		try {
			return holidaydao.countHolidaysByYearAndMonth(year, month+1);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
