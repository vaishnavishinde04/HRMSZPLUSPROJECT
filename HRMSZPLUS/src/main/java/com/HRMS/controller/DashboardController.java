package com.HRMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
	
	@GetMapping("/dashboard")
	public String showDashboard()
	{
		
		//so employee master is not completed yet so we are partially stopping here
		
		//once it is created create object of loginDAO get all data by accessing it using logedinuser session
		//acccoring to role redirect them
		//also create employee dao and access employee information using emp id
		//use it to show who has logged in...
		return "/Dashboard/index";
	}
}
