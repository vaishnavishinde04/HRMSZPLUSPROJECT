package com.HRMS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_clients")
public class ClientMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ClientID;
	
	private String ClientName;
	private String ContactPerson;
	private String ContactNumber;
	private String Email;
	private String Address;
	private String Website;
	private String Notes;
}
