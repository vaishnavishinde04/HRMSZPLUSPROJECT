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

import com.HRMS.model.HolidayMaster;
import com.HRMS.service.HolidayService;

@Controller
public class HolidayController {
	
	private static final Logger log = LoggerFactory.getLogger(HolidayController.class); //log4j aditi push

	@Autowired
	private HolidayService holidayService;
	
	@GetMapping("/holidays")
	public String getHolidays(Model model) {
		
		List<HolidayMaster> holidays = holidayService.getAllHolidays();
		if(holidays.isEmpty())
		{
			log.error("NO Holiday Avilable To Retrieve");
			return "/Holiday/HolidayMaintenance";
		}
		model.addAttribute("holidays",holidays);
		return "/Holiday/HolidayMaintenance";
		
	}
	
	
	@GetMapping("/holidays/new")
	public String createHolidayForm(Model model)
	{
		model.addAttribute("holiday", new HolidayMaster());
		
		return "/Holiday/NewHoliday";
         
	}
	
	
	//For new holidays
	@PostMapping("/holidays")
	public String addHoliday(@ModelAttribute("holiday") HolidayMaster holidaymaster) {
		System.out.print("sssssss"+holidaymaster);
		HolidayMaster holiday = holidayService.saveHoliday(holidaymaster);
		if(holiday == null)
		{
			log.error("Unable to Save Data");
			return "redirect:/holidays";
		}
		
		return "redirect:/holidays";
	}
	
	@GetMapping("/holiday/edit/{id}")
	public String editHoliday(@PathVariable("id") int id,Model model)
	{
		HolidayMaster holiday = holidayService.findHolidayById(id);
		
		if(holiday == null)
		{
			log.error("Holiday with ID " + id + " not found.");
			return "redirect:/holiday";
		}
		
		model.addAttribute("holiday",holiday);
		return "/Holiday/editHoliday";
	}
	
	//for editing specific holiday 
	@PostMapping("/holiday/{id}")
	public String updateholiday(@PathVariable("id") int id,
			@ModelAttribute("holiday") HolidayMaster holidaymaster){
		System.out.print("");
	
		try {
			HolidayMaster existingHoliday = holidayService.findHolidayById(id);
				
			if(existingHoliday == null)
			{
				log.error("Holiday with id " + id + " not found.");
				return "redirect:/holidays";
			}
			else {
				holidaymaster.setHolidayId(id); // Set the ID in case it is not provided in the form data
				holidayService.updateHoliday(holidaymaster);
				return "redirect:/holidays";
				
			}
		}catch(Exception e)
		{
			log.error("Failed to update holiday with id " + id ,e);
			e.printStackTrace();
			return "redirect:/holidays";
		}
		
		
	}
	
	//For find and deleting specific holiday
	@GetMapping("/holiday/{id}")
	public String deleteHoliday(@PathVariable("id") int id) {
		try {
			HolidayMaster existngHoliday = holidayService.findHolidayById(id);
			
			if(existngHoliday == null)
			{
				log.error("Holiday with ID " + id + " not found.");
			}else {
				holidayService.deleteHoliday(id);
			}
			
		}catch(Exception e)
		{
			log.error("Failed to delete holiday with ID" + id,e);
			e.printStackTrace();
			return "redirect:/holidays";
		}
		
		return "redirect:/holidays";
	}
	
	
	//For view 
	@GetMapping("/holiday/view/{id}")
	public String viewHoliday(@PathVariable("id") int id ,Model model)
	{
		HolidayMaster holiday = holidayService.findHolidayById(id);
		if(holiday == null)
		{
			return "redirect:/holiday";
		}
		model.addAttribute("holiday", holiday);
		return "/Holiday/ViewHoliday";
	}
}
