package com.github.stanislauprakapovich.model;

import java.util.List;

public class Root {

	protected String name;
	protected List<Vehicle> vehicles;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	@Override
	public String toString() {
		return "Root [name=" + name + ", vehicles=" + vehicles + "]";
	}

}
