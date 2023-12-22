package com.HRMS.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.HRMS.model.CandidateMaster;
import com.HRMS.service.CandidateService;

@Controller
public class CandidateController {

	private static final Logger log = LoggerFactory.getLogger(CandidateController.class);

	@Autowired
	private CandidateService CService;
	
	@GetMapping("/getAllCandidates")
	public String getAllCandidates(Model model) {

		List<CandidateMaster> allCandidate = CService.getAllCandidateService();
		model.addAttribute("allCandidates", allCandidate);
		return "/a";
	}
	
	@GetMapping("/getCandidate/{id}")
	public String getCandidate(@PathVariable("id") int candidateId,Model model)
	{
		CandidateMaster candidateById = CService.getCandidateById(candidateId);
		model.addAttribute("Candidate", candidateById);
		return "/a";
	}

}
