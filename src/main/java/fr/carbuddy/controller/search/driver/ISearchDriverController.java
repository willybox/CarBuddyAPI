package fr.carbuddy.controller.search.driver;

import java.util.List;
import java.util.Set;

import fr.carbuddy.bean.Criteria;
import fr.carbuddy.bean.User;

public interface ISearchDriverController {

	public List<User> search(Set<Criteria> criterias);

}
