package com.HRMS.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "tbl_employee_deduction")
public class EmpDeductionMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name ="employee_id")
	private EmployeeMaster employee;
	
	@ManyToOne
	@JoinColumn(name ="deduction_id")
	private DeductionMaster deduction;
	
	private double amount;
	
	

	public EmpDeductionMaster() {
		super();
	}

	public EmpDeductionMaster(long id, EmployeeMaster employee, DeductionMaster deduction, int amount) {
		super();
		this.id = id;
		this.employee = employee;
		this.deduction = deduction;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "EmpDeductionMaster [id=" + id + ", employee=" + employee + ", deduction=" + deduction + ", amount="
				+ amount + "]";
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

	public DeductionMaster getDeduction() {
		return deduction;
	}

	public void setDeduction(DeductionMaster deduction) {
		this.deduction = deduction;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	
}
