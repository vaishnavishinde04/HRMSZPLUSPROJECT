package com.HRMS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_allowance")
public class AllowanceMaster {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int allowanceId;

    private String allowanceName;

    private String allowanceDescription;
    
   

	public AllowanceMaster(int allowanceId, String allowanceName, String allowanceDescription) {
		super();
		this.allowanceId = allowanceId;
		this.allowanceName = allowanceName;
		this.allowanceDescription = allowanceDescription;
	}

	public AllowanceMaster() {
		super();
	}

	public int getAllowanceId() {
		return allowanceId;
	}

	public void setAllowanceId(int allowanceId) {
		this.allowanceId = allowanceId;
	}

	public String getAllowanceName() {
		return allowanceName;
	}

	public void setAllowanceName(String allowanceName) {
		this.allowanceName = allowanceName;
	}

	public String getAllowanceDescription() {
		return allowanceDescription;
	}

	public void setAllowanceDescription(String allowanceDescription) {
		this.allowanceDescription = allowanceDescription;
	}

	@Override
	public String toString() {
		return "AllowanceMaster [allowanceId=" + allowanceId + ", allowanceName=" + allowanceName
				+ ", allowanceDescription=" + allowanceDescription + "]";
	}
    
    
}
