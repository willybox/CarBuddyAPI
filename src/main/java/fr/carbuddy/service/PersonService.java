package fr.carbuddy.service;

import fr.carbuddy.bean.Person;

public class PersonService {

	private Person person;

	public PersonService(Person person) {
		this.person = person;
	}
	
	public String presentWithFirstnameAndName() {
		return person.getFirstname() + " " + person.getName();
	}
}
