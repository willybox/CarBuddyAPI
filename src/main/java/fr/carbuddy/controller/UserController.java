package fr.carbuddy.controller;

import fr.carbuddy.exception.NegativeIdRuntimeException;
import fr.carbuddy.model.User;

public class UserController {

	public User getUser(int id) {
		if(id <= 0) {
			throw new NegativeIdRuntimeException();
		}
		return null;
	}

}
