package ManageSatelite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Exceptions.ManageSateliteException;

public class Cosmos {
	
	public static void checkArguments(String args[]) throws ManageSateliteException {
		//Only 2 arguments
		if(args.length != 2) {
			throw new ManageSateliteException("The structure is ./cosmos.java <problema.prob> <heuristica>");
		}else if(!args[1].equals("heuristica1") && !args[1].equals("heuristica2")) { //La heuristica esta definida
			throw new ManageSateliteException("La heuristica no esta definida");
		}
		
	}
	
	public static String[] readFile(File f) throws FileNotFoundException {
		String data[] = new String[3];
		try {
			Scanner myReader = new Scanner(f);
			
			for(int i=0; myReader.hasNextLine() == true && i<3; i++) {
				data[i] = myReader.nextLine();
			}
			myReader.close();
		}catch(FileNotFoundException e) {
			System.out.println("Cant read the file");
		}
		
		return data;
	}
	
	public static void getInfo(String data[]) {
		
	}

	public static void main (String args[]) throws ManageSateliteException, FileNotFoundException {
		
		//Check the arguments
		checkArguments(args);
		
		//Open the file
		File input;
		try {
			input = new File(args[0]);
		}catch (NullPointerException e) {
			throw new ManageSateliteException("Input File is null");
		}
		//Comprobar que el file existe
		if(!input.exists()) {
			throw new ManageSateliteException("The file doesnt exists");
		}
		
		//Data from file
		String data[] = readFile(input);
		for(int i =0; i< data.length; i++) {
			System.out.println(data[i]);
		}
		
	}
	
}
