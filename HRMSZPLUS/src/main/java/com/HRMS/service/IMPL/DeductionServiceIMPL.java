package com.HRMS.service.IMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMS.dao.DeductionDao;
import com.HRMS.model.DeductionMaster;
import com.HRMS.service.DeductionService;

@Service
public class DeductionServiceIMPL implements DeductionService {
    
	@Autowired
	private DeductionDao deductionDao;
	
	@Override
	public List<DeductionMaster> getAllDeduction() {
		try {
			return (List<DeductionMaster>) deductionDao.findAll();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	
	}

	@Override
	public DeductionMaster saveDeduciton(DeductionMaster deductionMaster) {
		DeductionMaster deductionmaster =null;
		try {
			deductionmaster = deductionDao.save(deductionMaster);
			return deductionmaster;
		} catch (Exception e) {
			e.printStackTrace();
			return deductionmaster;
		}
	}

	@Override
	public DeductionMaster findDeductionById(int id) {
		try {
			return deductionDao.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DeductionMaster updateDeduction(DeductionMaster deductionMaster) {
		DeductionMaster deducMaster=null;
		try {
			deducMaster=deductionDao.save(deductionMaster);
			return deducMaster;
		} catch (Exception e) {
			e.printStackTrace();
			return deducMaster;
		}
		
		
	}

	@Override
	public void deleteDeduction(int id) {
		try {
			deductionDao.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("failed to delet deduction. Please try again later.");
		}
		
	}

}
