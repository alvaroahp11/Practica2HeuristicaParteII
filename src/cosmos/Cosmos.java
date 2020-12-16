package cosmos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import manageStates.*;
import exceptions.CosmosException;

public class Cosmos {

	public static void checkArguments(String args[]) throws CosmosException {
		// Only 2 arguments
		if (args.length != 2) {
			throw new CosmosException("\tThe structure must be:\n ./cosmos.java <problema.prob> <heuristica>");
		} else if (!args[1].equals("heuristica1") && !args[1].equals("heuristica2")) { // La heuristica esta definida
			throw new CosmosException("La heuristica no esta definida");
		}

	}

	public static String[] readFile(File f) throws CosmosException {
		String data[] = new String[3];
		try {
			Scanner myReader = new Scanner(f);

			for (int i = 0; myReader.hasNextLine() == true && i < 3; i++) {
				data[i] = myReader.nextLine();
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			throw new CosmosException("Cant find the file: " + f.getPath());
		}
		return data;
	}
	//Comprobamos que tiene el formato y eliminamos lo que va antes de cada linea
	public static String[] dataFilter(String data[]) throws CosmosException {

		for (int i = 0; i < data.length; i++) {
			if (data[i].equals(null)) {
				throw new CosmosException("Wrong input format \n\tMust be like: \n" + "OBS: (i,j);(i,j); ... ;(i,j)\r\n"
						+ "SAT1: 1;1;1;1;nbattery\r\n" + "SAT2: 1;1;1;1;nbattery");
			}
			String line[] = data[i].split(":");
			if (!((line[0].equals("OBS") && i == 0) || (line[0].equals("SAT1") && i == 1)
					|| (line[0].equals("SAT2") && i == 2))) {
				throw new CosmosException("Wrong input format \n\tMust be like: \n" + "OBS: (i,j);(i,j); ... ;(i,j)\r\n"
						+ "SAT1: 1;1;1;1;nbattery\r\n" + "SAT2: 1;1;1;1;nbattery");
			}
			data[i] = line[1];
		}
		return data;
	}

	public static State firstState(String data[]) {
		State intialState = null;
		Satelite SAT1 = null;
		Satelite SAT2 = null;
		boolean area[][] = new boolean[4][12];
			
				
		// String line[] = data[1].split(";");
		// for(int j=0; j< line.length; j++) {
		// 	System.out.println(line);
		// }

		// String coordinate[] = data[2].split(";");
		// for (int j = 0; j < coordinate.length; j++) {
		// 	coordinate[j];
		// 	System.out.println(coordinate[j]);
		// }	

	
		String coordinate[] = data[0].split(";");

		
		for (int j = 0; j < coordinate.length; j++) {
			int start = (coordinate[j].indexOf("(") + 1);
			int end = coordinate[j].indexOf(",");			
			int x = Integer.parseInt(coordinate[j].substring(start, end));


			coordinate[j] = coordinate[j].substring(end + 1);
			end = coordinate[j].indexOf(")") - 1;
			System.out.println(coordinate[j].substring(end));
		
			//int y = Integer.parseInt(coordinate[j].substring(end));

			//System.out.println(x + " " + y);


		}	
		

		

		return intialState;
	}

	public static void main(String args[]) throws CosmosException, FileNotFoundException {

		// Check the arguments
		checkArguments(args);

		// Open the file
		File input;
		try {
			input = new File(args[0]);
		} catch (NullPointerException e) {
			throw new CosmosException("Input File is null");
		}
		// Comprobar que el file existe
		if (!input.exists()) {
			throw new CosmosException("The file " + input.getPath() + " doesnt exists");
		}

		// Data from file
		String data[] = readFile(input);

		// Check syntax of file
		try {
			dataFilter(data);

		} catch (CosmosException e) {
			throw e;
		}

		State initialState = firstState(data);

	}

}
