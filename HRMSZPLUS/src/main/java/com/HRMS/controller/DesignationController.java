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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HRMS.model.DesignationMaster;
import com.HRMS.service.DesignationService;

@Controller
public class DesignationController {

	
	private static final Logger log = LoggerFactory.getLogger(DesignationController.class);

	@Autowired
	private DesignationService designationservice;
	
	@GetMapping("/designations")
	public String getAllDesigantions(Model model)
	{
		List<DesignationMaster> findAllDesignations = designationservice.findAllDesignations();
		if(findAllDesignations.isEmpty())
		{
			log.error("No Deisgnation Available To Retrieve");
			return "/Designation/DesignationMaintenance";
		}
		
		model.addAttribute("designations",findAllDesignations);
		return "/Designation/DesignationMaintenance";
	}
	
	
	@GetMapping("/designation/new")
	public String createDesignationForm(Model model)
	{
		model.addAttribute("designation", new DesignationMaster());
		return "/Designation/NewDesignation";
	}
	
	
	@PostMapping("/designation")
	public String addDesignation(@ModelAttribute("designation") DesignationMaster designationmaster)
	{
		DesignationMaster addDesignation = designationservice.addDesignation(designationmaster);
		if(addDesignation==null)
		{
			log.error("Unable To Save Data");
		}
		return "redirect:/designations";
	}
	
	
	@GetMapping("/designation/edit/{id}")
	public String EditDesignationForm(Model model,@PathVariable("id") int id)
	{
		DesignationMaster findById = designationservice.findById(id);
		model.addAttribute("designation",findById);
		return "/Designation/editDesignation";
	}
	
	@PostMapping("/designation/{id}")
	public String EditDesignation(@ModelAttribute("designation") DesignationMaster designation,@PathVariable("id") int id,RedirectAttributes redirectAttributes)
	{
		DesignationMaster addDesignation = designationservice.updateDesignation(designation,id);
		if(addDesignation==null)
		{
			log.error("Unable To Save Data");
			redirectAttributes.addAttribute("id", id);
			return "redirect:/designation/edit/{id}";
		}
		return "redirect:/designations";
	}
	
	
	@GetMapping("/designation/delete/{id}")
	public String deleteDesignation(@PathVariable("id") int id)
	{
		DesignationMaster findById = designationservice.findById(id);
		
		if(findById==null)
		{
			log.error("Unable to Find Designation with Id"+id);
			return "redirect:/designations";
		}
		else
		{
			designationservice.deleteDesignation(id);
			return "redirect:/designations";
		}
	}
}
