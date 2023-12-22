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

import com.HRMS.model.DeductionMaster;
import com.HRMS.model.EmpDeductionMaster;
import com.HRMS.model.EmployeeMaster;
import com.HRMS.service.DeductionService;
import com.HRMS.service.EmpAllowanceService;
import com.HRMS.service.EmpDeductionService;
import com.HRMS.service.EmployeeService;

@Controller
public class EmpDeductionController {

	@Autowired
	private DeductionService deductionservice;

	@Autowired
	private EmpDeductionService empDeductionService;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmpAllowanceService empAllowanceService;

	@GetMapping("/getEmpDeduction")
	public String showEmployeeIdForm() {
		return "EmpDeduction/EmpDeductionID";
	}

	@GetMapping("/getEmpDeduction/{id}")
	public String getEmployeeDeduction(@PathVariable("id") long id, Model model,
			RedirectAttributes redirectAttributes) {

		EmployeeMaster employee = employeeService.findById(id);

		if (employee != null) {
			List<EmpDeductionMaster> employeeDeductions = empDeductionService.getEmployeeDeductionsById(id);

			model.addAttribute("employee", employee);
			model.addAttribute("employeeDeductions", employeeDeductions);

			return "EmpDeduction/EmpDeductionMaintenance";// Assuming "employeeAllowances" is your HTML template
															// for displaying allowances.
		} else {
			// Handle the case when the employee does not exist
			model.addAttribute("errorMessage", "Employee not found");
			return "EmpDeduction/EmpDeductionMaintenance"; // You should have an error template for displaying the
															// error message.
		}
	}

	@GetMapping("empDeduction/new/{empid}")
	public String createNewForm(@PathVariable("empid") long empid, Model model) {
		EmpDeductionMaster empdeduction = new EmpDeductionMaster();

	    // Get all deductions
	    List<DeductionMaster> allDeductions = deductionservice.getAllDeduction();

	    // Get the employee's assigned deductions
	    List<EmpDeductionMaster> allAssignedDeductions = empDeductionService.getEmployeeDeductionsById(empid);

	    // Extract the DeductionMaster objects from the assigned deductions
	    List<DeductionMaster> assignedDeductions = allAssignedDeductions.stream()
	            .map(EmpDeductionMaster::getDeduction)
	            .collect(Collectors.toList());

	    // Create a HashSet of assigned deductions for faster lookups
	    Set<DeductionMaster> assignedDeductionsSet = new HashSet<>(assignedDeductions);

	    // Filter out assigned deductions from all deductions
	    List<DeductionMaster> unassignedDeductions = allDeductions.stream()
	            .filter(deduction -> !assignedDeductionsSet.contains(deduction))
	            .collect(Collectors.toList());
	    
	    EmployeeMaster employee = new EmployeeMaster();
	    employee.setEmpId(empid);
	    empdeduction.setEmployee(employee);
	    
		model.addAttribute("empdeduction", empdeduction);
		model.addAttribute("Alldeductions", unassignedDeductions);
		return "EmpDeduction/EmpDeductionAdd";
	}

	@PostMapping("/empDeduction")
	public String assignDeduction(@ModelAttribute("empdeduction") EmpDeductionMaster empdeduction,
			@RequestParam("empId") long empId) {
		// Set the employee for the EmpAllowanceMaster object
		DeductionMaster deductionData = deductionservice
				.findDeductionById(empdeduction.getDeduction().getDeductionId());
		empdeduction.setDeduction(deductionData);
		EmployeeMaster employee = employeeService.findById(empId);
		empdeduction.setEmployee(employee);

		empDeductionService.saveEmployee(empdeduction);

		// Redirect to the main page that displays all details for the employee
		return "redirect:/getEmpDeduction/" + empId;
	}

	@GetMapping("/empDeduction/edit/{empid}")
	public String editEmpDeduction(@PathVariable("empid") long empid, Model model) {

		EmpDeductionMaster empdeduction = new EmpDeductionMaster();
		List<DeductionMaster> deductions = deductionservice.getAllDeduction();
		EmployeeMaster employee = new EmployeeMaster();
		employee.setEmpId(empid);

		empdeduction.setEmployee(employee);
		model.addAttribute("empDeduction", empdeduction);
		model.addAttribute("Alldeduction", deductions);

		return "/EmpDeduction/EmpDeductionEdit";
	}

	@GetMapping("/empDeduction/edit/{empId}/{deductionId}")
	public String editEmpDeductionForm(@PathVariable("empId") long empId, @PathVariable("deductionId") long deductionId,
			Model model) {

		EmpDeductionMaster editWithDeductionidAndEmployeeid = empDeductionService
				.getDataWithDeductionidAndEmployeeid(deductionId, empId);
		
		double calculatedPercentage = empAllowanceService.calculatePercentage(editWithDeductionidAndEmployeeid.getAmount(), editWithDeductionidAndEmployeeid.getEmployee().getEmpId());
		editWithDeductionidAndEmployeeid.setAmount(calculatedPercentage);
		model.addAttribute("employeedata", editWithDeductionidAndEmployeeid);
		return "/EmpDeduction/EmpDeductionEdit";
	}

	@PostMapping("/editEmpDeduction")
	public String editEmployeeData(@ModelAttribute("employeedata") EmpDeductionMaster employeedata) {

		DeductionMaster deductionData = deductionservice
				.findDeductionById(employeedata.getDeduction().getDeductionId());
		employeedata.setDeduction(deductionData);
		EmployeeMaster employee = employeeService.findById(employeedata.getEmployee().getEmpId());
		employeedata.setEmployee(employee);

		EmpDeductionMaster editedEmployeeData = empDeductionService.editEmployeeData(employeedata);

		return "redirect:/getEmpDeduction/" + editedEmployeeData.getEmployee().getEmpId();
	}

	@GetMapping("/empDeduction/delete/{id}")
	public String deleteDeduction(@PathVariable("id") long id, @RequestParam("empId") long empId) {
		EmpDeductionMaster existingDeduction = empDeductionService.findById(id);
		try {

			if (existingDeduction == null) {
			} else {
				empDeductionService.deleteEmpDeduction(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/getEmpDeduction/" + empId;
	}
}
