package com.HRMS.model;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_interviewRounds")
public class InterviewRoundMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int InterviewID;
	
	private int CandidateID; //foreign key
	
	private int InterviewerID; //emplyee taking interview
	
	private int RoundNumber;
	
	private Date InterviewDate;
	private Timestamp InterviewStartTime;
	private Timestamp InterviewEndTime;
	private String InterviewLocation;
	private String InterviewStatus; //passed failed
	private String InterviewType; //formal,techincal,HR,behvaoural
	private String InterviewFeedback;
	
	private int TechnicalSkillsRating;
	private int SoftSkillsRating;
	private int CommunicationSkillsRating;
	private int OverallRating;
}
