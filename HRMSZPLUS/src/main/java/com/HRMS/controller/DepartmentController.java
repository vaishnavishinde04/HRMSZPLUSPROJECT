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

import com.HRMS.model.DepartmentMaster;
import com.HRMS.service.DepartmentService;

@Controller
public class DepartmentController {

	private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService departmentservice;

	@GetMapping("/departments")
	public String getAllDepartments(Model model) {
		List<DepartmentMaster> findAlldepartments = departmentservice.findAllDepartments();
		if (findAlldepartments.isEmpty()) {
			log.error("No Deparments Available To Retrieve");
			return "/Department/departmentMaintenance";
		}

		model.addAttribute("departments", findAlldepartments);
		return "/Department/departmentMaintenance";
	}

	@GetMapping("/department/new")
	public String createdepartmentForm(Model model) {
		model.addAttribute("department", new DepartmentMaster());
		return "/department/NewDepartment";
	}

	@PostMapping("/department")
	public String adddepartment(@ModelAttribute("department") DepartmentMaster departmentmaster) {
		DepartmentMaster adddepartment = departmentservice.addDepartment(departmentmaster);
		if (adddepartment == null) {
			log.error("Unable To Save Data");
		}
		return "redirect:/departments";
	}

	@GetMapping("/department/edit/{id}")
	public String EditdepartmentForm(Model model, @PathVariable("id") int id) {
		DepartmentMaster findById = departmentservice.findById(id);
		model.addAttribute("department", findById);
		return "/department/editdepartment";
	}

	@PostMapping("/department/{id}")
	public String Editdepartment(@ModelAttribute("department") DepartmentMaster department, @PathVariable("id") int id,
			RedirectAttributes redirectAttributes) {
		DepartmentMaster adddepartment = departmentservice.updateDepartment(department, id);
		if (adddepartment == null) {
			log.error("Unable To Save Data");
			redirectAttributes.addAttribute("id", id);
			return "redirect:/department/edit/{id}";
		}
		return "redirect:/departments";
	}

	@GetMapping("/department/delete/{id}")
	public String deletedepartment(@PathVariable("id") int id) {
		DepartmentMaster findById = departmentservice.findById(id);

		if (findById == null) {
			log.error("Unable to Find department with Id" + id);
			return "redirect:/departments";
		} else {
			departmentservice.deleteDepartment(id);
			return "redirect:/departments";
		}
	}
}
