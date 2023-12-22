package com.HRMS.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_candidate")
public class CandidateMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long candidateId;

	private int demandId;

	private Date appliedDate;

	private String candidateName;
	private String candidateAddress;
	private String candidateGender;
	private String candidateEmail;
	private String candidateMobileNo;
	private Date candidateDOB;
	private String candidateState;
	private String candidatePan;

	private String candidateHighestEducation;
	private String candidateCollegeUniversityName;
	private String candidateAreaOfStudy;
	private Date candidateDateOfGraduation;

	private String candidateReference;

	private String Status;

	@Column(name = "resume_path")
	private String resumePath;

	@Column(name = "profileImage_path")
	private String profileImageData;

}
