package fr.carbuddy.controller;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import fr.carbuddy.bean.Criteria;
import fr.carbuddy.bean.Driver;
import fr.carbuddy.bean.User;
import fr.carbuddy.controller.search.driver.ISearchDriverController;
import fr.carbuddy.controller.search.driver.impl.SearchDriverDBController;

/**
 * @author Willy U.
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchDriverControllerUnitTest {
	


    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    }


	@Test
	public void search_for_driver_returns_nothing() {
		User user = Mockito.mock(User.class);
		ISearchDriverController searchDriverController = new SearchDriverDBController(user);
		Set<Criteria> criterias = new HashSet<>();
		List<Driver> drivers = searchDriverController.search(criterias);
		
		assertEquals(true, drivers.isEmpty());
	}
	
}
