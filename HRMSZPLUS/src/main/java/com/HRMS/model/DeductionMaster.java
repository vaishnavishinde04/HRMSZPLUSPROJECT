package com.HRMS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_deduction")
public class DeductionMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int deductionId;
	
	private String deductionName;
	
	private String deductionDescription;
     
	
	
	public DeductionMaster() {
		super();
	}



	public DeductionMaster(int deductionId, String deductionName, String deductionDescription) {
		super();
		this.deductionId = deductionId;
		this.deductionName = deductionName;
		this.deductionDescription = deductionDescription;
	}



	public int getDeductionId() {
		return deductionId;
	}



	public void setDeductionId(int deductionId) {
		this.deductionId = deductionId;
	}



	public String getDeductionName() {
		return deductionName;
	}



	public void setDeductionName(String deductionName) {
		this.deductionName = deductionName;
	}



	public String getDeductionDescription() {
		return deductionDescription;
	}



	public void setDeductionDescription(String deductionDescription) {
		this.deductionDescription = deductionDescription;
	}



	@Override
	public String toString() {
		return "DeductionMaster [deductionId=" + deductionId + ", deductionName=" + deductionName
				+ ", deductionDescription=" + deductionDescription + "]";
	}
	
	
	

}
