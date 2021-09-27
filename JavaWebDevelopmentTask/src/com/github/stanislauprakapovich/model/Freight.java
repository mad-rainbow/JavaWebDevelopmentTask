package com.github.stanislauprakapovich.model;

public class Freight extends Vehicle {

	public Freight() {
		this.type = "freight";
	}

	public Freight(int number, int capacity) {
		super(number, capacity);
		this.type = "freight";
	}

}
