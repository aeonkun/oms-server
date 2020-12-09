package com.ohwow.oms.customer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ohwow.oms.customer.dto.CustomerDto;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	private String deliveryAddress;
	private String contactNumber;
	private String nearbyLandmark;

	public Customer() {
		// default constructor
	}

	public Customer(String firstName, String lastName, String deliveryAddress, String contactNumber,
			String nearbyLandmark) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.deliveryAddress = deliveryAddress;
		this.contactNumber = contactNumber;
		this.nearbyLandmark = nearbyLandmark;
	}

	public Customer(CustomerDto customerDto) {
		this.firstName = customerDto.getFirstName();
		this.lastName = customerDto.getLastName();
		this.deliveryAddress = customerDto.getDeliveryAddress();
		this.contactNumber = customerDto.getContactNumber();
		this.nearbyLandmark = customerDto.getNearbyLandmark();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getNearbyLandmark() {
		return nearbyLandmark;
	}

	public void setNearbyLandmark(String nearbyLandmark) {
		this.nearbyLandmark = nearbyLandmark;
	}

}
