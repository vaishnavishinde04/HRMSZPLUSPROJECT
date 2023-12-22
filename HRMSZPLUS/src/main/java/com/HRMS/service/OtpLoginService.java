package com.HRMS.service;

public interface OtpLoginService {
	
	public boolean isOtpAlreadyPresent(String username);
	public boolean verifyOtp(String username, int enteredOtp);
	public void generateAndSaveOtp(String username);
	void deleteExpiredOtp();
	void deleteOtpByUsername(String username);
	
	boolean CheckLockedUser(String username);
	void recordFailedAttempt(String username);
	void resetFailedAttemp(String username);
}
