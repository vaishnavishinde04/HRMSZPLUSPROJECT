package com.HRMS.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_otp")
public class OtpLoginMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private int pin;
	
	@CreationTimestamp
	private Timestamp ts;

	public OtpLoginMaster(int id, String name, int pin, Timestamp ts) {
		super();
		this.id = id;
		this.name = name;
		this.pin = pin;
		this.ts = ts;
	}

	public OtpLoginMaster() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public Timestamp getTs() {
		return ts;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}

	@Override
	public String toString() {
		return "OTPMaster [id=" + id + ", name=" + name + ", pin=" + pin + ", ts=" + ts + "]";
	}
	
	
	
}
