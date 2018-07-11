package com.capgemini.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.wallet.service.AccountValidation;

public class WalletValidationTest {

	AccountValidation validate = new AccountValidation();

	@Test
	public void testValidateUsername() {

		assertEquals(true, validate.validateUsername("deepak_12"));
		assertEquals(true, validate.validateUsername("deepak@12"));
		assertEquals(false, validate.validateUsername("deepak*12"));
		assertEquals(false, validate.validateUsername("deepak_df"));

	}

	@Test
	public void testValidatePassword() {

		assertEquals(true, validate.validatePassword("Deepak_12"));
		assertEquals(true, validate.validatePassword("Deepak@12"));
		assertEquals(false, validate.validatePassword("deepak_12"));
		assertEquals(false, validate.validatePassword("Deepak*12"));

	}

	@Test
	public void testValidateEmail() {

		assertEquals(true, validate.validateEmail("deepak_12@gmail.com"));
		assertEquals(true, validate.validateEmail("deepak@gamil.com"));
		assertEquals(false, validate.validateEmail("deepak@re@pre.com"));
		assertEquals(false, validate.validateEmail("deepak*re@pre.com"));

	}

	@Test
	public void testValidatePhoneNumber() {
		
		assertEquals(true, validate.validatePhoneNumber("7073197888"));
		assertEquals(true, validate.validatePhoneNumber("8073197899"));
		assertEquals(false, validate.validatePhoneNumber("670731978881"));
		assertEquals(false, validate.validatePhoneNumber("9880731978995"));

	}

	@Test
	public void testValidateAge() {
		
		assertEquals(true, validate.validateAge(20));
		assertEquals(true, validate.validateAge(100));
		assertEquals(false, validate.validateAge(15));
		assertEquals(false, validate.validateAge(10));
		
	}

	@Test
	public void testValidateGender() {
		
		assertEquals(true, validate.validateGender("male"));
		assertEquals(true, validate.validateGender("Male"));
		assertEquals(true, validate.validateGender("f"));
		assertEquals(true, validate.validateGender("F"));
		assertEquals(true, validate.validateGender("female"));
		assertEquals(true, validate.validateGender("Female"));
		assertEquals(true, validate.validateGender("m"));
		assertEquals(true, validate.validateGender("M"));
		assertEquals(false, validate.validateGender("malee"));
		assertEquals(false, validate.validateGender("f1"));
		
	}

	@Test
	public void testValidateLogin() {
		
		assertEquals(false, validate.validateLogin("deepak_12", "Deepak@12"));
		assertEquals(false, validate.validateLogin("deepak@12", "Deepak$12"));
		assertEquals(false, validate.validateLogin("deepak#12", "Deepak%12"));
		assertEquals(false, validate.validateLogin("deepak$12", "Deepak#12"));
		
	}

}
