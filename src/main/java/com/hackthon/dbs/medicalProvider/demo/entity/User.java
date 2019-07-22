package com.hackthon.dbs.medicalProvider.demo.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import org.springframework.core.serializer.Deserializer;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.hackthon.dbs.medicalProvider.demo.DTO.UserTypes;


/**
 * 
 * @author varadharajan
 *
 */
@Entity
@Table(name = "\"user\"")

public class User extends BaseEntity implements Serializable {

	@Column(length = 30)
	private String firstName;
	@Column(length = 30)
	private String lastName;
	@Column( unique = true, length = 100)
	private String email;
	@Column(length = 30)
	private String phoneNumber;
	@Column(name = "lastLogin")
	private Instant lastLogin;

	@Column(name = "pincode")
	private String pinCode;


	private String password;

	private String userTypes;

	public String getCommissionOnSale() {
		return commissionOnSale;
	}

	public void setCommissionOnSale(String commissionOnSale) {
		this.commissionOnSale = commissionOnSale;
	}

	private String commissionOnSale;


	private String Address;

	private boolean active = true;
	
	public User() {
		
	}
	
	public User (String email, String password) {
		super();
		this.email=email;
		this.password=password;
	}

	public User(String firstName, String lastName, String email, String phoneNumber, Instant lastLogin, String pinCode,
			String password, String userTypes, String commissionOnSale, String address, boolean active) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.lastLogin = lastLogin;
		this.pinCode = pinCode;
		this.password = password;
		this.userTypes = userTypes;
		this.commissionOnSale = commissionOnSale;
		Address = address;
		this.active = active;
	}

	public String getPinCode() {

		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(String userTypes) {
		this.userTypes = userTypes;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Instant getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Instant lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", lastLogin=" + lastLogin + ", active=" + active + "]";
	}

}
