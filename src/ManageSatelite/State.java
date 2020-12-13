package ManageSatelite;
public class State {
	
	private Satelite SAT1;
	private Satelite SAT2;
	private boolean area [][];

    public State (Satelite SAT1, Satelite SAT2, boolean area[][]) {
    	this.SAT1 = SAT1;
    	this.SAT2 = SAT2;
    	this.area = area;
    }

}
