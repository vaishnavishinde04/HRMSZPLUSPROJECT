package com.HRMS.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_employee")
public class EmployeeMaster {
	
	//page 1
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empId;
	private String empName;
    private String currentAddress;
    private String permanentAddress;
    private Date dateOfBirth;
    private String gender;
    private String employeeEmail;
    private String employeeContactNo;
    private String maritalStatus;
    private String state;
    private String employeePan;
    
    
//    @OneToMany
//    private List<EmpAllowanceMaster> employeeAllowances;

    
    //page2
    @ManyToOne
	@JoinColumn(name="designationID")
    private DesignationMaster designation;
    
    @ManyToOne
    @JoinColumn(name="departmentID")
    private DepartmentMaster department;
	
    private Date dateOfJoining;
    private String employmentType;
    
    @ManyToOne
    @JoinColumn(name="bankID")
    private BankMaster bank;
    
    private String bankAccountNumber;
    private String bankIFSC;
    
    //page3
    private String highestEducation;
    private String collegeUniversityName;
    private String areaOfStudy;
    private Date dateOfGraduation;
    
    //page4
    private String emergencyContactName;
    private String emergencyContactRelation;
    private String emergencyContactAddress;
    private String emergencyContactContact;
    
    
    
    private String reference;
    
    @Column(name = "resume_path")
    private String resumePath;

    
    @Column(name = "profileImage_path")
    private String profileImageData;
    
    private String employmentEligibilityVerification;
    private String ndaConsent;
    private String criminalBackgroundCheckConsent;
    private String drugTestingConsent;
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getEmployeeContactNo() {
		return employeeContactNo;
	}
	public void setEmployeeContactNo(String employeeContactNo) {
		this.employeeContactNo = employeeContactNo;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEmployeePan() {
		return employeePan;
	}
	public void setEmployeePan(String employeePan) {
		this.employeePan = employeePan;
	}
	public DesignationMaster getDesignation() {
		return designation;
	}
	public void setDesignation(DesignationMaster designation) {
		this.designation = designation;
	}
	public DepartmentMaster getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentMaster department) {
		this.department = department;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
	public BankMaster getBank() {
		return bank;
	}
	public void setBank(BankMaster bank) {
		this.bank = bank;
	}
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public String getBankIFSC() {
		return bankIFSC;
	}
	public void setBankIFSC(String bankIFSC) {
		this.bankIFSC = bankIFSC;
	}
	public String getHighestEducation() {
		return highestEducation;
	}
	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}
	public String getCollegeUniversityName() {
		return collegeUniversityName;
	}
	public void setCollegeUniversityName(String collegeUniversityName) {
		this.collegeUniversityName = collegeUniversityName;
	}
	public String getAreaOfStudy() {
		return areaOfStudy;
	}
	public void setAreaOfStudy(String areaOfStudy) {
		this.areaOfStudy = areaOfStudy;
	}
	public Date getDateOfGraduation() {
		return dateOfGraduation;
	}
	public void setDateOfGraduation(Date dateOfGraduation) {
		this.dateOfGraduation = dateOfGraduation;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyContactRelation() {
		return emergencyContactRelation;
	}
	public void setEmergencyContactRelation(String emergencyContactRelation) {
		this.emergencyContactRelation = emergencyContactRelation;
	}
	public String getEmergencyContactAddress() {
		return emergencyContactAddress;
	}
	public void setEmergencyContactAddress(String emergencyContactAddress) {
		this.emergencyContactAddress = emergencyContactAddress;
	}
	public String getEmergencyContactContact() {
		return emergencyContactContact;
	}
	public void setEmergencyContactContact(String emergencyContactContact) {
		this.emergencyContactContact = emergencyContactContact;
	}
	
	public String getResumePath() {
		return resumePath;
	}
	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}
	public String getProfileImageData() {
		return profileImageData;
	}
	public void setProfileImageData(String profileImageData) {
		this.profileImageData = profileImageData;
	}
	public String getEmploymentEligibilityVerification() {
		return employmentEligibilityVerification;
	}
	public void setEmploymentEligibilityVerification(String employmentEligibilityVerification) {
		this.employmentEligibilityVerification = employmentEligibilityVerification;
	}
	public String getNdaConsent() {
		return ndaConsent;
	}
	public void setNdaConsent(String ndaConsent) {
		this.ndaConsent = ndaConsent;
	}
	public String getCriminalBackgroundCheckConsent() {
		return criminalBackgroundCheckConsent;
	}
	public void setCriminalBackgroundCheckConsent(String criminalBackgroundCheckConsent) {
		this.criminalBackgroundCheckConsent = criminalBackgroundCheckConsent;
	}
	public String getDrugTestingConsent() {
		return drugTestingConsent;
	}
	public void setDrugTestingConsent(String drugTestingConsent) {
		this.drugTestingConsent = drugTestingConsent;
	}
	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	
	public EmployeeMaster(long empId, String empName, String currentAddress, String permanentAddress, Date dateOfBirth,
			String gender, String employeeEmail, String employeeContactNo, String maritalStatus, String state,
			String employeePan, DesignationMaster designation, DepartmentMaster department, Date dateOfJoining,
			String employmentType, BankMaster bank, String bankAccountNumber, String bankIFSC, String highestEducation,
			String collegeUniversityName, String areaOfStudy, Date dateOfGraduation, String emergencyContactName,
			String emergencyContactRelation, String emergencyContactAddress, String emergencyContactContact,
			String reference, String resumePath, String profileImageData, String employmentEligibilityVerification,
			String ndaConsent, String criminalBackgroundCheckConsent, String drugTestingConsent) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.currentAddress = currentAddress;
		this.permanentAddress = permanentAddress;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.employeeEmail = employeeEmail;
		this.employeeContactNo = employeeContactNo;
		this.maritalStatus = maritalStatus;
		this.state = state;
		this.employeePan = employeePan;
		this.designation = designation;
		this.department = department;
		this.dateOfJoining = dateOfJoining;
		this.employmentType = employmentType;
		this.bank = bank;
		this.bankAccountNumber = bankAccountNumber;
		this.bankIFSC = bankIFSC;
		this.highestEducation = highestEducation;
		this.collegeUniversityName = collegeUniversityName;
		this.areaOfStudy = areaOfStudy;
		this.dateOfGraduation = dateOfGraduation;
		this.emergencyContactName = emergencyContactName;
		this.emergencyContactRelation = emergencyContactRelation;
		this.emergencyContactAddress = emergencyContactAddress;
		this.emergencyContactContact = emergencyContactContact;
		this.reference = reference;
		this.resumePath = resumePath;
		this.profileImageData = profileImageData;
		this.employmentEligibilityVerification = employmentEligibilityVerification;
		this.ndaConsent = ndaConsent;
		this.criminalBackgroundCheckConsent = criminalBackgroundCheckConsent;
		this.drugTestingConsent = drugTestingConsent;
	}
	public EmployeeMaster() {
		super();
	}
	
    
    
	
	
}
