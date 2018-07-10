package com.capgemini.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.wallet.bean.AccountDetails;

public class AccountDetailsTest {
	
	AccountDetails obj = new AccountDetails();

	@Test
	public void testGetUsername() {
		
		obj.setUsername("deepak_12");
		assertEquals("deepak_12", obj.getUsername());
		assertNotEquals("deepak_124", obj.getUsername());
	}

	@Test
	public void testGetPassword() {
		obj.setPassword("Deepak@12");
		assertEquals("Deepak@12", obj.getPassword());
		assertNotEquals("Deepak@124", obj.getPassword());
	}

	@Test
	public void testGetBalance() {
		obj.setBalance(5000);
		assertEquals(5000, obj.getBalance());
	}

	@Test
	public void testGetAccountHolderName() {
		obj.setAccountHolderName("deepak gupta");
		assertEquals("deepak gupta", obj.getAccountHolderName());
		assertNotEquals("depak gupta", obj.getAccountHolderName());
	}

	@Test
	public void testGetPhoneNumber() {
		obj.setAcccountNumber("7073197888");
		assertEquals("7073197888", obj.getAcccountNumber());
		assertNotEquals("7073197887", obj.getAcccountNumber());
	}

	@Test
	public void testGetEmail() {
		obj.setEmail("deepakgupta6034@gmail.com");
		assertEquals("deepakgupta6034@gmail.com", obj.getEmail());
		assertNotEquals("deepakgupta@gmail.com", obj.getEmail());
	}

	@Test
	public void testGetGender() {
		obj.setGender("male");
		assertEquals("male", obj.getGender());
		assertNotEquals("mal", obj.getGender());
	}

	@Test
	public void testGetAge() {
		obj.setAge(21);
		assertEquals(21, obj.getAge());
		assertNotEquals(20, obj.getAge());
	}

	@Test
	public void testGetAcccountNumber() {
		obj.setAcccountNumber("1221455");
		assertEquals("1221455", obj.getAcccountNumber());
		assertNotEquals("1221456", obj.getAcccountNumber());
	}

}
