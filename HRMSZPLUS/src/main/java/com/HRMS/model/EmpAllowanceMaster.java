package com.HRMS.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_employee_allowance")
public class EmpAllowanceMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private EmployeeMaster employee;

	@ManyToOne
	@JoinColumn(name = "allowance_id")
	private AllowanceMaster allowance;

	private double amount;

	public EmpAllowanceMaster() {
		super();
	}

	public EmpAllowanceMaster(long id, EmployeeMaster employee, AllowanceMaster allowance, int amount) {
		super();
		this.id = id;
		this.employee = employee;
		this.allowance = allowance;
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EmployeeMaster getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeMaster employee) {
		this.employee = employee;
	}

	public AllowanceMaster getAllowance() {
		return allowance;
	}

	public void setAllowance(AllowanceMaster allowance) {
		this.allowance = allowance;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "EmpAllowanceMaster [id=" + id + ", employee=" + employee + ", allowance=" + allowance + ", amount="
				+ amount + "]";
	}

}
