package com.capgemini.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.wallet.bean.AccountDetails;
import com.capgemini.wallet.bean.CustomerDetails;

public class WalletDetailsTest {
	
	CustomerDetails details = new CustomerDetails();

	@Test
	public void testGetUsername() {
		
		details.getAccountDetails().getLoginDetails().setUsername("deepak_12");
		assertEquals("deepak_12", details.getAccountDetails().getLoginDetails().getUsername());
		assertNotEquals("deepak_124", details.getAccountDetails().getLoginDetails().getUsername());
	}

	@Test
	public void testGetPassword() {
		details.getAccountDetails().getLoginDetails().setPassword("Deepak@12");
		assertEquals("Deepak@12", details.getAccountDetails().getLoginDetails().getPassword());
		assertNotEquals("Deepak@124", details.getAccountDetails().getLoginDetails().getPassword());
	}

	@Test
	public void testGetBalance() {
		details.getAccountDetails().setBalance(5000);
		assertEquals(5000, details.getAccountDetails().getBalance());
	}

	@Test
	public void testGetAccountHolderName() {
		details.setAccountHolderName("deepak gupta");
		assertEquals("deepak gupta", details.getAccountHolderName());
		assertNotEquals("depak gupta", details.getAccountHolderName());
	}

	@Test
	public void testGetPhoneNumber() {
		details.getAccountDetails().setAcccountNumber("7073197888");
		assertEquals("7073197888", details.getAccountDetails().getAcccountNumber());
		assertNotEquals("7073197887", details.getAccountDetails().getAcccountNumber());
	}

	@Test
	public void testGetEmail() {
		details.setEmail("deepakgupta6034@gmail.com");
		assertEquals("deepakgupta6034@gmail.com", details.getEmail());
		assertNotEquals("deepakgupta@gmail.com", details.getEmail());
	}

	@Test
	public void testGetGender() {
		details.setGender("male");
		assertEquals("male", details.getGender());
		assertNotEquals("mal", details.getGender());
	}

	@Test
	public void testGetAge() {
		details.setAge(21);
		assertEquals(21, details.getAge());
		assertNotEquals(20, details.getAge());
	}

	@Test
	public void testGetAcccountNumber() {
		details.getAccountDetails().setAcccountNumber("1221455");
		assertEquals("1221455", details.getAccountDetails().getAcccountNumber());
		assertNotEquals("1221456", details.getAccountDetails().getAcccountNumber());
	}

}
