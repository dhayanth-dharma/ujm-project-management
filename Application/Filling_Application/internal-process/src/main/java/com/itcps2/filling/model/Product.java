package com.itcps2.filling.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	private int id;
	
	private String availability;
	
	private String name;
	
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvailability() {
		return availability;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
