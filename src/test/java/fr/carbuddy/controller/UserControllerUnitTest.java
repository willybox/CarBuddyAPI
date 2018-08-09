package fr.carbuddy.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.carbuddy.bean.User;
import fr.carbuddy.exception.NegativeIdRuntimeException;

public class UserControllerUnitTest {

	@Test(expected=NegativeIdRuntimeException.class)
	public void negative_id_returns_throw_negative_id_exception() {
		UserController sut = new UserController();
		sut.getUser(-1);
		
		/** If no exception thrown, test failed */
		assertEquals(false, true);
	}
	
	@Test
	public void get_id_1_returns_user() {
		UserController sut = new UserController();
		User user = sut.getUser(1);
		
		/** If no exception thrown, test failed */
		assertEquals(true, user != null);
	}
}
