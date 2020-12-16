package com.pottin.model;

public class Output_Prop {

	public boolean axe_remplissage_impulsion;
	public boolean power_up;
	public boolean acquired_fault;
	public boolean verte;
	public boolean magnet;
	public boolean comveyor_motor_image;
    public int number_pots;
    public double pot_size;
    public double cup_diameter;
    public boolean success;
	public void InputProp()
	{

	}

	//setters
	public void setAcquired_fault(boolean acquired_fault) {
		this.acquired_fault = acquired_fault;
	}
	public void setAxe_remplissage_impulsion(boolean axe_remplissage_impulsion) {
		this.axe_remplissage_impulsion = axe_remplissage_impulsion;
	}
	public void setPower_up(boolean power_up) {
		this.power_up = power_up;
	}
	public void setMagnet(boolean magnet) {
		this.magnet = magnet;
	}
	public void setComveyor_motor_image(boolean comveyor_motor_image) {
		this.comveyor_motor_image = comveyor_motor_image;
	}
	public void setVerte(boolean verte) {
		this.verte = verte;
	}
	
	//getters
	public boolean getAcquired() {
		return this.acquired_fault;
	}
	public boolean getAxe_remplissage_impulsion() {
		return this.axe_remplissage_impulsion ;
	}
	public boolean getPower_up() {
		return this.power_up ;
	}
	public boolean getMagnet() {
		return this.magnet ;
	}
	public boolean gettComveyor_motor_image(boolean comveyor_motor_image) {
		return this.comveyor_motor_image ;
	}
	public boolean getVerte() {
		return this.verte;
	}
	
}
