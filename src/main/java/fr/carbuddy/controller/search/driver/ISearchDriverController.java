package fr.carbuddy.controller.search.driver;

import java.util.List;
import java.util.Set;

import fr.carbuddy.bean.Criteria;
import fr.carbuddy.bean.Driver;

public interface ISearchDriverController {

	public List<Driver> search(Set<Criteria> criterias);

}
