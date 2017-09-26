package me.micopiira.hibernatetest.domain;

import static org.junit.Assert.*;
import org.junit.Test;

public class CustomerTest {

	@Test
	public void getFullName() throws Exception {
		Customer c = new Customer();
		c.setFirstName("firstName");
		c.setLastName("lastName");
		assertEquals("firstName lastName", c.getFullName());
	}

}