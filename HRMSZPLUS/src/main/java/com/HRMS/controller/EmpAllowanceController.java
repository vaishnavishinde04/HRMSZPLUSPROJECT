package com.HRMS.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HRMS.model.AllowanceMaster;
import com.HRMS.model.EmpAllowanceMaster;
import com.HRMS.model.EmployeeMaster;
import com.HRMS.service.AllowanceService;
import com.HRMS.service.EmpAllowanceService;
import com.HRMS.service.EmployeeService;

@Controller
public class EmpAllowanceController {

	@Autowired
	private AllowanceService allowanceservice;

	@Autowired
	private EmpAllowanceService empAllowanceService;

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/getEmpAllowance")
	public String showEmployeeIdForm() {
		return "EmpAllowance/EmpAllowanceID";

	}

	@GetMapping("/getEmpAllowance/{empId}")
	public String getEmployeeAllowances(@PathVariable("empId") long id, Model model,
			RedirectAttributes redirectAttributes) {

		EmployeeMaster employee = employeeService.findById(id);

		if (employee != null) {
			// If the employee exists, retrieve their allowances
			List<EmpAllowanceMaster> employeeAllowances = empAllowanceService.getEmployeeAllowancesById(id);

			model.addAttribute("employee", employee);
			model.addAttribute("employeeAllowances", employeeAllowances);

			return "EmpAllowance/EmpAllowanceMaintenance";// Assuming "employeeAllowances" is your HTML template
															// for displaying allowances.
		} else {
			// Handle the case when the employee does not exist
			model.addAttribute("errorMessage", "Employee not found");
			return "EmpAllowance/error"; // You should have an error template for displaying the
											// error message.
		}
	}

	@GetMapping("empAllowance/new/{empid}")
	public String createNewForm(@PathVariable("empid") long empid, Model model) {
		EmpAllowanceMaster empallowance = new EmpAllowanceMaster();
		
		// Get all allowances
	    List<AllowanceMaster> allAllowances = allowanceservice.getAllAllowances();
	    
	    // Get the employee's assigned allowances
	    List<EmpAllowanceMaster> allAssignedAllowances = empAllowanceService.getEmployeeAllowancesById(empid);

	    // Extract the AllowanceMaster objects from the assigned allowances
	    List<AllowanceMaster> assignedAllowances = allAssignedAllowances.stream()
	            .map(EmpAllowanceMaster::getAllowance)
	            .collect(Collectors.toList());

	    // Create a HashSet of assigned allowances for faster lookups
	    Set<AllowanceMaster> assignedAllowancesSet = new HashSet<>(assignedAllowances);

	    // Filter out assigned allowances from all allowances
	    List<AllowanceMaster> unassignedAllowances = allAllowances.stream()
	            .filter(allowance -> !assignedAllowancesSet.contains(allowance))
	            .collect(Collectors.toList());

	    EmployeeMaster employee = new EmployeeMaster();
	    employee.setEmpId(empid);

		empallowance.setEmployee(employee);
		model.addAttribute("empallowance", empallowance);
		model.addAttribute("Allallowances", unassignedAllowances);
		return "/EmpAllowance/EmpAllowanceAdd";
	}

	@PostMapping("/empAllowance")
	public String assignAllowance(@ModelAttribute("empallowance") EmpAllowanceMaster empallowance,
			@RequestParam("empId") long empId) {

		AllowanceMaster allowance = allowanceservice.findAllowanceById(empallowance.getAllowance().getAllowanceId());
		empallowance.setAllowance(allowance);
		// Set the employee for the EmpAllowanceMaster object
		EmployeeMaster employee = employeeService.findById(empId);
		empallowance.setEmployee(employee);
		empAllowanceService.saveEmployee(empallowance);

		// Redirect to the main page that displays all details for the employee
		return "redirect:/getEmpAllowance/" + empId;
	}

	@GetMapping("/empAllowance/edit/{empId}/{allowanceId}")
	public String editEmpAllowanceForm(@PathVariable("empId") long empId, @PathVariable("allowanceId") long allowanceId,
			Model model) {

		EmpAllowanceMaster editWithAllowanceidAndEmployeeid = empAllowanceService
				.getDataWithAllowanceidAndEmployeeid(allowanceId, empId);
		if(editWithAllowanceidAndEmployeeid.getAllowance().getAllowanceName().equals("Basic"))
		{
			model.addAttribute("employeedata", editWithAllowanceidAndEmployeeid);
			return "/EmpAllowance/EmpAllowanceEdit";
		}
		double calculatedPercentage = empAllowanceService.calculatePercentage(editWithAllowanceidAndEmployeeid.getAmount(),editWithAllowanceidAndEmployeeid.getEmployee().getEmpId());
		editWithAllowanceidAndEmployeeid.setAmount(calculatedPercentage);
		model.addAttribute("employeedata", editWithAllowanceidAndEmployeeid);
		return "/EmpAllowance/EmpAllowanceEdit";
	}

	@PostMapping("editEmpAllowance")
	public String editEmployeeData(@ModelAttribute("employeedata") EmpAllowanceMaster employeedata) {

		AllowanceMaster allowance = allowanceservice.findAllowanceById(employeedata.getAllowance().getAllowanceId());
		employeedata.setAllowance(allowance);
		// Set the employee for the EmpAllowanceMaster object
		EmployeeMaster employee = employeeService.findById(employeedata.getEmployee().getEmpId());
		employeedata.setEmployee(employee);

		empAllowanceService.editEmployeeData(employeedata);

		return "redirect:/getEmpAllowance/" + employeedata.getEmployee().getEmpId();
	}

	@GetMapping("/empAllowance/delete/{id}")
	public String deleteAllowance(@PathVariable("id") long id, @RequestParam("empId") long empId) {
		EmpAllowanceMaster existingAllowance = empAllowanceService.findById(id);
		try {

			if (existingAllowance == null) {
				// Handle the case when allowance with ID not found.
			} else {
				empAllowanceService.deleteEmpAllowance(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Redirect to the main page that displays all details for the employee
		// (getEmployeeAllowances)
		return "redirect:/getEmpAllowance/" + empId;
	}

	@GetMapping("/getEmpAllowance/view/{id}")
	public String viewEmployeeAllowance(@PathVariable("id") long id, Model model) {
		// Retrieve the details of the specific allowance by id
		EmpAllowanceMaster empAllowance = empAllowanceService.getAllowanceById(id);

//	    EmployeeMaster employee = employeeService.findById();
		// Add the allowance details to the model
		model.addAttribute("allowance", empAllowance);

		// Return the view page (e.g., "viewEmpAllowance")
		return "EmpAllowance/EmpAllowanceView";
	}

}
