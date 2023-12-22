package com.HRMS.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_demands")
public class DemandMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long demandId;

	@ManyToOne
	@JoinColumn(name = "projectId")
	private ProjectMaster Project;

	private Date demandDate;

	@ManyToOne
	@JoinColumn(name="designationId")
	private DesignationMaster Position;
	
	private String requiredSkills;
	private String Priority;
	private String numberOfPositions;
	private String Description;
	private String Status;
	public long getDemandId() {
		return demandId;
	}
	public void setDemandId(long demandId) {
		this.demandId = demandId;
	}
	public ProjectMaster getProject() {
		return Project;
	}
	public void setProject(ProjectMaster project) {
		Project = project;
	}
	public Date getDemandDate() {
		return demandDate;
	}
	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}
	public DesignationMaster getPosition() {
		return Position;
	}
	public void setPosition(DesignationMaster position) {
		Position = position;
	}
	public String getRequiredSkills() {
		return requiredSkills;
	}
	public void setRequiredSkills(String requiredSkills) {
		this.requiredSkills = requiredSkills;
	}
	public String getPriority() {
		return Priority;
	}
	public void setPriority(String priority) {
		Priority = priority;
	}
	public String getNumberOfPositions() {
		return numberOfPositions;
	}
	public void setNumberOfPositions(String numberOfPositions) {
		this.numberOfPositions = numberOfPositions;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public DemandMaster(long demandId, ProjectMaster project, Date demandDate, DesignationMaster position,
			String requiredSkills, String priority, String numberOfPositions, String description, String status) {
		super();
		this.demandId = demandId;
		Project = project;
		this.demandDate = demandDate;
		Position = position;
		this.requiredSkills = requiredSkills;
		Priority = priority;
		this.numberOfPositions = numberOfPositions;
		Description = description;
		Status = status;
	}
	public DemandMaster() {
		super();
	}
	@Override
	public String toString() {
		return "DemandMaster [demandId=" + demandId + ", Project=" + Project + ", demandDate=" + demandDate
				+ ", Position=" + Position + ", requiredSkills=" + requiredSkills + ", Priority=" + Priority
				+ ", numberOfPositions=" + numberOfPositions + ", Description=" + Description + ", Status=" + Status
				+ "]";
	}
	
	

	

}
