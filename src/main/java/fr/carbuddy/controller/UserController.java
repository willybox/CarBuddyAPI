package fr.carbuddy.controller;

import fr.carbuddy.bean.User;
import fr.carbuddy.exception.NegativeIdRuntimeException;

public class UserController {

	public User getUser(int id) {
		if(id <= 0) {
			throw new NegativeIdRuntimeException();
		}
		return null;
	}

}
