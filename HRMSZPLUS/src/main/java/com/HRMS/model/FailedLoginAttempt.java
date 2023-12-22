package com.HRMS.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_failedloginattempt")
public class FailedLoginAttempt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	private int attemptCount;
	private String status;
	private String mailSent;
	
	@CreationTimestamp
	private Timestamp ts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAttemptCount() {
		return attemptCount;
	}

	public void setAttemptCount(int attemptCount) {
		this.attemptCount = attemptCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMailSent() {
		return mailSent;
	}

	public void setMailSent(String mailSent) {
		this.mailSent = mailSent;
	}

	public Timestamp getTs() {
		return ts;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}

	public FailedLoginAttempt(Long id, String username, int attemptCount, String status, String mailSent,
			Timestamp ts) {
		super();
		this.id = id;
		this.username = username;
		this.attemptCount = attemptCount;
		this.status = status;
		this.mailSent = mailSent;
		this.ts = ts;
	}

	public FailedLoginAttempt() {
		super();
	}

	@Override
	public String toString() {
		return "FailedLoginAttempt [id=" + id + ", username=" + username + ", attemptCount=" + attemptCount
				+ ", status=" + status + ", mailSent=" + mailSent + ", ts=" + ts + "]";
	}
	
	

	

	

	

}
