package com.github.stanislauprakapovich.assembler;

import java.util.ArrayList;
import java.util.List;

import com.github.stanislauprakapovich.model.Freight;
import com.github.stanislauprakapovich.model.Locomotive;
import com.github.stanislauprakapovich.model.Passenger;
import com.github.stanislauprakapovich.model.Train;
import com.github.stanislauprakapovich.model.Vehicle;

public class Assembler {

	public static List<Train> assebmbleTrains(List<Locomotive> locList, List<Freight> freList,
			List<Passenger> pasList) {
		
		List<Train> result = new ArrayList<>();

		int freIndex = 0;
		int pasIndex = 0;

		for (int i = 0; i < locList.size(); i++) {
			if (locList.get(i).getCapacity() > 100) {
				Train train = new Train();
				train = assembleFreight(locList.get(i), freList, freIndex);
				result.add(train);
				freIndex += train.size()-1;
			}else {
				Train train = new Train();
				train = assemblePassenger(locList.get(i), pasList, pasIndex);
				result.add(train);
				pasIndex += train.size()-1;
			}
		}

		return result;
	}

	private static Train assembleFreight(Locomotive loc, List<Freight> freList, int index) {
		Train train = new Train();
		int locCapacity = loc.getCapacity();
		int carCapacity = 0;

		train.add(loc);

		for (int i = index; i < freList.size(); i++) {
			if (locCapacity < freList.get(i).getCapacity()) {
				continue;
			}
			if (i != freList.size() - 1) {
				if (carCapacity + freList.get(i + 1).getCapacity() > locCapacity) {
					break;
				}
			} else {
				if (carCapacity + freList.get(i).getCapacity() > loc.getCapacity()) {
					break;
				}
			}
			train.add(freList.get(i));
			carCapacity += freList.get(i).getCapacity();
		}

		return train;

	}

	private static Train assemblePassenger(Locomotive loc, List<Passenger> pasList, int index) {
		Train train = new Train();
		int locCapacity = loc.getCapacity();
		double carCapacity = 0;

		train.add(loc);

		for (int i = index; i < pasList.size(); i++) {
			if (locCapacity < pasList.get(i).getCapacityWeight()) {
				continue;
			}
			if (i != pasList.size() - 1) {
				if (carCapacity + pasList.get(i + 1).getCapacityWeight() > locCapacity) {
					break;
				} else {
					if (carCapacity + pasList.get(i).getCapacityWeight() > loc.getCapacity()) {
						break;
					}
				}
			}
			train.add(pasList.get(i));
			carCapacity += pasList.get(i).getCapacityWeight();
		}
		return train;
	}
}
