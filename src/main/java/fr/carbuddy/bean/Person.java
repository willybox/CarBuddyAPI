package fr.carbuddy.bean;

import java.util.Date;

import fr.carbuddy.enumeration.Gender;

public abstract class Person {

	private Long id;
    private String name;
    private String firstname;
    private Gender gender;
    private Address address;
    private String email;
    private String phone;
    private Date birthday;
    
	public String getName() {
		return name;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Gender getGender() {
		return gender;
	}

	public Long getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
