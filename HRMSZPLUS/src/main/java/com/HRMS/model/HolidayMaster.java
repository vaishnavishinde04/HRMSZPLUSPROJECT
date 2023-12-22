package com.HRMS.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_holiday")
public class HolidayMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int holidayId;
	
	private Date holidayDate;
	
	private String holidayDescription;

	/**
	 * @return the holidayId
	 */
	public int getHolidayId() {
		return holidayId;
	}

	/**
	 * @param holidayId the holidayId to set
	 */
	public void setHolidayId(int holidayId) {
		this.holidayId = holidayId;
	}

	/**
	 * @return the holidayDate
	 */
	public Date getHolidayDate() {
		return holidayDate;
	}

	/**
	 * @param holidayDate the holidayDate to set
	 */
	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	/**
	 * @return the holidayDescription
	 */
	public String getHolidayDescription() {
		return holidayDescription;
	}

	/**
	 * @param holidayDescription the holidayDescription to set
	 */
	public void setHolidayDescription(String holidayDescription) {
		this.holidayDescription = holidayDescription;
	}

	public HolidayMaster(int holidayId, Date holidayDate, String holidayDescription) {
		super();
		this.holidayId = holidayId;
		this.holidayDate = holidayDate;
		this.holidayDescription = holidayDescription;
	}

	public HolidayMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "HolidayMaster [holidayId=" + holidayId + ", holidayDate=" + holidayDate + ", holidayDescription="
				+ holidayDescription + "]";
	}
	
	
	
}
