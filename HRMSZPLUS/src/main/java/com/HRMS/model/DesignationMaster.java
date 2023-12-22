package com.HRMS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_designation")
public class DesignationMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int designationId;
	
	private String designationName;
	private String designationDescription;
	
	public int getDesignationId() {
		return designationId;
	}
	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getDesignationDescription() {
		return designationDescription;
	}
	public void setDesignationDescription(String designationDescription) {
		this.designationDescription = designationDescription;
	}
	public DesignationMaster(int designationId, String designationName, String designationDescription) {
		super();
		this.designationId = designationId;
		this.designationName = designationName;
		this.designationDescription = designationDescription;
	}
	public DesignationMaster() {
		super();
	}
	
	
	
}
