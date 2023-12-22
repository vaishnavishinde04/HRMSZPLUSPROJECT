package com.HRMS.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_workday")
public class WorkdayMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workday_id")
	private int workdayId;

    @Column(name = "year")
    private int yearOfWorkday;
    
    @Column(name = "month")
    private int monthOfWorkday;
    
    @Column(name = "noOfDays")
    private int noOfWorkdays;
    
    @Column(name = "workdaysInWeek") // Adjust the length as needed
    private String selectedDaysOfWeek; // Store selected days as a comma-separated string

	public int getWorkdayId() {
		return workdayId;
	}

	public void setWorkdayId(int workdayId) {
		this.workdayId = workdayId;
	}

	public int getYearOfWorkday() {
		return yearOfWorkday;
	}

	public void setYearOfWorkday(int yearOfWorkday) {
		this.yearOfWorkday = yearOfWorkday;
	}

	public int getMonthOfWorkday() {
		return monthOfWorkday;
	}

	public void setMonthOfWorkday(int monthOfWorkday) {
		this.monthOfWorkday = monthOfWorkday;
	}

	public int getNoOfWorkdays() {
		return noOfWorkdays;
	}

	public void setNoOfWorkdays(int noOfWorkdays) {
		this.noOfWorkdays = noOfWorkdays;
	}

	public String getSelectedDaysOfWeek() {
		return selectedDaysOfWeek;
	}

	public void setSelectedDaysOfWeek(String selectedDaysOfWeek) {
		this.selectedDaysOfWeek = selectedDaysOfWeek;
	}

	public WorkdayMaster(int workdayId, int yearOfWorkday, int monthOfWorkday, int noOfWorkdays,
			String selectedDaysOfWeek) {
		super();
		this.workdayId = workdayId;
		this.yearOfWorkday = yearOfWorkday;
		this.monthOfWorkday = monthOfWorkday;
		this.noOfWorkdays = noOfWorkdays;
		this.selectedDaysOfWeek = selectedDaysOfWeek;
	}

	public WorkdayMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "WorkdayMaster [workdayId=" + workdayId + ", yearOfWorkday=" + yearOfWorkday + ", monthOfWorkday="
				+ monthOfWorkday + ", noOfWorkdays=" + noOfWorkdays + ", selectedDaysOfWeek=" + selectedDaysOfWeek
				+ "]";
	}

	
    



}