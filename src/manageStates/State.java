//State is the node definition for A*
package manageStates;

import java.util.ArrayList;

public class State {
	
	private Satelite SAT1;
	private Satelite SAT2;
	private boolean area [][];
	
	private State parent;
	private int costValue;
	private int heuristicValue;

    public State (Satelite SAT1, Satelite SAT2, boolean area[][]) {
    	this.SAT1 = SAT1;
    	this.SAT2 = SAT2;
    	this.area = area;
    }
    
    
    
    public ArrayList<State> getChildrens(){
        
        return null;
    }
    
    

}
