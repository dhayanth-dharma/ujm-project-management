package com.pottin.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Pot {

		@Id
		private int id;

		private String size;
		
		private String name;
		private int availability;
		public void setId(int id) {
			this.id = id;
		}
		public void setAvailability(int availability) {
			this.availability = availability;
		}
		public int getAvailability() {
			return availability;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(String size) {
			this.size = size;
		}
		public int getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public String getSize() {
			return size;
		}
	}
