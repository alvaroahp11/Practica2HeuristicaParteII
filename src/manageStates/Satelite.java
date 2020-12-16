package manageStates;
import java.util.ArrayList;

import exceptions.ManageStateException;

public class Satelite {

	private int battery;
	private boolean watchArea; // false la franja es unica y compartida, true para la franja compartida
	private ArrayList<String> observations;
	private int observationCost;
	private int transmitionCost;
	private int spinCost;
	private int chargeUnit;

	public Satelite(int battery, int observationCost, int transmitionCost, int spinCost, int chargeUnit) {
		this.battery = battery;
		this.watchArea = true;
		this.observations = new ArrayList<String>();
		this.observationCost = observationCost;
		this.transmitionCost = transmitionCost;
		this.spinCost = spinCost;
		this.chargeUnit = chargeUnit;
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
	public void addObsevation(String observation) throws ManageStateException {
		if (!(observation == null || observation == "")) {
			observations.add(observation);

		}else 
			throw new ManageStateException("Trying to ad a null or empty observation");
	}
	
	//add battery 
	public void chargeBatter() {
		this.battery+=this.chargeUnit;
	}
	
	//Change watch area and uncharge battery
	public void changeWatchArea() {
		if(this.watchArea) {
			this.watchArea = false;
			this.battery-= this.spinCost;
		}else {
			this.watchArea = true;
			this.battery -= this.spinCost;
		}
	}

}
