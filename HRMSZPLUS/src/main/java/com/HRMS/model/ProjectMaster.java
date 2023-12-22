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
@Table(name = "tbl_projects")
public class ProjectMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int projectId;
	
	private String projectName;
	private String projectDescription;
	private Date startDate;
	private Date endDate;
	private String Status;
	private String Priority;
	private String Location;
	private String ProjectType;
	private long budget;
	
	private int ClientID;

	@ManyToOne
	@JoinColumn(name = "managerId")
	private EmployeeMaster projectManager;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getPriority() {
		return Priority;
	}

	public void setPriority(String priority) {
		Priority = priority;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getProjectType() {
		return ProjectType;
	}

	public void setProjectType(String projectType) {
		ProjectType = projectType;
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}

	public int getClientID() {
		return ClientID;
	}

	public void setClientID(int clientID) {
		ClientID = clientID;
	}

	public EmployeeMaster getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(EmployeeMaster projectManager) {
		this.projectManager = projectManager;
	}

	public ProjectMaster(int projectId, String projectName, String projectDescription, Date startDate, Date endDate,
			String status, String priority, String location, String projectType, long budget, int clientID,
			EmployeeMaster projectManager) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.startDate = startDate;
		this.endDate = endDate;
		Status = status;
		Priority = priority;
		Location = location;
		ProjectType = projectType;
		this.budget = budget;
		ClientID = clientID;
		this.projectManager = projectManager;
	}

	public ProjectMaster() {
		super();
	}
	
	
}
