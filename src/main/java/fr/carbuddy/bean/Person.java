package fr.carbuddy.bean;

import java.util.Date;

public abstract class Person {

    private String name;
    private String firstname;
    private Adress adress;
    private String email;
    private String phone;
    private Date birthday;
    
	public String getName() {
		return name;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public Adress getAdress() {
		return adress;
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public void setAdress(Adress adress) {
		this.adress = adress;
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
}
