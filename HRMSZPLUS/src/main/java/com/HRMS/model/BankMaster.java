package com.HRMS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_bank")
public class BankMaster {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId;
	
	private String bankName;
	
	private String bankBranch;
	
	private String bankDescritpion;

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankDescritpion() {
		return bankDescritpion;
	}

	public void setBankDescritpion(String bankDescritpion) {
		this.bankDescritpion = bankDescritpion;
	}

	public BankMaster(int bankId, String bankName, String bankBranch, String bankDescritpion) {
		super();
		this.bankId = bankId;
		this.bankName = bankName;
		this.bankBranch = bankBranch;
		this.bankDescritpion = bankDescritpion;
	}

	public BankMaster() {
		super();
	}

	@Override
	public String toString() {
		return "BankMaster [bankId=" + bankId + ", bankName=" + bankName + ", bankBranch=" + bankBranch
				+ ", bankDescritpion=" + bankDescritpion + "]";
	}
	
	
	
	
}
