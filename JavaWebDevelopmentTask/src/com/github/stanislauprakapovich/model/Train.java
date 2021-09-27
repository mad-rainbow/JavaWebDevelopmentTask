package com.github.stanislauprakapovich.model;

import java.util.ArrayList;
import java.util.List;

public class Train {
	private List<Vehicle> train;
	private int totalCapacity;


	public Train() {
		this.train = new ArrayList<>();
	}

	public Vehicle getVehicle(int i) {
		return train.get(i);
	}

	public void add(Vehicle vehicle) {
		this.train.add(vehicle);

	}

	@Override
	public String toString() {
		return "Train [train=" + train + "]";
	}

	public int size() {
		return train.size();
	}
	
	public int getTotalCapacity() {
		totalCapacity = 0;
		for (Vehicle car : train) {
			if (car.getType() == "locomotive") {
				continue;
			}
			totalCapacity += car.getCapacity(); 
		}
		return totalCapacity;
	}

}
