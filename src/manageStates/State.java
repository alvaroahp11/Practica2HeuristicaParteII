//State is the node definition for A*
package manageStates;

import java.util.ArrayList;

public class State {
	
	private Satelite SAT1;
	private Satelite SAT2;
	private boolean area [][];
	private int j;

	
	private State parent;
	private int costValue;
	private int heuristicValue;


	//Initial constructor
    public State (Satelite SAT1, Satelite SAT2, boolean area[][]) {
    	this.SAT1 = SAT1;
    	this.SAT2 = SAT2;
    	this.area = area;
		this.j = 0;
    }

	
    
    
    
    public ArrayList<State> getChildrens(){
        
        return null;
    }


	private State sat1NadaSat2Nada (){
		State chld = this;
		chld.setParent(this);
		chld.setCostValue(this.getCostValue() + 1);
		chld.setJ(this.getJ() + 1);

		return chld;
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

	public boolean[][] getArea() {
		return area;
	}

	public void setArea(boolean[][] area) {
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
}
