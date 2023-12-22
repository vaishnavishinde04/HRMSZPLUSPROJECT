package com.HRMS.service;

import java.util.List;

import com.HRMS.model.DesignationMaster;

public interface DesignationService {

	List<DesignationMaster> findAllDesignations();
	DesignationMaster addDesignation(DesignationMaster designation);
	DesignationMaster findById(int id);
	DesignationMaster updateDesignation(DesignationMaster designation,int id);
	void deleteDesignation(int id);
}
