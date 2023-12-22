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

import com.HRMS.model.BankMaster;
import com.HRMS.service.BankService;

@Controller
public class BankController {

	private static final Logger log = LoggerFactory.getLogger(BankController.class);

	@Autowired
	private BankService bankService;
	
	@GetMapping("/banks")
	public String getAllBanks(Model model) {
		List<BankMaster> banks = bankService.findAllBanks();
		
		if (banks.isEmpty()) {
			log.error("No Banks Available To Retrieve");
			return "Bank/BankMaintenance";
		}
		
		model.addAttribute("banks", banks);
		return "Bank/BankMaintenance";
	}
	
	@GetMapping("/bank/new")
	public String createBankForm(Model model) {
		model.addAttribute("bank", new BankMaster());
		return "Bank/NewBank";
	}
	
	@PostMapping("/bank")
	public String addBank(@ModelAttribute("bank") BankMaster bankMaster) {
		BankMaster addedBank = bankService.addBank(bankMaster);
		
		if (addedBank == null) {
			log.error("Unable To Save Data");
			return "redirect:/bank/new";
		}
		
		return "redirect:/banks";
	}
	
	@GetMapping("/bank/edit/{id}")
	public String editBankForm(Model model, @PathVariable("id") int id) {
		BankMaster bank = bankService.findById(id);
		
		if (bank == null) {
			log.error("Unable to Find Bank with Id " + id);
			return "redirect:/banks";
		}
		
		model.addAttribute("bank", bank);
		return "Bank/editBank";
	}
	
	@PostMapping("/bank/{id}")
	public String editBank(@ModelAttribute("bank") BankMaster bank, @PathVariable("id") int id,
			RedirectAttributes redirectAttributes) {
		BankMaster updatedBank = bankService.updateBank(bank, id);
		
		if (updatedBank == null) {
			log.error("Unable To Save Data");
			redirectAttributes.addAttribute("id", id);
			return "redirect:/bank/edit/{id}";
		}
		
		return "redirect:/banks";
	}
	
	@GetMapping("/bank/delete/{id}")
	public String deleteBank(@PathVariable("id") int id) {
		BankMaster bank = bankService.findById(id);
		
		if (bank == null) {
			log.error("Unable to Find Bank with Id " + id);
		} else {
			bankService.deleteBank(id);
		}
		
		return "redirect:/banks";
	}
}