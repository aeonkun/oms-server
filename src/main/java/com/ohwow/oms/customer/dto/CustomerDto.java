package com.ohwow.oms.customer.dto;

import com.ohwow.oms.customer.domain.Customer;

public class CustomerDto {

	private String firstName;
	private String lastName;
	private String deliveryAddress;
	private String contactNumber;
	private String nearbyLandmark;

	public CustomerDto(String firstName, String lastName, String deliveryAddress, String contactNumber,
			String nearbyLandmark) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.deliveryAddress = deliveryAddress;
		this.contactNumber = contactNumber;
		this.nearbyLandmark = nearbyLandmark;
	}

	public CustomerDto(Customer customer) {
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.deliveryAddress = customer.getDeliveryAddress();
		this.contactNumber = customer.getContactNumber();
		this.setNearbyLandmark(customer.getNearbyLandmark());
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result + ((deliveryAddress == null) ? 0 : deliveryAddress.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((nearbyLandmark == null) ? 0 : nearbyLandmark.hashCode());
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
		CustomerDto other = (CustomerDto) obj;
		if (contactNumber == null) {
			if (other.contactNumber != null)
				return false;
		} else if (!contactNumber.equals(other.contactNumber))
			return false;
		if (deliveryAddress == null) {
			if (other.deliveryAddress != null)
				return false;
		} else if (!deliveryAddress.equals(other.deliveryAddress))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (nearbyLandmark == null) {
			if (other.nearbyLandmark != null)
				return false;
		} else if (!nearbyLandmark.equals(other.nearbyLandmark))
			return false;
		return true;
	}

}
