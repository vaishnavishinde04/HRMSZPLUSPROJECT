package com.HRMS.service.IMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMS.dao.DemandDAO;
import com.HRMS.model.DemandMaster;
import com.HRMS.service.DemandService;

@Service
public class DemandServiceIMPL implements DemandService {

	@Autowired
	private DemandDAO demandDAO;

	@Override
	public DemandMaster saveDemand(DemandMaster demand) {
		try {
			return demandDAO.save(demand);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<DemandMaster> getAllDemands() {
		try {
			return (List<DemandMaster>) demandDAO.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DemandMaster getDemandDetailsById(int demandId) {
		try {
			return demandDAO.findById(demandId).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DemandMaster editDemand(DemandMaster demandToBeEdited) {
		try {
			return demandDAO.save(demandToBeEdited);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteAllClosedDemands() {
		try {
			List<DemandMaster> allDemands = (List<DemandMaster>) demandDAO.findAll();
			for(DemandMaster demand:allDemands)
			{
				if(demand.getStatus().equals("Closed"))
				{
					demandDAO.delete(demand);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
