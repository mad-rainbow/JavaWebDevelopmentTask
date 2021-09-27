package com.github.stanislauprakapovich.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.github.stanislauprakapovich.model.Freight;
import com.github.stanislauprakapovich.model.Locomotive;
import com.github.stanislauprakapovich.model.Passenger;
import com.github.stanislauprakapovich.model.Root;

public class Parser {

	private List<Locomotive> locList = new ArrayList<>();
	private List<Freight> freList = new ArrayList<>();
	private List<Passenger> pasList = new ArrayList<>();

	public Parser() {

		Document doc;

		try {
			doc = buildDocument();
		} catch (Exception e) {
			System.out.println("Cannot read input file");
			e.printStackTrace();
			return;

		}

		Node rootNode = doc.getFirstChild();

		NodeList rootChilds = rootNode.getChildNodes();

		Node vehiclesNode = parseVehiclesNode(rootChilds);

		NodeList vehiclesChilds = vehiclesNode.getChildNodes();
		NodeList vehicleChilds = null;
		NodeList vehicleInstanceChilds = null;

		List<Locomotive> locList = new ArrayList<>();
		List<Freight> freList = new ArrayList<>();
		List<Passenger> pasList = new ArrayList<>();

		for (int i = 0; i < vehiclesChilds.getLength(); i++) {
			if (vehiclesChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			if (vehiclesChilds.item(i).getNodeName().equals("vehicle")) {
				vehicleChilds = vehiclesChilds.item(i).getChildNodes();
			}
			for (int j = 0; j < vehicleChilds.getLength(); j++) {
				if (vehicleChilds.item(j).getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				switch (vehicleChilds.item(j).getNodeName()) {

				case "locomotive": {
					vehicleInstanceChilds = vehicleChilds.item(j).getChildNodes();

					Locomotive loc = new Locomotive(parseVehicleInstanceChilds(vehicleInstanceChilds)[0],
							parseVehicleInstanceChilds(vehicleInstanceChilds)[1]);
					locList.add(loc);
					break;
				}
				case "freight": {
					vehicleInstanceChilds = vehicleChilds.item(j).getChildNodes();

					Freight fre = new Freight(parseVehicleInstanceChilds(vehicleInstanceChilds)[0],
							parseVehicleInstanceChilds(vehicleInstanceChilds)[1]);
					freList.add(fre);
					break;
				}
				case "passenger": {
					vehicleInstanceChilds = vehicleChilds.item(j).getChildNodes();

					Passenger pas = new Passenger(parseVehicleInstanceChilds(vehicleInstanceChilds)[0],
							parseVehicleInstanceChilds(vehicleInstanceChilds)[1]);
					pasList.add(pas);
					break;
				}
				}

			}

		}
		this.locList = locList;
		this.freList = freList;
		this.pasList = pasList;
	}

	public List<Locomotive> getLocList() {
		return locList;
	}

	public List<Freight> getFreList() {
		return freList;
	}

	public List<Passenger> getPasList() {
		return pasList;
	}

	private static int[] parseVehicleInstanceChilds(NodeList vehicleInstanceChilds) {
		int number = 0;
		int capacity = 0;
		int[] result = new int[2];

		try {
			for (int k = 0; k < vehicleInstanceChilds.getLength(); k++) {
				if (vehicleInstanceChilds.item(k).getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				if (vehicleInstanceChilds.item(k).getNodeName() == "number") {
					number = Integer.parseInt(vehicleInstanceChilds.item(k).getTextContent());
				} else if (vehicleInstanceChilds.item(k).getNodeName() == "capacity") {
					capacity = Integer.parseInt(vehicleInstanceChilds.item(k).getTextContent());
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Node does not content Integer");
			e.printStackTrace();
		} catch (DOMException e) {
			System.out.println("Cannot parse node content");
			e.printStackTrace();
		}
		result[0] = number;
		result[1] = capacity;

		return result;
	}

	private static Document buildDocument() throws Exception {
		File dir = new File("data");
		File input = new File(dir, "depot.xml");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		return dbf.newDocumentBuilder().parse(input);

	}

	private static Node parseVehiclesNode(NodeList rootChilds) {
		Root root = new Root();
		String mainName = null;
		Node vehiclesNode = null;

		for (int i = 0; i < rootChilds.getLength(); i++) {
			if (rootChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}

			switch (rootChilds.item(i).getNodeName()) {

			case "name": {
				mainName = rootChilds.item(i).getTextContent();
				break;
			}
			case "vehicles": {
				vehiclesNode = rootChilds.item(i);
				break;
			}
			}
		}

		root.setName(mainName);

		if (vehiclesNode == null) {
			return null;
		}
		return vehiclesNode;
	}
}
