package com.HRMS.service.IMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMS.dao.ProjectDAO;
import com.HRMS.model.ProjectMaster;
import com.HRMS.service.ProjectService;

@Service
public class ProjectServiceIMPL implements ProjectService {

	@Autowired
	private ProjectDAO pDAO;

	@Override
	public List<ProjectMaster> getAllProjects() {

		try {
			return (List<ProjectMaster>) pDAO.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ProjectMaster getProjectById(int projectId) {
		try {
			return pDAO.findById(projectId).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
