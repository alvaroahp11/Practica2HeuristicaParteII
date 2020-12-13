package ManageSatelite;
import java.util.ArrayList;

import Exceptions.ManageSateliteException;

public class Satelite {

	private int battery;
	private boolean watchArea; // false la franja es unica y compartida, true para la franja compartida
	private ArrayList<String> observations;

	public Satelite(int battery, boolean watchArea) {
		this.battery = battery;
		this.watchArea = watchArea;
		this.observations = new ArrayList<String>();
	}

	public int getBattery() {
		return battery;
	}

	public boolean isWatchArea() {
		return watchArea;
	}

	public ArrayList<String> getObservations() {
		return observations;
	}

	// Add observation to a list
	public void addObsevation(String observation) throws ManageSateliteException {
		if (!(observation == null || observation == "")) {
			observations.add(observation);

		}else 
			throw new ManageSateliteException("Trying to ad a null or empty observation");
	}
	
	//add battery unit
	public void chargeBatter() {
		this.battery+=1;
	}
	
	//Change watch area
	public void changeWatchArea() {
		if(this.watchArea) {
			this.watchArea = false;
		}else {
			this.watchArea = true;
		}
	}

}
