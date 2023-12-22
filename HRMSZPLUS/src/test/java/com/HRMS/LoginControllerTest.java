package com.HRMS;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.HRMS.controller.LoginController;
import com.HRMS.model.LoginMaster;
import com.HRMS.service.LoginService;
import com.HRMS.service.OtpLoginService;

@SpringBootTest
public class LoginControllerTest {
	@InjectMocks
	private LoginController loginController;

	@Mock
	private LoginMaster loginMaster;

	@Autowired
	private LoginService loginService;

	@Mock
	private OtpLoginService otpLoginService;

	@BeforeEach
	public void setUp() {
		// Initialize your LoginController instance.
		loginController = new LoginController();
	}

	@Test
	public void testUsernameRegex() {
		// Define the regular expression for username validation.
		String usernameRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";

		// Test with valid and invalid usernames
		assertTrue(loginController.validateUsername("validUsername123@example.com", usernameRegex));
		assertTrue(loginController.validateUsername("anotherValid@my-website.co", usernameRegex));
		assertTrue(loginController.validateUsername("1234@domain.name", usernameRegex));

		// Invalid usernames
		assertTrue(!loginController.validateUsername("invalidUsername", usernameRegex));
		assertTrue(!loginController.validateUsername("example.com", usernameRegex));
		assertTrue(!loginController.validateUsername("user@.com", usernameRegex));

		System.out.println("\nvalidUsername123@example.com :"
				+ loginController.validateUsername("validUsername123@example.com", usernameRegex));
		System.out.println("anotherValid@my-website.co : "
				+ loginController.validateUsername("anotherValid@my-website.co", usernameRegex));
		System.out.println("1234@domain.name : " + loginController.validateUsername("1234@domain.name", usernameRegex));
		System.out.println("invalidUsername : " + loginController.validateUsername("invalidUsername", usernameRegex));
		System.out.println("example.com :" + loginController.validateUsername("example.com", usernameRegex));
		System.out.println("user@.com : " + loginController.validateUsername("user@.com", usernameRegex));

	}

	@Test
	public void testPasswordRegex() {
		// Define the regular expression for password validation.
		String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d@$!%*?&#]{8,}$";

		// Test with valid and invalid passwords
		System.out
				.println("\nValidPassword123$ : " + loginController.validatePassword("ValidPassword123$", passwordRegex));
		System.out.println("StrongP@ssw0rd : " + loginController.validatePassword("StrongP@ssw0rd", passwordRegex));
		System.out.println("Secure123!# : " + loginController.validatePassword("Secure123!#", passwordRegex));

		// Invalid passwords
		System.out.println("weakpassword : " + loginController.validatePassword("weakpassword", passwordRegex));
		System.out.println("Short1@ : " + loginController.validatePassword("Short1@", passwordRegex));
		System.out.println(
				"noSpecialChars1234 : " + loginController.validatePassword("noSpecialChars1234", passwordRegex));
		System.out.println();

	}

	@Test
	public void testPassowrdFromDatabase() {
		String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d@$!%*?&#]{8,}$";

		LoginMaster findUserLoginsById = loginService.findUserLoginsById(1);
		String Password = findUserLoginsById.getPassword();

		System.out.print(
				"\nTest For Password From Database : " + loginController.validatePassword(Password, passwordRegex));
	}
	
	@Test
	public void testUsernameFromDatabase() {
		String usernameRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";

		LoginMaster findUserLoginsById = loginService.findUserLoginsById(1);
		String Username = findUserLoginsById.getUsername();

		System.out.print(
				"\nTest For Username From Database : " + loginController.validatePassword(Username, usernameRegex));
	}
	
	
	
}
