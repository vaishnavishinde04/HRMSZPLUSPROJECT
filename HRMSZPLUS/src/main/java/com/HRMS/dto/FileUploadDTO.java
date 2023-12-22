package com.HRMS.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadDTO {
    private MultipartFile resumeFile;
    private MultipartFile profileImageFile;
	public MultipartFile getResumeFile() {
		return resumeFile;
	}
	public void setResumeFile(MultipartFile resumeFile) {
		this.resumeFile = resumeFile;
	}
	public MultipartFile getProfileImageFile() {
		return profileImageFile;
	}
	public void setProfileImageFile(MultipartFile profileImageFile) {
		this.profileImageFile = profileImageFile;
	}
	public FileUploadDTO(MultipartFile resumeFile, MultipartFile profileImageFile) {
		super();
		this.resumeFile = resumeFile;
		this.profileImageFile = profileImageFile;
	}
	public FileUploadDTO() {
		super();
	}
    
    

}
