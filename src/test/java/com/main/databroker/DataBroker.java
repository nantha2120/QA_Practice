package com.main.databroker;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

public class DataBroker {
	private String CSV_PATH= "src/test/resources/data.csv";
	public String[] ReadData() {
		// Retrieving data from CSV file
		String HomePageURL = null;
		String UserName = null;
		String Password = null;
		String Invalidusername = null;

		try {
			CSVReader reader = new CSVReader(new FileReader(CSV_PATH));
			String[] csvCell;

			// Retrieving data from a CSV file.
			csvCell = reader.readNext();
			csvCell = reader.readNext();
			HomePageURL = csvCell[0];
			UserName = csvCell[1];
			Password = csvCell[2];
			Invalidusername = csvCell[3];
			
			csvCell = reader.readNext();
			csvCell = reader.readNext();
			
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] RetrivedData = { HomePageURL, UserName, Password, Invalidusername };
		return RetrivedData;
	}

}
