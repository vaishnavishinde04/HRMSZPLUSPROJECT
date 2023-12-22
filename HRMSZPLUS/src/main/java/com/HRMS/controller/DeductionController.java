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

import com.HRMS.model.DeductionMaster;
import com.HRMS.service.DeductionService;


@Controller
public class DeductionController {
   
	private static final Logger logger = LoggerFactory.getLogger(DeductionController.class);
	
	@Autowired
	private DeductionService deductionService;
	
	@GetMapping("/deductions")
	public String getAllDeduction(Model model) {
		
		 List<DeductionMaster> deductions = deductionService.getAllDeduction();
		 if(deductions.isEmpty()) {
			 logger.error("No Deduction Avilable To Retrieve");
			 return"/Deduction/DeductionMaintenance";
		 }
		 model.addAttribute("deduction",deductions);
		 return "/Deduction/DeductionMaintenance";
	}
	
	@GetMapping("/deductions/new")
	public String createDeductionForm(Model model) {
		model.addAttribute("deduction", new DeductionMaster());
		return "/Deduction/NewDeduction";
		
	}
	
	@PostMapping("/deductions")
	public String addDeduciton(@ModelAttribute("deduction") DeductionMaster deductionmaster) {
		
		DeductionMaster deduction =deductionService.saveDeduciton(deductionmaster);
		if(deduction ==null) {
			logger.error("Unable to Save DATA");
//			return "redirect:/deductions";
		}
		return "redirect:/deductions";
	}
	
	@GetMapping("/deduction/edit/{id}")
	public String editDeduciton(@PathVariable("id") int  id, Model model) {
		DeductionMaster deduction = deductionService.findDeductionById(id);
		
		if(deduction==null) {
			logger.error("Deduction with ID "+id+"not Found.");
			return "redirect:/deductions";
		}
		model.addAttribute("deduction",deduction);
		return "/Deduction/EditDeduction";
	}
	
	@PostMapping("/deduction/{id}")
	public String updateDeduction(@PathVariable("id") int id, @ModelAttribute("deduction") DeductionMaster deductionMaster) {
		try {
			DeductionMaster existingDeduction= deductionService.findDeductionById(id);
			
			if(existingDeduction == null) {
				logger.error("Deduction with ID"+ id +"not FOUND.");
			}else {
				deductionMaster.setDeductionId(id); 
				deductionService.updateDeduction(deductionMaster);
			}
		}catch (Exception e) {
			logger.error("Failed to update deduction with ID " + id, e);
			e.printStackTrace();
			return "redirect:/deductions";
		}
		return "redirect:/deductions";
		
	}
	
	@GetMapping("/deduction/{id}")
	public String deleteDeduction(@PathVariable("id") int id) {
		try {
			DeductionMaster existingDeduction = deductionService.findDeductionById(id);

			if (existingDeduction == null) {
				logger.error("Deduction with ID " + id + " not found.");
			} else {
				deductionService.deleteDeduction(id);
			}
		} catch (Exception e) {
			logger.error("Failed to delete deduction with ID " + id, e);
			e.printStackTrace();
			return "redirect:/deductions";
		}

		return "redirect:/deductions";
	}

	@GetMapping("/deductions/view/{id}")
	public String viewDeduction(@PathVariable("id") int id, Model model) {
		DeductionMaster deduction = deductionService.findDeductionById(id);
		if (deduction == null) {
			return "redirect:/deductions";
		}
		model.addAttribute("deduction", deduction);
		return "/Deduction/ViewDeduction";
	}

	
	   
}
