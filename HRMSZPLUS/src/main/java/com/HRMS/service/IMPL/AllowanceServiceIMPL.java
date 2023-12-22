package com.HRMS.service.IMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMS.dao.AllowanceDAO;
import com.HRMS.model.AllowanceMaster;
import com.HRMS.service.AllowanceService;

@Service
public class AllowanceServiceIMPL implements AllowanceService {

	@Autowired
	private AllowanceDAO allowancedao;

	@Override
	public List<AllowanceMaster> getAllAllowances() {
		try {
			return (List<AllowanceMaster>) allowancedao.findAll();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
			

	}

	@Override
	public AllowanceMaster saveAllowance(AllowanceMaster allowancemaster) {
		
		AllowanceMaster allowmaster=null;
		try
		{
			allowmaster= allowancedao.save(allowancemaster);
			return allowmaster;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return allowmaster;
		}
		

	}

	@Override
	public AllowanceMaster findAllowanceById(int id) {

		try
		{
			return allowancedao.findById(id).get();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		

	}

	@Override
	public AllowanceMaster updateAllowance(AllowanceMaster allowancemaster) {
		AllowanceMaster allowmaster=null;
		try
		{
			allowmaster= allowancedao.save(allowancemaster);
			return allowmaster;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return allowmaster;
		}
	}

	@Override
	public void deleteAllowance(int id) {
		try {
			allowancedao.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Failed to delete allowance. Please try again later.");
		}
	}

}
