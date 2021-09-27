package com.github.stanislauprakapovich.model;

public abstract class Vehicle {

	protected int number;
	protected String type;
	protected int capacity;
	
	public Vehicle() {
	}

	public Vehicle(int number, int capacity) {
		this.number = number;
		this.capacity = capacity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Vehicle [number=" + number + ", type=" + type + ", capacity=" + capacity + "]";
	}

}
