package com.HRMS.service.IMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMS.dao.WorkdayDAO;
import com.HRMS.model.WorkdayMaster;
import com.HRMS.service.HolidayService;
import com.HRMS.service.WorkdayService;

@Service
public class WorkdayServiceIMPL implements WorkdayService {

	@Autowired
	private WorkdayDAO workdaydao;
	
	@Autowired
    private HolidayService holidayService;

	@Override
	public List<WorkdayMaster> getAllWorkdays() {
		try {
			return (List<WorkdayMaster>) workdaydao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public WorkdayMaster saveWorkday(WorkdayMaster workdaymaster) {
		WorkdayMaster workmaster = null;
		int holidays=holidayService.countHolidays(workdaymaster.getYearOfWorkday(), workdaymaster.getMonthOfWorkday());
		int totalworkdays=workdaymaster.getNoOfWorkdays()-holidays;
		workdaymaster.setNoOfWorkdays(totalworkdays);
		try {
			workmaster = workdaydao.save(workdaymaster);
			return workmaster;
		} catch (Exception e) {
			e.printStackTrace();
			return workmaster;
		}
	}

	@Override
	public WorkdayMaster findWorkdayById(int id) {
		try {
			return workdaydao.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public WorkdayMaster updateWorkday(WorkdayMaster workdaymaster) {
		WorkdayMaster workmaster = null;

//		switch (workdaymaster.getMonthOfWorkday()) {
//		case "0":
//			workdaymaster.setMonthOfWorkday("January");
//			break;
//		case "1":
//			workdaymaster.setMonthOfWorkday("February");
//			break;
//		case "2":
//			workdaymaster.setMonthOfWorkday("March");
//			break;
//		case "3":
//			workdaymaster.setMonthOfWorkday("April");
//			break;
//		case "4":
//			workdaymaster.setMonthOfWorkday("May");
//			break;
//		case "5":
//			workdaymaster.setMonthOfWorkday("June");
//			break;
//		case "6":
//			workdaymaster.setMonthOfWorkday("July");
//			break;
//		case "7":
//			workdaymaster.setMonthOfWorkday("August");
//			break;
//		case "8":
//			workdaymaster.setMonthOfWorkday("September");
//			break;
//		case "9":
//			workdaymaster.setMonthOfWorkday("October");
//			break;
//		case "10":
//			workdaymaster.setMonthOfWorkday("November");
//			break;
//		case "11":
//			workdaymaster.setMonthOfWorkday("December");
//			break;
//		}
		try {
			workmaster = workdaydao.save(workdaymaster);
			return workmaster;

		} catch (Exception e) {
			e.printStackTrace();
			return workmaster;
		}
	}

	@Override
	public void deleteWorkday(int id) {
		try {
			workdaydao.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Failed to delete workday. Please try again later.");
		}
	}

	@Override
	public List<WorkdayMaster> findByYear(int year) {
		try {
			List<WorkdayMaster> findByYearOfWorkday = workdaydao.findByYearOfWorkday(year);
			return findByYearOfWorkday;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}