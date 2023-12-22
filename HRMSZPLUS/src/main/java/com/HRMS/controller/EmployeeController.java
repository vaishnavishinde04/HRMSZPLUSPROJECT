package com.HRMS.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;

import com.HRMS.model.BankMaster;
import com.HRMS.model.DepartmentMaster;
import com.HRMS.model.DesignationMaster;
import com.HRMS.model.EmployeeMaster;
import com.HRMS.service.BankService;
import com.HRMS.service.DepartmentService;
import com.HRMS.service.DesignationService;
import com.HRMS.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeservice;
	
	@Autowired
	private DepartmentService departmentservice;
	
	@Autowired
	private DesignationService designationservice;
	
	@Autowired
	private BankService bankservice;
	
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	
	@GetMapping("/employees")
	public String getAll(Model model)
	{
		List<EmployeeMaster> allEmployees = employeeservice.getAllEmployees();
		if(employeeservice!=null)
		{
			model.addAttribute("employees",allEmployees);
			return "Employee/EmployeeMaintenance";
		}
		else
		{
			log.error("No Data Avilable For Retrival");
			return "Employee/EmployeeMaintenance";
		}
	}
	
	@GetMapping("/employee/new")
	public String createNewForm(Model model)
	{
		List<EmployeeMaster> allEmployees = employeeservice.getAllEmployees();
		List<DepartmentMaster> allDepartments = departmentservice.findAllDepartments();
		List<DesignationMaster> allDesignations = designationservice.findAllDesignations();
		List<BankMaster> allBanks = bankservice.findAllBanks();
		
		model.addAttribute("allEmployees",allEmployees);
		model.addAttribute("allDepartments",allDepartments);
		model.addAttribute("allDesignations",allDesignations);
		model.addAttribute("allbanks",allBanks);
		model.addAttribute("employee",new EmployeeMaster());
		
		return "Employee/newEmployee";
	}
	
	@PostMapping("/employee")
	public String addemp(@ModelAttribute("employee") EmployeeMaster employee, @RequestParam("resumeFile") MultipartFile resumeFile, @RequestParam("profilePhoto") MultipartFile profilePhoto) {
		
		if (!resumeFile.isEmpty()) {
		    String originalFileName =employee.getEmpName().charAt(0)+"_"+employee.getEmpName()+"_"+resumeFile.getOriginalFilename();
		    String directoryPath = "resumes/"; 
		    try {
		        Files.createDirectories(Paths.get(directoryPath));
		        String filePath = directoryPath + originalFileName;
		        Path path = Paths.get(filePath);
		        Files.write(path, resumeFile.getBytes());
		        employee.setResumePath(filePath);
		    } catch (IOException e) {
		        e.printStackTrace();
		        return "redirect:/employees";
		    }
		}
		 if (!profilePhoto.isEmpty()) {
		        String originalFileName = profilePhoto.getOriginalFilename();
		        String directoryPath = "profilePhotos/"; // Adjust the path as needed
		        try {
		            Files.createDirectories(Paths.get(directoryPath));
		            String filePath = directoryPath + originalFileName;
		            Path path = Paths.get(filePath);
		            Files.write(path, profilePhoto.getBytes());
		            employee.setProfileImageData(filePath);
		        } catch (IOException e) {
		            e.printStackTrace();
		            return "redirect:/employees";
		        }
		    }
		 
	        EmployeeMaster addEmployee = employeeservice.addEmployee(employee);
	        if (addEmployee != null) {
	            return "redirect:/employees";
	        } else {
	            return "redirect:/employees";
	        }
	    
	}
	
	@GetMapping("/employee/edit/{id}")
	public String createUpdatePage(@PathVariable("id") int id,Model model)
	{
		EmployeeMaster findById = employeeservice.findById(id);
		model.addAttribute("employee",findById);
		return "Employee/editEmployee";
	}
	
	@PostMapping("/employee/edit/{id}")
	public String updateEmployee(@PathVariable("id") int id,@ModelAttribute("employee") EmployeeMaster employee)
	{
		try {
			EmployeeMaster findById = employeeservice.findById(id);
			if(findById==null)
			{
				log.error("Employee with ID " + id + " not found.");
			}
			else
			{
				employee.setEmpId(id);
				employeeservice.updateEmployee(employee);
			}
			
		}
		catch(Exception e)
		{
			log.error("Failed to update employee with ID " + id, e);
			e.printStackTrace();
			return "redirect:/employees";
		}
		
		return "redirect:/employees";
	}


	
	@GetMapping("/employee/delete/{id}")
	public String deleteEmployee(@PathVariable("id") int id)
	{
		boolean deleteEmployee = employeeservice.deleteEmployee(id);
		if(deleteEmployee)
		{
			return "redirect:/employees";
		}
		return "redirect:/employees";
	}

}
