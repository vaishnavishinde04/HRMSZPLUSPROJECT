package com.HRMS.service.IMPL;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMS.dao.LeaveDAO;
import com.HRMS.model.LeaveMaster;
import com.HRMS.service.LeaveService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;


@Service
public class LeaveServiceIMPL implements LeaveService{
	
	@Autowired
	private LeaveDAO leavedao;

	@Override
	public List<LeaveMaster> getAllLeaves() {

		try {
			return (List<LeaveMaster>) leavedao.findAll();
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public LeaveMaster saveLeave(LeaveMaster leavemaster) {

		LeaveMaster leamaster = null;
		
		try {
			leavemaster = leavedao.save(leavemaster);
			return leamaster;
		}catch(Exception e)
		{
			e.printStackTrace();
			return leamaster;
		}
	}

	@Override
	public LeaveMaster findLeaveById(int id) {
			try {
				return leavedao.findById(id).get();
			}catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
	}

	@Override
	public LeaveMaster updateLeave(LeaveMaster leavemaster) {
		
		LeaveMaster leamaster = null;
		try {
			leavemaster = leavedao.save(leavemaster);
			return leamaster;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteLeave(int id) {

		try {
			leavedao.deleteById(id);
		}catch(Exception e)
		{
			throw new RuntimeException("Failed to delete leave. Please try again later.");
		}
			
	}

	@Override
	public Map<YearMonth, Integer> calculateLeaveDaysByMonth(LeaveMaster leavemaster) {
	    try {
	        Date leaveFromDate = leavemaster.getLeaveFrom(); // Assuming LeaveMaster has getter methods returning Date
	        Date leaveToDate = leavemaster.getLeaveTo();

	        // Convert Date objects to LocalDate
	        LocalDate leaveFrom = leaveFromDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        LocalDate leaveTo = leaveToDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	        Map<YearMonth, Integer> leaveDaysByMonth = new LinkedHashMap<>();

	        while (!leaveFrom.isAfter(leaveTo)) {
	            YearMonth yearMonth = YearMonth.from(leaveFrom);
	            LocalDate endOfMonth = yearMonth.atEndOfMonth();
	            long daysInMonth = ChronoUnit.DAYS.between(leaveFrom, endOfMonth) + 1;
	            
	            // Determine the number of leave days within the current month
	            long leaveDaysInMonth = Math.min(daysInMonth, ChronoUnit.DAYS.between(leaveFrom, leaveTo) + 1);
	            
	            leaveDaysByMonth.put(yearMonth, (int) leaveDaysInMonth);
	            
	            // Move to the next month
	            leaveFrom = leaveFrom.plusMonths(1);
	        }
	        
	        return leaveDaysByMonth;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Collections.emptyMap();
	    }
}
}