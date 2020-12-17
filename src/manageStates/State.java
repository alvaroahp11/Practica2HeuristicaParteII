//State is the node definition for A*
package manageStates;

import java.util.ArrayList;

public class State {
	
	private Satelite SAT1;
	private Satelite SAT2;
	private String area [][];
	private int j;

	
	private State parent;
	private int costValue;
	private int heuristicValue;
	//private int obsevedNum;
	private String parentAction;


	//Initial constructor
    public State (Satelite SAT1, Satelite SAT2, String area[][]) {
    	this.SAT1 = SAT1;
    	this.SAT2 = SAT2;
    	this.area = area;
		this.j = 0;
		//Que hace
		//this.setObsevedNum(0);
		this.parentAction = "";
    }

	
    
    
    
    public ArrayList<State> getChildrens(){
        
        return null;
    }


	private State sat1NothingSat2Nothing(){
		State chld = this;
		chld.setParent(this);
		chld.setCostValue(this.getCostValue() + 1);
		if(!(this.getJ() == 11))
		    chld.setJ(this.getJ() + 1);
		else 
		    chld.setJ(0);

		chld.setParentAction("sat1NothingSat2Nothing");
		    
		return chld;

	}
	
	//EN CASO DE ERROR COMPROBAR AQUI, SENTIMOS QUE NOS FALTA ALGO
	//EN CASO DE ERROR COMPROBAR AQUI, SENTIMOS QUE NOS FALTA ALGO
	//EN CASO DE ERROR COMPROBAR AQUI, SENTIMOS QUE NOS FALTA ALGO
	//EN CASO DE ERROR COMPROBAR AQUI, SENTIMOS QUE NOS FALTA ALGO
	private State sat1NothingSat2Observe(){
		State chld = this;		
		chld.setParent(this);
        chld.setCostValue(this.getCostValue() + 1);
		if(!(this.getJ() == 11))
            chld.setJ(this.getJ() + 1);
        else 
            chld.setJ(0);

		
		if(chld.getSAT2().isWatchArea() && ((area[2][chld.getJ()] != null) || (area[1][chld.getJ()] != null)) && (chld.getSAT2().getBattery() >= chld.getSAT2().getObservationCost())){
			//Compartida 
		    if(area[2][chld.getJ()] != null){
		        chld.getSAT2().pushObsevation(area[2][chld.getJ()]);
				chld.removeObservtioninArea(2, chld.getJ());
				
			}else {
			    chld.getSAT2().pushObsevation(area[1][chld.getJ()]);
				chld.removeObservtioninArea(1, chld.getJ());				
			}
		    return chld;
			
		}else if(!chld.SAT2.isWatchArea() && ((area[3][chld.getJ()] != null) || (area[2][chld.getJ()] != null)) && (chld.getSAT2().getBattery() >= chld.getSAT2().getObservationCost())){
			//No compartida
		    if(area[3][chld.getJ()] != null){
                chld.getSAT2().pushObsevation(area[3][chld.getJ()]);
                chld.removeObservtioninArea(3, chld.getJ());
                
            }else {
                chld.getSAT2().pushObsevation(area[2][chld.getJ()]);
                chld.removeObservtioninArea(2, chld.getJ());                
            }
            return chld;
		}
		//Si SAT2 no puede observar
		return null;
	}

	
	private State sat1NothingSat2Spin(){
	    State chld = this;     
        chld.setParent(this);
        chld.setCostValue(this.getCostValue() + 1);
        if(!(this.getJ() == 11))
            chld.setJ(this.getJ() + 1);
        else 
            chld.setJ(0);
        
        
        if(chld.getSAT2().getBattery() >= chld.getSAT2().getSpinCost()) {
			chld.getSAT2().changeWatchArea();
			return chld;
        }   
        return null;
    }

	private State sat1NothingSat2Charge(){
		State chld = this;     
        chld.setParent(this);
        chld.setCostValue(this.getCostValue() + 1);
        if(!(this.getJ() == 11))
            chld.setJ(this.getJ() + 1);
        else 
            chld.setJ(0);
		
		if(!(chld.getSAT2().getBattery() == chld.getSAT2().getMaxBattery())) {
		    chld.getSAT2().chargeBattery();
		}
        
        return null;
    }

	private State sat1NothingSat2Transmit(){
       State chld = this;     
        chld.setParent(this);
        chld.setCostValue(this.getCostValue() + 1);
        if(!(this.getJ() == 11))
            chld.setJ(this.getJ() + 1);
        else 
            chld.setJ(0);
        
        if(chld.getSAT2().getBattery() >= chld.getSAT2().getTransmitionCost()) {
            
        }
        
        return null;
    }

	private State sat1SpinSat2Nothing(){
        
        return null;
    }

	private State sat1SpinSat2Observe(){
        
        return null;
    }
	private State sat1SpinSat2Spin(){
        
        return null;
    }
	private State sat1SpinSat2Charge(){
        
        return null;
    }
	private State sat1SpinSat2Transmit(){
        
        return null;
    }

	private State sat1ObserveSat2Nothing(){
        
        return null;
    }
	private State sat1ObserveSat2Observe(){
        
        return null;
    }
	private State sat1ObserveSat2Spin(){
        
        return null;
    }
	private State sat1ObserveSat2Charge(){
        
        return null;
    }
	private State sat1ObserveSat2Transmit(){
        
        return null;
    }
	private State sat1ChargeSat2Nothing(){
        
        return null;
    }
	private State sat1ChargeSat2Observe(){
        
        return null;
    }
	private State sat1ChargeSat2Spin(){
        
        return null;
    }
	private State sat1ChargeSat2Charge(){
        
        return null;
    }
	private State sat1ChargeSat2Transmit(){
        
        return null;
    }
	private State sat1TransSat2Nothing(){
        
        return null;
    }
	private State sat1TransSat2Observe(){
        
        return null;
    }
	private State sat1TransSat2Spin(){
        
        return null;
    }
	private State sat1TransSat2Charge(){
        
        return null;
    }
	private State sat1TransSat2Transmit(){
        
        return null;
    } 


	public boolean isFinal(){
		return true;
	}
	

	public Satelite getSAT1() {
		return SAT1;
	}

	public void setSAT1(Satelite sAT1) {
		SAT1 = sAT1;
	}

	public Satelite getSAT2() {
		return SAT2;
	}

	public void setSAT2(Satelite sAT2) {
		SAT2 = sAT2;
	}

	public String[][] getArea() {
		return area;
	}

	public void setArea(String[][] area) {
		this.area = area;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public State getParent() {
		return parent;
	}

	public void setParent(State parent) {
		this.parent = parent;
	}

	public int getCostValue() {
		return costValue;
	}

	public void setCostValue(int costValue) {
		this.costValue = costValue;
	}

	public int getHeuristicValue() {
		return heuristicValue;
	}

	public void setHeuristicValue(int heuristicValue) {
		this.heuristicValue = heuristicValue;
	}




//
//    public int getObsevedNum() {
//        return obsevedNum;
//    }
//
//
//
//
//
//    public void setObsevedNum(int obsevedNum) {
//        this.obsevedNum = obsevedNum;
//    }

	private void removeObservtioninArea(int i, int j){
		this.area[i][j] = null;
	}



    public String getParentAction() {
        return parentAction;
    }


    public void setParentAction(String parentAction) {
        this.parentAction = parentAction;
    }
}
