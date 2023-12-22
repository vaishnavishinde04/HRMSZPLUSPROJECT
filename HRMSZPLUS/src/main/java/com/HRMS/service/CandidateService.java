package com.HRMS.service;

import java.util.List;

import com.HRMS.model.CandidateMaster;

public interface CandidateService {

	List<CandidateMaster> getAllCandidateService();
	CandidateMaster getCandidateById(int candidateId);
	void deleteCandidate(int candidateId);
}
