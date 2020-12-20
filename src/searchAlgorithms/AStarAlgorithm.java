package searchAlgorithms;

import java.util.*;

import manageStates.*;

public class AStarAlgorithm {
    private int expandedNodes;
    

    public State aStartInit(State state) {
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
        ArrayList<String> closeList = new ArrayList<String>();
        boolean success = false;

        State aux = null;
        while (!(openList.isEmpty()) && success != true) {
            aux = openList.poll();
            if (!(closeList.contains(aux.getStateHash()))) {
                //System.out.println(aux);            
                if (aux.isFinal()) {
                    success = true;
                    closeList.add(aux.getStateHash());
                } else {
                    ArrayList<State> childrens = aux.getChildrens();
                    while (!(childrens.isEmpty())) {
                        openList.add(childrens.remove(0));
                    }
                    closeList.add(aux.getStateHash());
                }
            }

        }
        if (success) {
            this.expandedNodes = closeList.size();
            return aux;
        }

        return null;
    }


    public int getExpandedNodes() {
        return expandedNodes;
    }

}
