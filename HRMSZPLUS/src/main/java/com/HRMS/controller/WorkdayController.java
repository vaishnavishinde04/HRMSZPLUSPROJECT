package com.HRMS.controller;

import java.util.Arrays;
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

import com.HRMS.model.WorkdayMaster;
import com.HRMS.service.WorkdayService;

@Controller
public class WorkdayController {
	private static final Logger log = LoggerFactory.getLogger(WorkdayController.class);

	@Autowired
	private WorkdayService workdayService;

	@GetMapping("/workdays")
	public String getWorkdays(Model model) {
		model.addAttribute("workdays", new WorkdayMaster());

		return "/Workday/SelectYear";

	}

	@GetMapping("/workdays/new/{year}")
	public String createWorkdayForm(@PathVariable("year") int year,Model model) {
		WorkdayMaster w=new WorkdayMaster();
		w.setYearOfWorkday(year);
		model.addAttribute("workday", w);

		return "Workday/NewWorkday";
	}

	@PostMapping("/workdays")
	public String addWorkday(@ModelAttribute("workday") WorkdayMaster workdaymaster) {

		WorkdayMaster workday = workdayService.saveWorkday(workdaymaster);
		if (workday == null) {
			log.error("Unable to Save Data");
			return "redirect:/workdays";
		}

		return "redirect:/workdays";
	}

	@GetMapping("/workday/edit/{id}")
	public String editWorkday(@PathVariable("id") int id, Model model) {
		WorkdayMaster workday = workdayService.findWorkdayById(id);

		if (workday == null) {
			log.error("Workday with ID " + id + " not found.");
			return "redirect:/workday";
		}
		List<String> selectedDaysOfWeek = Arrays.asList(workday.getSelectedDaysOfWeek().split(","));

		model.addAttribute("selectedDaysOfWeek", selectedDaysOfWeek);

		model.addAttribute("workday", workday);
		return "/Workday/editWorkday";
	}

	@PostMapping("/workday/{id}")
	public String updateworkday(@PathVariable("id") int id, @ModelAttribute("workday") WorkdayMaster workdaymaster) {
		System.out.print("");

		try {
			WorkdayMaster existingWorkday = workdayService.findWorkdayById(id);

			if (existingWorkday == null) {
				log.error("Workday with id " + id + " not found.");
				return "redirect:/workdays";
			} else {
				workdaymaster.setWorkdayId(id);
				workdayService.updateWorkday(workdaymaster);
				return "redirect:/workdays";

			}
		} catch (Exception e) {
			log.error("Failed to update workday with id " + id, e);
			e.printStackTrace();
			return "redirect:/workdays";
		}

	}

	@GetMapping("/workday/{id}")
	public String deleteWorkday(@PathVariable("id") int id) {
		try {
			WorkdayMaster existngWorkday = workdayService.findWorkdayById(id);

			if (existngWorkday == null) {
				log.error("Workday with ID " + id + " not found.");
			} else {
				workdayService.deleteWorkday(id);
			}

		} catch (Exception e) {
			log.error("Failed to delete workday with ID" + id, e);
			e.printStackTrace();
			return "redirect:/workdays";
		}

		return "redirect:/workdays";
	}

	// For view
	@GetMapping("/workday/view/{id}")
	public String viewWorkday(@PathVariable("id") int id, Model model) {
		WorkdayMaster workday = workdayService.findWorkdayById(id);
		if (workday == null) {
			return "redirect:/workday";
		}
		model.addAttribute("workday", workday);
		return "/Workday/ViewWorkday";
	}

	@PostMapping("/allWorkdays/{year}")
	public String getWorkdaysByYear(@PathVariable("year") int year, Model model) {
		List<WorkdayMaster> findByYear = workdayService.findByYear(year);

		if (findByYear.isEmpty()) {
			log.error("No months in year :" + year);
		}
		
		model.addAttribute("selectedYear", year);

		model.addAttribute("workdays", findByYear);
		return "/Workday/WorkdayMaintenance";

	}

}
