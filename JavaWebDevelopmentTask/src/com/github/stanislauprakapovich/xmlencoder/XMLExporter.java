package com.github.stanislauprakapovich.xmlencoder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.github.stanislauprakapovich.model.Train;

public class XMLExporter {
	public static void export(List<Train> trains) throws Exception {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder build = dbf.newDocumentBuilder();
		Document doc = build.newDocument();

		Element root = doc.createElement("root");
		doc.appendChild(root);

		Element _trains = doc.createElement("trains");
		_trains.appendChild(root);

		for (int i = 0; i < trains.size(); i++) {
			Element train = doc.createElement("train");
			train.appendChild(_trains);
			for (int j = 0; j < trains.get(i).size(); j++) {
				Element vehicle = doc.createElement("vehicle");
				vehicle.appendChild(train);

				Element number = doc.createElement("number");
				number.appendChild(doc.createTextNode(String.valueOf(trains.get(i).getVehicle(j).getNumber())));
				vehicle.appendChild(number);

				Element capacity = doc.createElement("capacity");
				capacity.appendChild(doc.createTextNode(String.valueOf(trains.get(i).getVehicle(j).getCapacity())));
				vehicle.appendChild(capacity);
				
				save(doc);
			}
		}
	}
	
	private static void save(Document doc) {
		TransformerFactory tranFactory = TransformerFactory.newInstance();
        try {
			Transformer aTransformer = tranFactory.newTransformer();
			  aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

		        aTransformer.setOutputProperty(
		                "{http://xml.apache.org/xslt}indent-amount", "4");
		        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

		        DOMSource source = new DOMSource(doc);
		        
		        File dir = new File("data"); 
		        File output = new File(dir, "trains.xml");
		        FileWriter fos = new FileWriter(output);
	            StreamResult result = new StreamResult(fos);
	            aTransformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error outputting document");
			e.printStackTrace();
		} catch (TransformerException e) {
	        System.out.println("Error building document");
	        e.printStackTrace();
		}

	}

}
