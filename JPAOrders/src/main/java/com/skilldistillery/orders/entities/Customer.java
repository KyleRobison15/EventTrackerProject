package com.skilldistillery.orders.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	
/////////////////////// FIELDS ///////////////////////////
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	private String email;
	
	private String phone;
	
	private String street;
	
	private String city;
	
	@Column(name="state_abbreviation")
	private String stateAbbreviation;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@OneToMany(mappedBy="customer")
	private List<Requisition> reqs;

/////////////////////// CONSTRUCTORS ///////////////////////////
	
	public Customer() {}
	
/////////////////////// METHODS ///////////////////////////
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateAbbreviation() {
		return stateAbbreviation;
	}

	public void setStateAbbreviation(String stateAbbreviation) {
		this.stateAbbreviation = stateAbbreviation;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public List<Requisition> getReqs() {
		return reqs;
	}

	public void setReqs(List<Requisition> reqs) {
		this.reqs = reqs;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", street=" + street + ", city=" + city + ", stateAbbreviation="
				+ stateAbbreviation + ", postalCode=" + postalCode + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
