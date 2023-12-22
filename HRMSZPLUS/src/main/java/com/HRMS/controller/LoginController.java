package com.HRMS.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.HRMS.model.EmployeeMaster;
import com.HRMS.model.LoginMaster;
import com.HRMS.service.EmployeeService;
import com.HRMS.service.LoginService;
import com.HRMS.service.OtpLoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private EmployeeService employeeservice;
	
	@Autowired
	private LoginService loginservice;
	
	@Autowired
	private OtpLoginService otploginservice;
	
	public boolean validateUsername(String username, String usernameRegex) {
        return Pattern.matches(usernameRegex, username);
    }

    public boolean validatePassword(String password, String passwordRegex) {
        return Pattern.matches(passwordRegex, password);
    }

	@GetMapping("/login")
	public String showLoginPage(Model model,HttpSession session) {
		
		model.addAttribute("login", new LoginMaster());
		session.invalidate();
		return "/Login/login";
	}
			
	@PostMapping("/login")
	public String checkLogin(@ModelAttribute("login") LoginMaster loginmaster, Model model, HttpSession session) {
		LoginMaster checklogin = loginservice.checklogin(loginmaster);
		
		if(otploginservice.CheckLockedUser(loginmaster.getUsername()))
		{
			model.addAttribute("AccountLocked", "Account Has Been Locked Due To Suspicious Activity");
			model.addAttribute("login", new LoginMaster());
		    return "/Login/login";
		}
		
		if (checklogin != null) {

			session.setAttribute("loggedInUsername", checklogin.getUsername());
			return "redirect:/otp";
			
		} else {
			model.addAttribute("login", new LoginMaster());
			model.addAttribute("errorMessage", "Incorrect username or password.");
			return "/Login/login";
		}

	}
	
	
	@GetMapping("/availableUsers")
	public String getAllUsers(Model model)
	{
		List<LoginMaster> allUsers = loginservice.getAllUser();
		
		if(allUsers.isEmpty())
		{
			log.error("No Users Available");
			return "/Login/LoginMaintenance";
		}
		model.addAttribute("allUsers",allUsers);
		return "/Login/LoginMaintenance";
	}
	
	
	@GetMapping("availableUsers/addUser")
	public String AddnewUser(Model model)
	{
		List<EmployeeMaster> allEmployees = employeeservice.getAllEmployees();
		model.addAttribute("employees",allEmployees);
		model.addAttribute("login",new LoginMaster());
		return "/Login/newUser";
	}
	
	@PostMapping("/newUserAdd")
	public String newUser(@ModelAttribute("login") LoginMaster loginmaster)
	{
		loginservice.newUser(loginmaster);
		return "redirect:/availableUsers";
	}
	
	
	
	@GetMapping("availableUsers/edit/{id}")
	public String editUser(@PathVariable("id") int id,Model model)
	{
		LoginMaster loginmaster=loginservice.findUserLoginsById(id);
		
		if(loginmaster==null)
		{
			log.error("Login Details with ID " + id + " not found.");
			return "redirect:/availableUsers";
		}
		
		loginmaster.setPassword(null);
		model.addAttribute("userDetails",loginmaster);
		return "/Login/EditLoginDetails";
	}
	
	@PostMapping("/editLogin/{id}")
	public String editLogin(@PathVariable("id") int id,@ModelAttribute("userDetails") LoginMaster loginmaster)
	{
		try {
			LoginMaster findUserLoginsById = loginservice.findUserLoginsById(id);
			if(findUserLoginsById==null)
			{
				log.error("No User With Id "+id+" Found");
				return "redirect:/availableUser";
			}
			else
			{
				loginmaster.setUserId(id);
				loginservice.updateLogins(loginmaster);
				return "redirect:/availableUsers";
			}
			
		}
		catch(Exception e)
		{
			log.error("Failed to update Login Details with ID " + id, e);
			e.printStackTrace();
			return "redirect:/availableUsers";
		}
	}
	
	@GetMapping("deleteLogin/{id}")
	public String deleteUser(@PathVariable("id") int id)
	{
		try
		{
			LoginMaster findUserLoginsById = loginservice.findUserLoginsById(id);
			if(findUserLoginsById==null)
			{
				log.error("User with Id:"+id+" Not Found");
				return "redirect:/availableUsers"; 
			}
			else
			{
				loginservice.deleteUser(id);
				return "redirect:/availableUsers";
				
			}
		}
		catch(Exception e)
		{
			log.error("Unable To Delete User With ID :"+id);
			e.printStackTrace();
			return "redirect:/availableUsers";
		}
	}

}
