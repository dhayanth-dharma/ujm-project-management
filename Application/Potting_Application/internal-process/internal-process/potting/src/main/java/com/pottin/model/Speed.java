package com.pottin.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Speed {
	@Id
	private int id;
	private int pot_id;
	private double speed;
	
	public void setId(int id) {
		this.id = id;
	}
	public void setPot_id(int pot_id) {
		this.pot_id = pot_id;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public int getId() {
		return id;
	}
	public int getPot_id() {
		return pot_id;
	}
	public double getSpeed() {
		return speed;
	}
	
}
