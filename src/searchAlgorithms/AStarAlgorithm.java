package searchAlgorithms;

import java.util.*;

import manageStates.*;

public class AStarAlgorithm {

    public static State aStartInit(State state) {
        PriorityQueue<State> openList = new PriorityQueue<State>(new Comparator<State>() {
            // override compare method
            public int compare(State i, State j) {
                if (i.getFunctionValue() > j.getFunctionValue()) {
                    return 1;
                }

                else if (i.getFunctionValue() < j.getFunctionValue()) {
                    return -1;
                }

                else {
                    return 0;
                }
            }

        });
        openList.add(state);
        ArrayList<State> closeList = new ArrayList<State>();
        boolean success = false;

        State aux = null;
        while (!(openList.isEmpty()) && success != true) {
            aux = openList.poll();
            if (!(closeList.contains(aux))) {
                if (aux.isFinal()) {
                    success = true;
                } else {
                    ArrayList<State> childrens = aux.getChildrens();
                    while (!(childrens.isEmpty())) {
                        openList.add(childrens.remove(0));
                    }
                    closeList.add(aux);
                }
            }

        }

        if (success) {
            return aux;
        }

        return null;
    }

}
