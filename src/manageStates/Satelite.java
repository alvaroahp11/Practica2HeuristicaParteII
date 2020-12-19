package manageStates;
import java.util.ArrayList;

public class Satelite {

	private int battery;
    private boolean watchArea; // false la franja es unica y compartida, true para la franja compartida
	private ArrayList<String> observations;
	private int observationCost;
	private int transmitionCost;
	private int spinCost;
	private int chargeUnit;
	private int maxBattery;

	



    //Solo se usa para el estado inicial
	public Satelite(int battery, int observationCost, int transmitionCost, int spinCost, int chargeUnit) {
		this.battery = battery;
		this.watchArea = false;
		this.observations = new ArrayList<String>();
		this.setObservationCost(observationCost);
		this.setTransmitionCost(transmitionCost);
		this.spinCost = spinCost;
		this.chargeUnit = chargeUnit;
		this.maxBattery = battery;	
		
	}
	
    public Satelite(Satelite copy) {
		this.battery = copy.getBattery();
		this.watchArea = copy.isWatchArea();
		this.observations = (ArrayList<String>) copy.getObservations().clone();
		this.setObservationCost(copy.getObservationCost());
		this.setTransmitionCost(copy.getTransmitionCost());
		this.spinCost = copy.getSpinCost();
		this.chargeUnit = copy.getChargeUnit();
		this.maxBattery = copy.getMaxBattery();	
	}

	
	
	public int getMaxBattery() {
        return maxBattery;
    }

    public int getBattery() {
		return battery;
	}

	public boolean isWatchArea() {
		return watchArea;
	}

	public String popObservations() {
	    if(observations.size() > 0)
	        return observations.get(0);
	    return null;
	}

	// Add observation to a list
	public void pushObsevation(String observation) {
		if (!(observation == null || observation == "")) {
	          this.battery-= this.observationCost;
			observations.add(observation);

		}			
	}
	
	//add battery 
	public void chargeBattery() {
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
	
	public void transmit() {
		this.observations.remove(0);
		this.battery-= this.transmitionCost;
	}
	

    public int getTransmitionCost() {
        return transmitionCost;
    }

    public void setTransmitionCost(int transmitionCost) {
        this.transmitionCost = transmitionCost;
    }

    public int getObservationCost() {
        return observationCost;
    }

    public void setObservationCost(int observationCost) {
        this.observationCost = observationCost;
    }
    
    public int getSpinCost() {
        return spinCost;
    }

    public void setSpinCost(int spinCost) {
        this.spinCost = spinCost;
    }

    public int getChargeUnit() {
        return chargeUnit;
    }

    public void setChargeUnit(int chargeUnit) {
        this.chargeUnit = chargeUnit;
    }


    public void setWatchArea(boolean watchArea) {
        this.watchArea = watchArea;
    }



	public ArrayList<String> getObservations() {
		return observations;
	}




    
  

}
