package com.HRMS.service;

import java.util.List;

import com.HRMS.model.DemandMaster;

public interface DemandService {
	
	List<DemandMaster> getAllDemands();
	DemandMaster saveDemand(DemandMaster demand);
	DemandMaster getDemandDetailsById(int demandId);
	DemandMaster editDemand(DemandMaster demandToBeEdited);
	void deleteAllClosedDemands();
}
