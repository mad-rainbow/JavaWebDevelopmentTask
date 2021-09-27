package com.github.stanislauprakapovich.model;

public class Passenger extends Vehicle {

	public static final double humanWeight = 70.0;

	protected double capacityWeight = (capacity * humanWeight / 1000);
	
	public Passenger() {
		this.type = "passenger";
	}
	
	public Passenger(int number, int capacity) {
		super(number, capacity);
		this.type = "passenger";
	}
	

	public double getCapacityWeight() {
		return capacityWeight;
	}

	public void setCapacityWeight(int capacityWeight) {
		this.capacityWeight = capacityWeight;
	}


	
	

}
