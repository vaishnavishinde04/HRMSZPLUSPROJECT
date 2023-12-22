package com.HRMS.service;

import java.util.List;

import com.HRMS.model.ProjectMaster;

public interface ProjectService {

	List<ProjectMaster> getAllProjects();
	ProjectMaster getProjectById(int projectId);
}
