package com.HRMS.service;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

import com.HRMS.model.LeaveMaster;

public interface LeaveService {

	List<LeaveMaster> getAllLeaves();
	
	LeaveMaster saveLeave(LeaveMaster leavemaster);
	
	LeaveMaster findLeaveById(int id);
	
	LeaveMaster updateLeave(LeaveMaster leavemaster);
	
	void deleteLeave(int id);
	
	public Map<YearMonth, Integer> calculateLeaveDaysByMonth(LeaveMaster leavemaster);
}