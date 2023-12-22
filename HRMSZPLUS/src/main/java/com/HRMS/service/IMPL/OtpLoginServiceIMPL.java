package com.HRMS.service.IMPL;

import java.sql.Timestamp;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HRMS.dao.FailedLoginAttemptDAO;
import com.HRMS.dao.LoginDAO;
import com.HRMS.dao.OtpLoginDAO;
import com.HRMS.model.FailedLoginAttempt;
import com.HRMS.model.LoginMaster;
import com.HRMS.model.OtpLoginMaster;
import com.HRMS.service.OtpLoginService;
import com.HRMS.utility.EmailService;

@Service
@EnableScheduling
@Transactional
public class OtpLoginServiceIMPL implements OtpLoginService {

	private static final Logger log = LoggerFactory.getLogger(OtpLoginServiceIMPL.class);

    @Autowired
    private FailedLoginAttemptDAO failedLoginAttemptDAO;
	
	@Autowired
	private OtpLoginDAO otplogindao;
	
	@Autowired
	private EmailService email;
	
	@Autowired
	private LoginDAO logindao;
	
	
	
	@Override
	public void recordFailedAttempt(String username) {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		
		FailedLoginAttempt findByUsername = failedLoginAttemptDAO.findByUsername(username);
		if(findByUsername!=null)
		{
			FailedLoginAttempt attempt=new FailedLoginAttempt();
			int count=findByUsername.getAttemptCount()+1;
			attempt.setId(findByUsername.getId());
			attempt.setUsername(username);
			attempt.setAttemptCount(count);
			attempt.setTs(timestamp);
			
			
			if(findByUsername.getAttemptCount()>=4)
			{
				attempt.setStatus("Locked");
				
				if(findByUsername.getMailSent().equals("NotSent"))
				{
					attempt.setMailSent("NotSent");
				}
				else
				{
					attempt.setMailSent("Sent");
				}
				
			}
			else
			{
				attempt.setMailSent("NotSent");
				attempt.setStatus("Unlocked");
			}
			
			failedLoginAttemptDAO.save(attempt);
		}
		else
		{
			FailedLoginAttempt attempt=new FailedLoginAttempt();
			attempt.setAttemptCount(1);
			attempt.setMailSent("NotSent");
			attempt.setStatus("Unlocked");
			attempt.setTs(timestamp);
			attempt.setUsername(username);
			
			failedLoginAttemptDAO.save(attempt);
			
		}
		
	}
	
	@Override
	public void resetFailedAttemp(String username) {
		FailedLoginAttempt findByUsername = failedLoginAttemptDAO.findByUsername(username);
		if(findByUsername!=null)
		{
			findByUsername.setAttemptCount(0);
			findByUsername.setStatus("Unlocked");
			failedLoginAttemptDAO.save(findByUsername);
		}
		return;
	}

	
	@Override
	public boolean CheckLockedUser(String username) {
		FailedLoginAttempt findByUsername = failedLoginAttemptDAO.findByUsername(username);
		if(findByUsername==null)
		{
			return false;
		}
		
		
		if(findByUsername.getMailSent().equals("NotSent") && findByUsername.getStatus().equals("Locked") )
		{
			email.sendEmailOnSuspiciousActivity("madhavlonkar2@gmail.com");
			findByUsername.setMailSent("Sent");
			failedLoginAttemptDAO.save(findByUsername);
			return true;
			
		}
		
		else if(findByUsername.getStatus().equals("Locked"))
		{
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isOtpAlreadyPresent(String username) {

		if (otplogindao.findByName(username) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean verifyOtp(String username, int enteredOtp) {

		OtpLoginMaster otp = otplogindao.findByName(username);

		if (otp != null && otp.getPin() == enteredOtp) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void generateAndSaveOtp(String username) {
		OtpLoginMaster existingOtp = otplogindao.findByName(username);

		
		if (existingOtp == null) {
			
			LoginMaster l=logindao.findByUsername(username);

			Random random = new Random();
			int pin = random.nextInt(9000) + 1000;
			
			if(username.equals("Admin"))
			{
				email.sendEmailWithOtp(l.getEmail(), pin);
			}
			else
			{
				email.sendEmailWithOtp(l.getEmail(), pin);
			}
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			OtpLoginMaster otp = new OtpLoginMaster();
			otp.setName(username);
			otp.setPin(pin);
			otp.setTs(timestamp);

			otplogindao.save(otp);
		}

	}
	
	@Scheduled(fixedRate = 60000) // Run every 2 minutes (60,000 milliseconds)
    public void deleteExpiredOtp() {
        Timestamp cutoffTime = new Timestamp(System.currentTimeMillis() - 300000); // 5 minutes ago
        otplogindao.deleteExpiredOtpRecords(cutoffTime);
    }
	
	@Scheduled(fixedRate = 60000) // Run every 2 minutes (60,000 milliseconds)
    public void deleteLockeddAccount() {
        Timestamp cutoffTime = new Timestamp(System.currentTimeMillis() - 600000); // 5 minutes ago
        otplogindao.deleteLockedAccounts(cutoffTime);
    }

	@Override
	public void deleteOtpByUsername(String username) {
		// TODO Auto-generated method stub
		otplogindao.deleteByUsername(username);
		try {
			otplogindao.deleteByUsername(username);
		} catch (Exception e) {
			throw new RuntimeException("Failed to delete otp for reset password.");
		}
		
	}

	
	

	


}
