package com.HRMS.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HRMS.model.DemandMaster;
import com.HRMS.model.DesignationMaster;
import com.HRMS.model.ProjectMaster;
import com.HRMS.service.DemandService;
import com.HRMS.service.DesignationService;
import com.HRMS.service.ProjectService;

@Controller
public class DemandController {

	@Autowired
	private DesignationService designationService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private DemandService demandService;

	@GetMapping("/allDemands")
	public String getAllDemands(Model model) {

		List<DemandMaster> allDemands = demandService.getAllDemands();
        
        if (allDemands.isEmpty()) {
            model.addAttribute("errorMessage", "No Demands Available.");
            return "Demands/allDemands";
        }

        model.addAttribute("allDemands", allDemands);
        return "Demands/allDemands";
	}

	@GetMapping("/demandDetail/{id}")
	public String getDemandDetails(@PathVariable("id") int demandId, Model model) {
		DemandMaster demandDetailsById = demandService.getDemandDetailsById(demandId);
		if(demandDetailsById==null)
		{
			return "ErrorPages/ErrorPage";
		}
		model.addAttribute("demandDetails", demandDetailsById);
		return "Demands/DemandDetailsWithCandidatesList";
	}

	@GetMapping("/createDemand")
	public String createDemandForm(Model model) {
		List<DesignationMaster> AllDesignations = designationService.findAllDesignations();
		List<ProjectMaster> allProjects = projectService.getAllProjects();

		model.addAttribute("AllDesignations", AllDesignations);
		model.addAttribute("allProjects", allProjects);

		DemandMaster demand = new DemandMaster();
		model.addAttribute("demand", demand);
		return "Demands/createDemand";
	}

	@PostMapping("/createDemand")
	public String createDemand(@ModelAttribute("demand") DemandMaster demand, Model model) {
		LocalDate currentDate = LocalDate.now();
		Date sqlDate = Date.valueOf(currentDate);

		DesignationMaster designationById = designationService.findById(demand.getPosition().getDesignationId());
		demand.setPosition(designationById);

		ProjectMaster projectById = projectService.getProjectById(demand.getProject().getProjectId());
		demand.setProject(projectById);

		demand.setDemandDate(sqlDate);
		demand.setStatus("Opened");

		DemandMaster savedDemand = demandService.saveDemand(demand);
//		return savedDemand;
		return "redirect:/allDemands";

	}

	@GetMapping("/closeDemand/{id}")
	public String closeDemand(@PathVariable("id") int demandIdToBeClosed) {
		DemandMaster demandDetailsById = demandService.getDemandDetailsById(demandIdToBeClosed);
		demandDetailsById.setStatus("Closed");
		demandService.editDemand(demandDetailsById);
		return "redirect:/allDemands";
	}

	@GetMapping("/editDemand/{id}")
	public String editDemandForm(@PathVariable("id") int demandId, Model model) {
		DemandMaster demandDetailsById = demandService.getDemandDetailsById(demandId);
		System.out.print("sssssssssssssssssssssss" + demandDetailsById.getDemandId());
		model.addAttribute("demand", demandDetailsById);
		return "Demands/editDemand";
	}

	@PostMapping("/editDemand")
	public String editDemand(@ModelAttribute("demand") DemandMaster demand) {

		DesignationMaster designationById = designationService.findById(demand.getPosition().getDesignationId());
		demand.setPosition(designationById);

		ProjectMaster projectById = projectService.getProjectById(demand.getProject().getProjectId());
		demand.setProject(projectById);

		DemandMaster editedDemand = demandService.editDemand(demand);
		System.out.print("ssssssssssssssssssssssssss" + demand.getDemandId());
		return "redirect:/demandDetail/" + demand.getDemandId();
	}

	@GetMapping("/deleteAllClosedDemands")
	public String deleteAllClosedDemands() {
		demandService.deleteAllClosedDemands();
		return "redirect:/allDemands";
	}

}
