package fr.carbuddy.dao.mysql.impl;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.carbuddy.bean.Address;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.dao.mysql.DAOFactoryMySQLImpl;
import fr.carbuddy.enumeration.ValidationStatus;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddy.exception.NotValidException;

public class AddressDAOMySQLImplUnitTest {
	
	private static DAOFactory daoFactory;
	
	@BeforeClass
	public static void init() {
		daoFactory = DAOFactoryMySQLImpl.getInstance(true);
	}
	
	@Test(expected=NullPointerException.class)
	public void create_address_null_throws_NPE() {
		Address address = null;
		try {
			daoFactory.getAddressDAO().create(address);
		} catch (DAORuntimeException e) {
			System.err.println("You should not be here");
		} catch (NotValidException e) {
			System.err.println("You should not be here");
		}
		System.err.println("If printed = failed");
	}
	
	@Test
	public void create_address_not_valid_catch_not_valid_exception() {
		Address address = new Address();
		try {
			daoFactory.getAddressDAO().create(address);
		} catch (DAORuntimeException e) {
			System.err.println("You should not be here");
		} catch (NotValidException e) {
			// Expected to go here

			assertEquals(
				true,
				e.getErrorsValidation()
					.contains(ValidationStatus.POSTAL_EMPTY)
			);
			assertEquals(
				true,
				e.getErrorsValidation()
					.contains(ValidationStatus.STREET_EMPTY)
			);
			assertEquals(
				true,
				e.getErrorsValidation()
					.contains(ValidationStatus.CITY_EMPTY)
			);
			assertEquals(
				true,
				e.getErrorsValidation()
					.contains(ValidationStatus.COUNTRY_EMPTY)
			);
		}
	}
	
	@Test
	public void create_address_not_complete_catch_not_valid_exception() {
		Address address = new Address();
		address.setCity("Paris");
		address.setCountry("FRANCE");
		address.setPostal("75000");
		try {
			daoFactory.getAddressDAO().create(address);
		} catch (DAORuntimeException e) {
			System.err.println("You should not be here");
		} catch (NotValidException e) {
			// Expected to go here

			assertEquals(
				false,
				e.getErrorsValidation()
					.contains(ValidationStatus.POSTAL_EMPTY)
			);
			assertEquals(
				true,
				e.getErrorsValidation()
					.contains(ValidationStatus.STREET_EMPTY)
			);
			assertEquals(
				false,
				e.getErrorsValidation()
					.contains(ValidationStatus.CITY_EMPTY)
			);
			assertEquals(
				false,
				e.getErrorsValidation()
					.contains(ValidationStatus.COUNTRY_EMPTY)
			);
		}
	}
}
