package com.HRMS.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.HRMS.model.AllowanceMaster;
import com.HRMS.service.AllowanceService;

@Controller
public class AllowanceController {

	private static final Logger logger = LoggerFactory.getLogger(AllowanceController.class);

	@Autowired
	private AllowanceService allowanceService; //comment
	
	public void setAllowanceService(AllowanceService allowanceService) {
	    this.allowanceService = allowanceService;
	}

	@GetMapping("/allowances")
	public String getAllAllowances(Model model) {

		List<AllowanceMaster> allowances = allowanceService.getAllAllowances();
		if (allowances.isEmpty()) {
			logger.error("No Allowance Avilable To Retrieve");
			return "/Allowance/AllowanceMaintenance";
		}
		model.addAttribute("allowances", allowances);
		return "/Allowance/AllowanceMaintenance";
	}

	@GetMapping("/allowances/new")
	public String createAllowanceForm(Model model) {
		model.addAttribute("allowance", new AllowanceMaster());
		return "/Allowance/NewAllowance";
	}

	@PostMapping("/allowances")
	public String addAllowance(@ModelAttribute("allowance") AllowanceMaster allowancemaster) {

		AllowanceMaster allowance = allowanceService.saveAllowance(allowancemaster);
		if (allowance == null) {
			logger.error("Unable to Save Data");
		}

		return "redirect:/allowances";

	}

	@GetMapping("/allowances/edit/{id}")
	public String editAllowance(@PathVariable("id") int id, Model model) {
		AllowanceMaster allowance = allowanceService.findAllowanceById(id);

		// Check if the allowance with the given ID exists
		if (allowance == null) {

			logger.error("Allowance with ID " + id + " not found.");
			return "redirect:/allowances";
		}

		model.addAttribute("allowance", allowance);
		return "/Allowance/EditAllowance";
	}

	@PostMapping("/allowance/{id}")
	public String updateallowance(@PathVariable("id") int id,
			@ModelAttribute("allowance") AllowanceMaster allowancemaster) {
		try {
			AllowanceMaster existingAllowance = allowanceService.findAllowanceById(id);

			if (existingAllowance == null) {
				logger.error("Allowance with ID " + id + " not found.");
			} else {
				allowancemaster.setAllowanceId(id); // Set the ID in case it is not provided in the form data
				allowanceService.updateAllowance(allowancemaster);
			}
		} catch (Exception e) {
			logger.error("Failed to update allowance with ID " + id, e);
			e.printStackTrace();
		}

		return "redirect:/allowances";
	}

	@GetMapping("/allowance/{id}")
	public String deleteAllowance(@PathVariable("id") int id) {
		try {
			AllowanceMaster existingAllowance = allowanceService.findAllowanceById(id);

			if (existingAllowance == null) {
				logger.error("Allowance with ID " + id + " not found.");
			} else {
				allowanceService.deleteAllowance(id);
			}
		} catch (Exception e) {
			logger.error("Failed to delete allowance with ID " + id, e);
			e.printStackTrace();
		}

		return "redirect:/allowances";
	}

	@GetMapping("/allowances/view/{id}")
	public String viewAllowance(@PathVariable("id") int id, Model model) {
		AllowanceMaster allowance = allowanceService.findAllowanceById(id);
		if (allowance == null) {
			// Handle the case where the allowance with the given id does not exist
			return "redirect:/allowances";
		}
		model.addAttribute("allowance", allowance);
		return "/Allowance/ViewAllowance";
	}

}
