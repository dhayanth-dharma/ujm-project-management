package com.itcps2.filling.model;


import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Cupconsume {
	
	@Id
	private int id;

	private String name;

	private String size;
	
	private String consume;
	
	public void setConsume(String consume) {
		this.consume = consume;
	}public void setId(int id) {
		this.id = id;
	}public void setName(String name) {
		this.name = name;
	}public void setSize(String size) {
		this.size = size;
	}public String getConsume() {
		return consume;
	}public int getId() {
		return id;
	}public String getName() {
		return name;
	}public String getSize() {
		return size;
	}
}
