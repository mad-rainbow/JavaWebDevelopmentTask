package com.github.stanislauprakapovich.main;

import java.util.List;

import com.github.stanislauprakapovich.assembler.Assembler;
import com.github.stanislauprakapovich.model.Freight;
import com.github.stanislauprakapovich.model.Locomotive;
import com.github.stanislauprakapovich.model.Passenger;
import com.github.stanislauprakapovich.model.Train;
import com.github.stanislauprakapovich.model.Vehicle;
import com.github.stanislauprakapovich.parser.Parser;
import com.github.stanislauprakapovich.textoutput.TextOutput;
import com.github.stanislauprakapovich.xmlencoder.XMLExporter;

public class Main {

	public static void main(String[] args) {
		Parser parse = new Parser();

		List<Locomotive> locList = parse.getLocList();
		List<Freight> freList = parse.getFreList();
		List<Passenger> pasList = parse.getPasList();
		
		List<Train> trains = Assembler.assebmbleTrains(locList, freList, pasList);
				
		TextOutput.export(trains);
		
	}

}
