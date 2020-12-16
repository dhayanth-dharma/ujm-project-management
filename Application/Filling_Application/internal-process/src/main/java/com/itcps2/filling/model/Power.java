package com.itcps2.filling.model;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Power {
	@Id
	private int id;

	private double value;

	public void setId(int id) {
		this.id = id;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public int getId() {
		return id;
	}
	public double getValue() {
		return value;
	}
	
}
