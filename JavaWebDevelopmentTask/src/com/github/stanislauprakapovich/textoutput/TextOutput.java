package com.github.stanislauprakapovich.textoutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.github.stanislauprakapovich.model.Train;

public class TextOutput {

	public static void export(List<Train> trains) {
		File dir = new File("data");
		File output = new File(dir, "trains.txt");

		String text = buildText(trains);

		try (OutputStream os = new FileOutputStream(output)) {

			os.write(text.getBytes());

		} catch (FileNotFoundException e) {
			System.out.println("Cannot create file");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Cannot write output file");
		}
	}

	private static String buildText (List<Train> trains) {
		
		String text = "";

		for (int i = 0; i < trains.size(); i++) {
			text += "Train No. " + (i+1) + ", Capacity: " + trains.get(i).getTotalCapacity();
			switch (trains.get(i).getVehicle(1).getType()) {
			case "freight":{
				text += " tons\n";
				break;
			}
			case "passenger":{
				text += " seats\n";
				break;
			}
			}
			
			for (int j = 0; j < trains.get(i).size(); j++) {
				switch (trains.get(i).getVehicle(j).getType()) {
				case "locomotive":{
					text += "Locomotive No." + trains.get(i).getVehicle(j).getNumber() + ", Capacity: " +  trains.get(i).getVehicle(j).getCapacity() + " tons\n";
					break;
				}
				case "freight":{
					text += "Freight No." + trains.get(i).getVehicle(j).getNumber() + ", Capacity: " +  trains.get(i).getVehicle(j).getCapacity() + " tons\n";
					break;
				}
				case "passenger":{
					text += "Passenger No." + trains.get(i).getVehicle(j).getNumber() + ", Capacity: " +  trains.get(i).getVehicle(j).getCapacity() + " seats\n";
					break;
				}
				}
			}
			text += "\n";

		}
		return text;

	}

}
