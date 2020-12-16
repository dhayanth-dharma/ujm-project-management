package com.pottin.model;

import org.springframework.web.socket.WebSocketSession;

public class Input_Prop {
	public boolean km1;
	public boolean axot_under_power;
	public boolean v90_alarm;
	public boolean presence_pot;
	public boolean homing_cam;
	public boolean presence_product;
	public String cup_size;
	public boolean downstream_b5_accumulation;
	public String product_name;
	public double speed;
	public double weight;

	public String _pottingstatus;
	public boolean potting_status_bool;
	public String filling_status;
	public boolean filling_status_boolean;
	
	public Input_Prop ()
	{	
																									
	}
	
	public void setCup_size(String cup_size) {
		this.cup_size = cup_size;
	}
	public String getCup_size() {
		return cup_size;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getSpeed() {
		return speed;
	}
	public double getWeight() {
		return weight;
	}
	public void setAxot_under_power(boolean axot_under_power) {
		this.axot_under_power = axot_under_power;
	}
	public void setDownstream_b5_accumulation(boolean downstream_b5_accumulation) {
		this.downstream_b5_accumulation = downstream_b5_accumulation;
	}
	public void setKm1(boolean km1) {
		this.km1 = km1;
	}
	public void setHoming_cam(boolean homing_cam) {
		this.homing_cam = homing_cam;
	}
	public void setPresence_product(boolean presemce_product) {
		this.presence_product = presemce_product;
	}
	public void setPresence_pot(boolean presence_pot) {
		this.presence_pot = presence_pot;
	}
	public void setV90_alarm(boolean v90_alarm) {
		this.v90_alarm = v90_alarm;
	}
	
	//getters

	public boolean getDwnstream_b5_accumulation() {
		return downstream_b5_accumulation;
	}
	
	public boolean getkm1() {
		return km1;
	}
	

	public boolean getAxot_under_power() {
		return axot_under_power;
	}

	public boolean getv90_alarm() {
		return v90_alarm;
	}

	public boolean getPresence_product() {
		return presence_product;
	}

	public boolean getHoming_cam() {
		return homing_cam;
	}

	public boolean getPresence_pot() {
		return presence_pot;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_name() {
		return product_name;
	}
	
}
