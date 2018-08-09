package fr.carbuddy.controller.search.driver.impl;

import java.util.List;
import java.util.Set;

import fr.carbuddy.bean.Criteria;
import fr.carbuddy.bean.User;
import fr.carbuddy.controller.search.driver.ISearchDriverController;

public class SearchDriverDBController implements ISearchDriverController {

	public SearchDriverDBController(User user) {
		
	}

	@Override
	public List<User> search(Set<Criteria> criterias) {
		return null;
	}

}
