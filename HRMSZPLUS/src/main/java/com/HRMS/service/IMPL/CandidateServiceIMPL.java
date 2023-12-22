package com.HRMS.service.IMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMS.dao.CandidateDAO;
import com.HRMS.model.CandidateMaster;
import com.HRMS.service.CandidateService;

@Service
public class CandidateServiceIMPL implements CandidateService {

	@Autowired
	private CandidateDAO candidateDao;

	@Override
	public List<CandidateMaster> getAllCandidateService() {
		try {

			List<CandidateMaster> allCandidates = (List<CandidateMaster>) candidateDao.findAll();
			return allCandidates;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CandidateMaster getCandidateById(int candidateId) {
		try {
			return candidateDao.findById(candidateId).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteCandidate(int candidateId) {
		try {
			candidateDao.deleteById(candidateId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
