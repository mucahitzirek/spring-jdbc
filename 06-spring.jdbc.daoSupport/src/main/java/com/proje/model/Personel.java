package com.proje.model;

import java.io.Serializable;
import java.util.Date;

public class Personel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int personelId;

	private String firstName;

	private String lastName;

	private Date birthOfDate;

	private String personelNumber;

	public Personel() {

	}

	public Personel(int personelId, String firstName, String lastName, Date birthOfDate, String personelNumber) {
		this.personelId = personelId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthOfDate = birthOfDate;
		this.personelNumber = personelNumber;
	}

	public int getPersonelId() {
		return personelId;
	}

	public void setPersonelId(int personelId) {
		this.personelId = personelId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthOfDate() {
		return birthOfDate;
	}

	public void setBirthOfDate(Date birthOfDate) {
		this.birthOfDate = birthOfDate;
	}

	public String getPersonelNumber() {
		return personelNumber;
	}

	public void setPersonelNumber(String personelNumber) {
		this.personelNumber = personelNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Personel [personelId=" + personelId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", birthOfDate=" + birthOfDate + ", personelNumber=" + personelNumber + "]";
	}

}
