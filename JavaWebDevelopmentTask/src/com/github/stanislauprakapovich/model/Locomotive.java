package com.github.stanislauprakapovich.model;

public class Locomotive extends Vehicle {

	public Locomotive() {
		this.type = "locomotive";
	}

	public Locomotive(int number, int capacity) {
		super(number, capacity);
		this.type = "locomotive";
	}

}
