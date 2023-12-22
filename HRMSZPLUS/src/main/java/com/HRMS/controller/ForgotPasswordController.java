package com.HRMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.HRMS.model.LoginMaster;
import com.HRMS.model.OtpLoginMaster;
import com.HRMS.service.LoginService;
import com.HRMS.service.OtpLoginService;
import com.HRMS.utility.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ForgotPasswordController {
	
	private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordController.class);

    @Autowired
    private LoginService loginService; // Inject your user service

    
    @Autowired
    private OtpLoginService otpLoginService;
	    
	    @GetMapping("/login/forgot_password")
	    public String getUsername(Model model) {
	    	model.addAttribute("user", new LoginMaster());
	        return "/Login/forgotPassword"; 
	    }
	    
	
	    @PostMapping("/forgot_password")
	    public String checkUser(@ModelAttribute("user") LoginMaster loginmaster , Model model, HttpSession session) {
	    	
	    	// Validate username or email
	                 LoginMaster user = loginService.findByUsername(loginmaster.getUsername());
	                 session.setAttribute("loggedInUsername", loginmaster.getUsername());

	                 if (user != null) {
	                     otpLoginService.generateAndSaveOtp(loginmaster.getUsername());
	                     model.addAttribute("successMessage", "OTP has been sent to your registered email.");
	                     return "/Login/forgotPasswordOtp";
	                 } else {
	                     model.addAttribute("errorMessage", "User not found. Please check the username.");
	                     return "/Login/forgotPassword";
	                 }
	    
	     
	    }
	    @GetMapping("/verify-otp")
	    public String getOtp(Model model, HttpSession session ) {
	        model.addAttribute("userOtp", new OtpLoginMaster());
	        String loggedInUsername = (String) session.getAttribute("loggedInUsername");
			
			if(otpLoginService.CheckLockedUser(loggedInUsername))
			{
				model.addAttribute("AccountLocked", "Account Has Been Locked Due To Suspicious Activity");
				model.addAttribute("login", new LoginMaster());
			    return "redirect:/login";
			}
			
			if (session.getAttribute("loggedInUsername") == null) {
	            return "redirect:/login";
	        }
			
			boolean otpAlreadyPresent = otpLoginService.isOtpAlreadyPresent(loggedInUsername);
			
			if(otpAlreadyPresent)
			{
				model.addAttribute("otpAlreadyPresent", otpAlreadyPresent);
				return "/Login/forgotPasswordOtp";
			}
			
			model.addAttribute("otpsent", true);
			otpLoginService.generateAndSaveOtp(loggedInUsername);
			model.addAttribute("otp", new OtpLoginMaster());
			return "/Login/forgotPasswordOtp";
	    }
	    
	    @PostMapping("/verify-otp")
	    public String verifyOtp(@ModelAttribute("userOtp") OtpLoginMaster otploginmaster, Model model, HttpSession session) {

	    	String loggedInUsername = (String) session.getAttribute("loggedInUsername");
			boolean otpVerified = otpLoginService.verifyOtp(loggedInUsername,otploginmaster.getPin());
			if (otpVerified) {
				
				otpLoginService.resetFailedAttemp(loggedInUsername);
				//session.setAttribute("otpVerifiedUser", loggedInUsername);
				System.out.print("User Logged in is :"+session.getAttribute("loggedInUsername"));

				
				return "redirect:/login/reset_password";
			} else {
				
				otpLoginService.recordFailedAttempt(loggedInUsername);
				
				if(otpLoginService.CheckLockedUser(loggedInUsername))
				{
					model.addAttribute("AccountLocked", "Account Has Been Locked Due To Suspicious Activity");
					model.addAttribute("login", new LoginMaster());
				    return "/Login/login";
				}
				// If the OTP is incorrect, show the error message on the OTP page
				model.addAttribute("errorMessage", "Incorrect OTP! Please try again.");
				return "/Login/forgotPasswordOtp";
				}
	    }
	    
	    @GetMapping("/login/reset_password")
	    public String editPassword(Model model, HttpSession session) {
	        // Get the username from the session
	        String username = (String) session.getAttribute("loggedInUsername");
	        if (username == null) {
	            // Handle the case where the username is not in the session
	            return "redirect:/login"; // Redirect to the login page or handle as needed
	        }

	        // Load the user details based on the username
	        LoginMaster loginmaster = loginService.findByUsername(username);

//	        if (loginmaster == null) {
//	            logger.error("User with username " + username + " not found.");
//	            return "redirect:/"; // Redirect to a relevant page
//	        }

	        loginmaster.setPassword(null);
	        model.addAttribute("userDetails", loginmaster);
	        return "/Login/reset_password"; // Display the password edit form
	    }

	    @PostMapping("/resetPassword")
	    public String editPasswordSubmit(@ModelAttribute("userDetails") LoginMaster userDetails, HttpSession session) {
	        try {
	            // Get the username from the session
	            String username = (String) session.getAttribute("loggedInUsername");
	            if (username == null) {
	                // Handle the case where the username is not in the session
	                return "redirect:/login"; // Redirect to the login page or handle as needed
	            }

	            // Load the user details based on the username
	            LoginMaster loginMaster = loginService.findByUsername(username);
	            System.out.println(""+username);

	            if (loginMaster == null) {
	            	logger.error("User with username " + username + " not found.");
	                return "/Login/forgotPassword"; // Redirect to a relevant page
	            } else {
	                // Update the user's password
	            	System.out.println(""+userDetails.getPassword());
	            	loginMaster.setPassword(userDetails.getPassword());
	                loginService.updateLogins(loginMaster);
	               otpLoginService.deleteOtpByUsername(username);
	                return "redirect:/login"; // Redirect to a relevant page after updating the password
	            }

	        } catch (Exception e) {
	           // logger.error("Failed to update the password for user with username " + username, e);
	            e.printStackTrace();
	            return "/Login/forgotPassword"; // Redirect to a relevant page in case of an error
	        }
	    }


}
