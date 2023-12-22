package com.HRMS.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tbl_leave")
public class LeaveMaster {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int leaveId;
	
	private int employeeId;
	
	private String leaveType;
	
	private Date leaveFrom;
	
	private Date leaveTo;
	
	private int leaveDays;
	
	private String leaveDescription;
	
	private String leaveStatus;

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public Date getLeaveFrom() {
		return leaveFrom;
	}

	public void setLeaveFrom(Date leaveFrom) {
		this.leaveFrom = leaveFrom;
	}

	public Date getLeaveTo() {
		return leaveTo;
	}

	public void setLeaveTo(Date leaveTo) {
		this.leaveTo = leaveTo;
	}

	public int getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(int leaveDays) {
		this.leaveDays = leaveDays;
	}

	public String getLeaveDescription() {
		return leaveDescription;
	}

	public void setLeaveDescription(String leaveDescription) {
		this.leaveDescription = leaveDescription;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public LeaveMaster(int leaveId, int employeeId, String leaveType, Date leaveFrom, Date leaveTo, int leaveDays,
			String leaveDescription, String leaveStatus) {
		super();
		this.leaveId = leaveId;
		this.employeeId = employeeId;
		this.leaveType = leaveType;
		this.leaveFrom = leaveFrom;
		this.leaveTo = leaveTo;
		this.leaveDays = leaveDays;
		this.leaveDescription = leaveDescription;
		this.leaveStatus = leaveStatus;
	}

	public LeaveMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LeaveMaster [leaveId=" + leaveId + ", employeeId=" + employeeId + ", leaveType=" + leaveType
				+ ", leaveFrom=" + leaveFrom + ", leaveTo=" + leaveTo + ", leaveDays=" + leaveDays
				+ ", leaveDescription=" + leaveDescription + ", leaveStatus=" + leaveStatus + "]";
	}

	
	
}
