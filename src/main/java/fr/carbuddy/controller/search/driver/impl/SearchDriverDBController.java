package fr.carbuddy.controller.search.driver.impl;

import java.util.List;
import java.util.Set;

import fr.carbuddy.controller.search.driver.ISearchDriverController;
import fr.carbuddy.model.Criteria;
import fr.carbuddy.model.Driver;
import fr.carbuddy.model.User;

public class SearchDriverDBController implements ISearchDriverController {

	private User user;
	
	public SearchDriverDBController(User user) {
		this.user = user;
	}

	@Override
	public List<Driver> search(Set<Criteria> criterias) {
		return null;
	}

}
