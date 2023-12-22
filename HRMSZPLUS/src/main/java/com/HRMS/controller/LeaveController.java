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
import org.springframework.web.bind.annotation.RequestParam;

import com.HRMS.model.LeaveMaster;
import com.HRMS.service.LeaveService;



@Controller
public class LeaveController {

	private static final Logger Log = LoggerFactory.getLogger(LeaveController.class);
	
	@Autowired
	private LeaveService leaveService;
	
	@GetMapping("/leaves")
	public String getLeaves(Model model)
	{
		List<LeaveMaster> leaves = leaveService.getAllLeaves();
		if(leaves.isEmpty())
		{
			Log.error("NO leaves available to retrive");
			return "/Leave/LeaveMaintenance";
		}
		else
		{
			model.addAttribute("leaves",leaves);
			return "/Leave/LeaveMaintenance";
			
		}
	}
	
	
	@GetMapping("/leaves/new")
	public String createLeaveForm(Model model)
	{
		LeaveMaster l=new LeaveMaster();
		l.setEmployeeId(1);
		model.addAttribute("leave",l);
		return "/Leave/LeaveApply";
	}
	
	@PostMapping("/leaves")
	public String addLeaves(@ModelAttribute("leave") LeaveMaster leavemaster) {
		leavemaster.setLeaveStatus("pending");
		LeaveMaster leave = leaveService.saveLeave(leavemaster);
		if(leave == null)
		{
			Log.error("Unable to save data");
			return "redirect:/leaves";
		}
		
		return "redirect:/leaves";
	}
	
	@GetMapping("leaves/edit/{id}")
	public String editLeave(@PathVariable("id") int id,Model model)
	{
		LeaveMaster leave = leaveService.findLeaveById(id);
		
		if(leave == null)
		{
			Log.error("Allowance with ID " + id + " not found.");
			return "redirect:/leaveMaintenance";
		}
		model.addAttribute("leaves" , leave);
		
		return "Leave/LeaveMaintenanceAction";
	}
	
	@PostMapping("/leave/{id}")
	public String updateLeave(@PathVariable("id") int id,
			@ModelAttribute("leave") LeaveMaster leavemaster) {
		try {
			LeaveMaster existingLeave = leaveService.findLeaveById(id);
			
			if(existingLeave == null)
			{
				Log.error("Leave with ID \" + id + \" not found.");
			}
			else
			{
				leavemaster.setLeaveId(id);
				leaveService.updateLeave(leavemaster);
			}
		}catch(Exception e)
		{
			Log.error("Failed to update leave with ID " + id, e);
			e.printStackTrace();
			return "redirect:/leaves";
		}
		
		return "redirect:/leaves";
	}
	
	@GetMapping("/leave/{id}")
	public String deleteLeave(@PathVariable("id") int id) {
		try {
			LeaveMaster existingLeave = leaveService.findLeaveById(id);

			if (existingLeave == null) {
				Log.error("Leave with ID " + id + " not found.");
			} else {
				leaveService.deleteLeave(id);
			}
		} catch (Exception e) {
			Log.error("Failed to delete leave with ID " + id, e);
			e.printStackTrace();
			return "redirect:/leaves";
		}

		return "redirect:/leaves";
	}
	
	
	@PostMapping("/leaves/allow/{id}")
	public String allowLeave(@PathVariable("id") int leaveId,
	                         @RequestParam("leaveType") String leaveType) {
	    LeaveMaster leave = leaveService.findLeaveById(leaveId);

	    if (leave != null) {
	        leave.setLeaveStatus("Allowed");
	        leave.setLeaveType(leaveType);
	        leaveService.updateLeave(leave);

	        // Use the 'leaveType' value as needed
	    } else {
	        Log.error("Leave with ID " + leaveId + " not found.");
	    }

	    return "redirect:/leaves"; // Redirect back to the leave maintenance page
	}



	

}