package fr.carbuddy.controller.search.driver;

import java.util.List;
import java.util.Set;

import fr.carbuddy.model.Criteria;
import fr.carbuddy.model.Driver;

public interface ISearchDriverController {

	public List<Driver> search(Set<Criteria> criterias);

}
