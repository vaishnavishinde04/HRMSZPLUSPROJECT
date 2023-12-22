package com.HRMS.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService{

	@Autowired
	private JavaMailSender mailSender;

	private static final Logger log = LoggerFactory.getLogger(EmailService.class);

	public void sendEmailWithOtp(String toEmail, int otp) {

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom("nanduchavan2232@gmail.com");
			helper.setTo(toEmail);
			helper.setSubject("Login OTP For HRMS System");
			helper.setText("Please Use This OTP To Get Logged In: <b>" + otp + "</b> \nI will Be Expired Within 5 Min", true); // 'true'
																														// enables
																													// HTML
																														// content

			// Add the attachment
//            File attachment = new File(attachmentPath);
//            helper.addAttachment(attachment.getName(), attachment);

			mailSender.send(message);
			log.info("Mail Sent Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Unable to Send Mail");
		}
	}
	
	
	public void sendEmailOnSuspiciousActivity(String toEmail) {

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom("nanduchavan2232@gmail.com");
			helper.setTo(toEmail);
			helper.setSubject("Suspicious activity detected on HRMS system.");
	        String messageBody = "<p><span style='color: red; font-weight: bold;'>Suspicious activity detected!</span></p>"
	                + "<p>We hope this message finds you well. We regret to inform you that our security "
	                + "systems have recently detected some unusual and potentially suspicious "
	                + "activity associated with your device. Your privacy and security are of utmost importance to us, and we "
	                + "take these matters very seriously.</p>"
	                + "<p>Here's a description of what has actually happened...</p>";
	        helper.setText(messageBody);
		
			mailSender.send(message);
			log.info("Mail Sent Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Unable to Send Mail");
		}
	}
	
	
	public void sendEmailOnSuccessfulLogin (String toEmail) {

		try {
			
			Date loginTime = new Date();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String loginTimeFormatted = dateFormat.format(loginTime);
	        
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("nanduchavan2232@gmail.com");
			helper.setTo(toEmail);
			helper.setSubject("Login Detected.");
	        String messageBody = "<p><span style='color: red; font-weight: bold;'>Login Detected!</span></p>"
	                + "<p>We hope this message finds you well. We to inform you that our security "
	                + "systems have recently detected login in you system at <b>Time :"+loginTimeFormatted
	                + "</b>Your privacy and security are of utmost importance to us, and we "
	                + "take these matters very seriously.</p>";
	        helper.setText(messageBody);
		
			mailSender.send(message);
			log.info("Mail Sent Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Unable to Send Mail");
		}
	}
}
