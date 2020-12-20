//State is the node definition for A*
package manageStates;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class State {

    private Satelite SAT1;
    private Satelite SAT2;
    private String area[][];
    private int j;

    private State parent;
    private int costValue;
    private int heuristicValue;
    private String parentAction;
    private int functionValue;
    private boolean typeOfHeuristic;

    // por ahora sin heuristica es igual al cost
    public int getFunctionValue() {
        if(typeOfHeuristic) {
            this.functionValue = this.getCostValue() + this.getHeuristicValue1();
        }else {
            this.functionValue = this.getCostValue() + this.getHeuristicValue2();
        }
        return functionValue;
    }

    // Initial constructor
    public State(Satelite SAT1, Satelite SAT2, String area[][], boolean typeOfHeuristic) {
        this.SAT1 = SAT1;
        this.SAT2 = SAT2;
        this.area = area;
        this.j = 0;
        this.parent = null;
        this.costValue = 0;

        // pilas AA
        // pilas AA
        this.heuristicValue = 0;// no es cero ver cuando implementemos get heuristic
        this.functionValue = 0;
        this.parentAction = "";
        this.typeOfHeuristic = typeOfHeuristic;
    }

    public State(State copy) {
        this.SAT1 = new Satelite(copy.getSAT1());
        this.SAT2 = new Satelite(copy.getSAT2());

        this.area = copy.area2(copy);
        this.j = copy.getJ();
        this.parent = copy.getParent();
        this.costValue = copy.getCostValue();
        this.heuristicValue = copy.getHeuristicValue1();
        this.functionValue = copy.getFunctionValue();
        this.parentAction = copy.getParentAction();
        this.typeOfHeuristic = copy.typeOfHeuristic;
    }

    public ArrayList<State> getChildrens() {
        ArrayList<State> childrens = new ArrayList<State>();
        State aux = null;

        if ((aux = sat1ObserveSat2Nothing()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1ObserveSat2Observe()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1ObserveSat2Spin()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1ObserveSat2Charge()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1ObserveSat2Transmit()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1ChargeSat2Nothing()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1ChargeSat2Observe()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1ChargeSat2Spin()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1ChargeSat2Charge()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1ChargeSat2Transmit()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1TransmitSat2Nothing()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1TransmitSat2Observe()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1TransmitSat2Spin()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1TransmitSat2Charge()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1TransmitSat2Transmit()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1NothingSat2Nothing()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1NothingSat2Observe()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1NothingSat2Spin()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1NothingSat2Charge()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1NothingSat2Transmit()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1SpinSat2Nothing()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1SpinSat2Observe()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1SpinSat2Spin()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1SpinSat2Charge()) != null) {
            childrens.add(aux);
        }

        if ((aux = sat1SpinSat2Transmit()) != null) {
            childrens.add(aux);
        }

        return childrens;
    }

    private State sat1NothingSat2Nothing() {
        State chld = new State(this);
        chld.setParent(this);
        chld.setCostValue(this.getCostValue() + 1);
        if (!(this.getJ() == 11))
            chld.setJ(this.getJ() + 1);
        else {
            chld.setJ(0);
            // chld.setCostValue(chld.getCostValue() + 12);
        }

        chld.setParentAction("sat1NothingSat2Nothing");

        return chld;

    }

    private State sat1NothingSat2Observe() {
        State chld = new State(this);

        return sat2Observe(chld, "sat1NothingSat2Observe");
    }

    private State sat1NothingSat2Spin() {
        State chld = new State(this);

        if (this.getSAT2().getBattery() >= this.getSAT2().getSpinCost()) {
            chld.getSAT2().changeWatchArea();
            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction("sat1NothingSat2Spin");

            return chld;
        }
        return null;
    }

    private State sat1NothingSat2Charge() {
        State chld = new State(this);

        if (!(this.getSAT2().getBattery() == this.getSAT2().getMaxBattery())) {
            chld.getSAT2().chargeBattery();
            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction("sat1NothingSat2Charge");
            return chld;
        }

        return null;
    }

    private State sat1NothingSat2Transmit() {
        State chld = new State(this);

        if (this.getSAT2().getBattery() >= this.getSAT2().getTransmitionCost()
                && !(this.getSAT2().getObservations().isEmpty())) {
            chld.getSAT2().transmit();
            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction("sat1NothingSat2Transmit");

            return chld;
        }

        return null;
    }

    private State sat1SpinSat2Nothing() {
        State chld = new State(this);
        if (this.getSAT1().getBattery() >= this.getSAT1().getSpinCost()) {
            chld.getSAT1().changeWatchArea();
            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction("sat1SpinSat2Nothing");
            return chld;

        }

        return null;
    }

    private State sat1SpinSat2Observe() {
        State chld = new State(this);
        if (this.getSAT1().getBattery() >= this.getSAT1().getSpinCost()) {
            // Cambiamos primero sat1Spin
            chld.getSAT1().changeWatchArea();

            return sat2Observe(chld, "sat1SpinSat2Observe");
        }
        return null;
    }

    private State sat1SpinSat2Spin() {
        State chld = new State(this);
        if (this.getSAT1().getBattery() >= this.getSAT1().getSpinCost()
                && this.getSAT2().getBattery() >= this.getSAT2().getSpinCost()) {
            chld.getSAT1().changeWatchArea();
            chld.getSAT2().changeWatchArea();
            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction("sat1SpinSat2Spin");
            return chld;

        }
        return null;
    }

    private State sat1SpinSat2Charge() {
        State chld = new State(this);
        if (this.getSAT1().getBattery() >= this.getSAT1().getSpinCost()
                && !(this.getSAT2().getBattery() == this.getSAT2().getMaxBattery())) {
            chld.getSAT1().changeWatchArea();
            chld.getSAT2().chargeBattery();
            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction("sat1SpinSat2Charge");
            return chld;
        }

        return null;
    }

    private State sat1SpinSat2Transmit() {
        State chld = new State(this);
        if (this.getSAT1().getBattery() >= this.getSAT1().getSpinCost()
                && this.getSAT2().getBattery() >= this.getSAT2().getTransmitionCost()
                && !(this.getSAT2().getObservations().isEmpty())) {
            chld.getSAT1().changeWatchArea();
            chld.getSAT2().transmit();
            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction("sat1SpinSat2Transmit");
            return chld;

        }

        return null;
    }

    private State sat1ObserveSat2Nothing() {
        State chld = new State(this);
        return sat1Observe(chld, "sat1ObserveSat2Nothing");
    }

    private State sat1ObserveSat2Observe() {
        State chld = new State(this);

        if (this.getSAT1().getBattery() >= this.getSAT1().getObservationCost()
                && this.getSAT2().getBattery() >= this.getSAT2().getObservationCost()) {

            if (this.getSAT1().isWatchArea() && this.getSAT2().isWatchArea()) {
                // true true
                if (this.area[1][this.getJ()] != null && this.area[2][this.getJ()] != null) {
                    chld.getSAT1().pushObsevation(this.area[1][this.getJ()]);
                    chld.removeObservtioninArea(1, this.getJ());
                    chld.getSAT2().pushObsevation(this.area[2][this.getJ()]);
                    chld.removeObservtioninArea(2, this.getJ());
                } else {
                    return null;

                }
                chld.setParent(this);
                chld.setCostValue(this.getCostValue() + 1);
                if (!(this.getJ() == 11))
                    chld.setJ(this.getJ() + 1);
                else {
                    chld.setJ(0);
                    // chld.setCostValue(chld.getCostValue() + 12);
                }
                chld.setParentAction("sat1ObserveSat2Observe");
                return chld;

            } else if (this.getSAT1().isWatchArea() && !this.getSAT2().isWatchArea()) {
                // true false
                if ((this.area[1][this.getJ()] != null || this.area[2][this.getJ()] != null)
                        && (this.area[2][this.getJ()] != null || this.area[3][this.getJ()] != null)) {

                    if (this.area[1][this.getJ()] != null && this.area[2][this.getJ()] != null) {
                        chld.getSAT1().pushObsevation(this.area[1][this.getJ()]);
                        chld.removeObservtioninArea(1, this.getJ());
                        chld.getSAT2().pushObsevation(this.area[2][this.getJ()]);
                        chld.removeObservtioninArea(2, this.getJ());

                    } else if (this.area[1][this.getJ()] != null && this.area[3][this.getJ()] != null) {

                        chld.getSAT1().pushObsevation(this.area[1][this.getJ()]);
                        chld.removeObservtioninArea(1, this.getJ());
                        chld.getSAT2().pushObsevation(this.area[3][this.getJ()]);
                        chld.removeObservtioninArea(3, this.getJ());

                    } else if (this.area[2][this.getJ()] != null && this.area[3][this.getJ()] != null) {

                        chld.getSAT1().pushObsevation(this.area[2][this.getJ()]);
                        chld.removeObservtioninArea(2, this.getJ());
                        chld.getSAT2().pushObsevation(this.area[3][this.getJ()]);
                        chld.removeObservtioninArea(3, this.getJ());

                    } else {
                        return null;
                    }
                    chld.setParent(this);
                    chld.setCostValue(this.getCostValue() + 1);
                    if (!(this.getJ() == 11))
                        chld.setJ(this.getJ() + 1);
                    else {
                        chld.setJ(0);
                        // chld.setCostValue(chld.getCostValue() + 12);
                    }
                    chld.setParentAction("sat1ObserveSat2Observe");
                    return chld;
                }

            } else if (!this.getSAT1().isWatchArea() && this.getSAT2().isWatchArea()) {
                // False true
                if ((this.area[0][this.getJ()] != null || this.area[1][this.getJ()] != null)
                        && (this.area[1][this.getJ()] != null || this.area[2][this.getJ()] != null)) {

                    if (this.area[0][this.getJ()] != null && this.area[1][this.getJ()] != null) {

                        chld.getSAT1().pushObsevation(this.area[0][this.getJ()]);
                        chld.removeObservtioninArea(0, this.getJ());
                        chld.getSAT2().pushObsevation(this.area[1][this.getJ()]);
                        chld.removeObservtioninArea(1, this.getJ());

                    } else if (this.area[0][this.getJ()] != null && this.area[2][this.getJ()] != null) {

                        chld.getSAT1().pushObsevation(this.area[0][this.getJ()]);
                        chld.removeObservtioninArea(0, this.getJ());
                        chld.getSAT2().pushObsevation(this.area[2][this.getJ()]);
                        chld.removeObservtioninArea(2, this.getJ());

                    } else if (this.area[1][this.getJ()] != null && this.area[2][this.getJ()] != null) {
                        chld.getSAT1().pushObsevation(this.area[1][this.getJ()]);
                        chld.removeObservtioninArea(1, this.getJ());
                        chld.getSAT2().pushObsevation(this.area[2][this.getJ()]);
                        chld.removeObservtioninArea(2, this.getJ());

                    } else {
                        return null;
                    }
                    chld.setParent(this);
                    chld.setCostValue(this.getCostValue() + 1);
                    if (!(this.getJ() == 11))
                        chld.setJ(this.getJ() + 1);
                    else {
                        chld.setJ(0);
                        // chld.setCostValue(chld.getCostValue() + 12);
                    }
                    chld.setParentAction("sat1ObserveSat2Observe");
                    return chld;
                }

            } else {
                // false false
                if ((this.area[0][this.getJ()] != null || this.area[1][this.getJ()] != null)
                        && (this.area[2][this.getJ()] != null || this.area[3][this.getJ()] != null)) {

                    if (this.area[0][this.getJ()] != null && this.area[2][this.getJ()] != null) {

                        chld.getSAT1().pushObsevation(this.area[0][this.getJ()]);
                        chld.removeObservtioninArea(0, this.getJ());
                        chld.getSAT2().pushObsevation(this.area[2][this.getJ()]);
                        chld.removeObservtioninArea(2, this.getJ());

                    } else if (this.area[0][this.getJ()] != null && this.area[3][this.getJ()] != null) {

                        chld.getSAT1().pushObsevation(this.area[0][this.getJ()]);
                        chld.removeObservtioninArea(0, this.getJ());
                        chld.getSAT2().pushObsevation(this.area[3][this.getJ()]);
                        chld.removeObservtioninArea(3, this.getJ());

                    } else if (this.area[1][this.getJ()] != null && this.area[2][this.getJ()] != null) {

                        chld.getSAT1().pushObsevation(this.area[1][this.getJ()]);
                        chld.removeObservtioninArea(1, this.getJ());
                        chld.getSAT2().pushObsevation(this.area[2][this.getJ()]);
                        chld.removeObservtioninArea(2, this.getJ());

                    } else {
                        chld.getSAT1().pushObsevation(this.area[1][this.getJ()]);
                        chld.removeObservtioninArea(1, this.getJ());
                        chld.getSAT2().pushObsevation(this.area[3][this.getJ()]);
                        chld.removeObservtioninArea(3, this.getJ());

                    }

                    chld.setParent(this);
                    chld.setCostValue(this.getCostValue() + 1);
                    if (!(this.getJ() == 11))
                        chld.setJ(this.getJ() + 1);
                    else {
                        chld.setJ(0);
                        // chld.setCostValue(chld.getCostValue() + 12);
                    }
                    chld.setParentAction("sat1ObserveSat2Observe");
                    return chld;
                }

            }
        }
        return null;
    }

    private State sat1ObserveSat2Spin() {
        State chld = new State(this);
        if (this.getSAT2().getBattery() >= this.getSAT2().getSpinCost()) {
            // Cambiamos primero sat2Spin
            chld.getSAT2().changeWatchArea();

            return sat1Observe(chld, "sat1ObserveSat2Spin");
        }
        return null;
    }

    private State sat1ObserveSat2Charge() {
        State chld = new State(this);

        if (!(this.getSAT2().getBattery() == this.getSAT2().getMaxBattery())) {
            // Cambiamos primero sat2Charge
            chld.getSAT2().chargeBattery();

            return sat1Observe(chld, "sat1ObserveSat2Charge");
        }
        return null;
    }

    private State sat1ObserveSat2Transmit() {
        State chld = new State(this);

        if (this.getSAT2().getBattery() >= this.getSAT2().getTransmitionCost()
                && !(this.getSAT2().getObservations().isEmpty())) {
            chld.getSAT2().transmit();

            return sat1Observe(chld, "sat1ObserveSat2Transmit");
        }
        return null;
    }

    private State sat1ChargeSat2Nothing() {
        State chld = new State(this);
        if (!(this.getSAT1().getBattery() == this.getSAT1().getMaxBattery())) {
            chld.getSAT1().chargeBattery();
            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction("sat1ChargeSat2Nothing");
            return chld;
        }

        return null;
    }

    private State sat1ChargeSat2Observe() {
        State chld = new State(this);
        if (!(this.getSAT1().getBattery() == this.getSAT1().getMaxBattery())) {
            chld.getSAT1().chargeBattery();

            return sat2Observe(chld, "sat1ChargeSat2Observe");
        }

        return null;
    }

    private State sat1ChargeSat2Spin() {
        State chld = new State(this);
        if (!(this.getSAT1().getBattery() == this.getSAT1().getMaxBattery())
                && this.getSAT2().getBattery() >= this.getSAT2().getSpinCost()) {
            chld.getSAT1().chargeBattery();
            chld.getSAT1().changeWatchArea();
            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction("sat1ChargeSat2Spin");

            return chld;
        }
        return null;
    }

    private State sat1ChargeSat2Charge() {
        State chld = new State(this);
        if (!(this.getSAT1().getBattery() == this.getSAT1().getMaxBattery())
                && !(this.getSAT2().getBattery() == this.getSAT2().getMaxBattery())) {
            chld.getSAT1().chargeBattery();
            chld.getSAT2().chargeBattery();
            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction("sat1ChargeSat2Charge");
            return chld;
        }

        return null;

    }

    private State sat1ChargeSat2Transmit() {
        State chld = new State(this);
        if (!(this.getSAT1().getBattery() == this.getSAT1().getMaxBattery())
                && this.getSAT2().getBattery() >= this.getSAT2().getTransmitionCost()
                && !(this.getSAT2().getObservations().isEmpty())) {
            chld.getSAT1().chargeBattery();
            chld.getSAT2().transmit();
            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction("sat1ChargeSat2Transmit");
            return chld;
        }

        return null;
    }

    private State sat1TransmitSat2Nothing() {
        State chld = new State(this);
        if (this.getSAT1().getBattery() >= this.getSAT1().getTransmitionCost()
                && !(this.getSAT1().getObservations().isEmpty())) {
            chld.getSAT1().transmit();
            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction("sat1TransmitSat2Nothing");
            return chld;
        }
        return null;
    }

    private State sat1TransmitSat2Observe() {
        State chld = new State(this);
        if (this.getSAT1().getBattery() >= this.getSAT1().getTransmitionCost()
                && !(this.getSAT1().getObservations().isEmpty())) {
            chld.getSAT1().transmit();

            return sat2Observe(chld, "sat1TransmitSat2Observe");
        }

        return null;
    }

    private State sat1TransmitSat2Spin() {

        State chld = new State(this);
        if (this.getSAT1().getBattery() >= this.getSAT1().getTransmitionCost()
                && !(this.getSAT1().getObservations().isEmpty())
                && this.getSAT2().getBattery() >= this.getSAT2().getSpinCost()) {
            chld.getSAT1().transmit();
            chld.getSAT2().changeWatchArea();
            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction("sat1TransmitSat2Spin");
            return chld;
        }

        return null;
    }

    private State sat1TransmitSat2Charge() {
        State chld = new State(this);
        if (this.getSAT1().getBattery() >= this.getSAT1().getTransmitionCost()
                && !(this.getSAT1().getObservations().isEmpty())
                && !(this.getSAT2().getBattery() == this.getSAT2().getMaxBattery())) {
            chld.getSAT1().transmit();
            chld.getSAT2().chargeBattery();
            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction("sat1TransmitSat2Charge");
            return chld;
        }

        return null;
    }

    private State sat1TransmitSat2Transmit() {
        State chld = new State(this);
        if (this.getSAT1().getBattery() >= this.getSAT1().getTransmitionCost()
                && !(this.getSAT1().getObservations().isEmpty())
                && this.getSAT2().getBattery() >= this.getSAT2().getTransmitionCost()
                && !(this.getSAT2().getObservations().isEmpty())) {
            chld.getSAT1().transmit();
            chld.getSAT2().transmit();
            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction("sat1TransmitSat2Transmit");
            return chld;

        }
        return null;
    }

    public boolean isFinal() {
        if (isEmptyArea() && this.getSAT1().getObservations().isEmpty() && this.getSAT2().getObservations().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isEmptyArea() {
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                if (area[i][j] != null) {
                    return false;
                }
            }
        }
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

    public int getHeuristicValue1() {
        this.heuristicValue = (observationLeft()) + this.SAT1.getObservations().size()
                + this.SAT2.getObservations().size();
        return heuristicValue;
    }
    
    public int getHeuristicValue2() {
        this.heuristicValue = (observationLeft());
        return heuristicValue;
    }

    public void setHeuristicValue(int heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

    // Remueve de la matriz de strings
    private void removeObservtioninArea(int i, int j) {
        this.area[i][j] = null;
    }

    public String getParentAction() {
        return parentAction;
    }

    public void setParentAction(String parentAction) {
        this.parentAction = parentAction;
    }

    private State sat1Observe(State chld, String parentAction) {
        // Si mira el whatchArea TRUE y hay una observacion y tiene energia para
        // observar
        if (this.getSAT1().isWatchArea() && ((this.area[2][this.getJ()] != null) || (this.area[1][this.getJ()] != null))
                && (this.getSAT1().getBattery() >= this.getSAT1().getObservationCost())) {
            // Compartida
            if (this.area[2][this.getJ()] != null) {
                chld.getSAT1().pushObsevation(this.area[2][this.getJ()]);
                chld.removeObservtioninArea(2, this.getJ());

            } else {
                chld.getSAT1().pushObsevation(this.area[1][this.getJ()]);
                chld.removeObservtioninArea(1, this.getJ());
            }

            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction(parentAction);

            return chld;

            // Si mira el whatchArea FALSE y hay una observación y tiene energia para
            // observar
        } else if (!this.SAT1.isWatchArea()
                && ((this.area[0][this.getJ()] != null) || (this.area[1][this.getJ()] != null))
                && (this.getSAT1().getBattery() >= this.getSAT1().getObservationCost())) {
            // No compartida
            if (this.area[0][this.getJ()] != null) {
                chld.getSAT1().pushObsevation(this.area[0][this.getJ()]);
                chld.removeObservtioninArea(0, this.getJ());

            } else {
                chld.getSAT1().pushObsevation(this.area[1][this.getJ()]);
                chld.removeObservtioninArea(1, this.getJ());
            }

            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }

            chld.setParentAction(parentAction);

            return chld;
        }
        return null;
    }

    private State sat2Observe(State chld, String parentAction) {
        // Si mira el whatchArea TRUE y hay una observacion y tiene energia para
        // observar
        if (this.getSAT2().isWatchArea() && ((this.area[2][this.getJ()] != null) || (this.area[1][this.getJ()] != null))
                && (this.getSAT2().getBattery() >= this.getSAT2().getObservationCost())) {
            // Compartida
            if (this.area[2][this.getJ()] != null) {
                chld.getSAT2().pushObsevation(this.area[2][this.getJ()]);
                chld.removeObservtioninArea(2, this.getJ());

            } else {
                chld.getSAT2().pushObsevation(this.area[1][this.getJ()]);
                chld.removeObservtioninArea(1, this.getJ());
            }

            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }
            chld.setParentAction(parentAction);

            return chld;

            // Si mira el whatchArea FALSE y hay una observación y tiene energia para
            // observar
            // No tiene que tener el this???
            // No tiene que tener el this???
            // No tiene que tener el this???

        } else if (!this.SAT2.isWatchArea()
                && ((this.area[3][this.getJ()] != null) || (this.area[2][this.getJ()] != null))
                && (this.getSAT2().getBattery() >= this.getSAT2().getObservationCost())) {
            // No compartida
            if (this.area[3][this.getJ()] != null) {
                chld.getSAT2().pushObsevation(this.area[3][this.getJ()]);
                chld.removeObservtioninArea(3, this.getJ());

            } else {
                chld.getSAT2().pushObsevation(this.area[2][this.getJ()]);
                chld.removeObservtioninArea(2, this.getJ());
            }

            chld.setParent(this);
            chld.setCostValue(this.getCostValue() + 1);
            if (!(this.getJ() == 11))
                chld.setJ(this.getJ() + 1);
            else {
                chld.setJ(0);
                // chld.setCostValue(chld.getCostValue() + 12);
            }

            chld.setParentAction(parentAction);

            return chld;
        }
        // Si SAT2 no puede observar
        return null;
    }

    private int observationLeft() {
        int count = 0;
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                if (area[i][j] != null) {
                    count++;
                }
            }
        }
        return count;
    }

    private String areaToString() {
        String result = "";
        for (int i = 0; i < this.getArea().length; i++) {
            for (int j = 0; j < this.getArea()[i].length; j++) {
                if (this.getArea()[i][j] != null)
                    result += this.getArea()[i][j] + "\t";
                else {
                    result += "00\t";
                }
            }
            result += "\n";
        }
        return result;
    }

    public String toString() {
        return "Franja horaria: " + this.getJ() + "\n" + "f(n) = " + this.getFunctionValue() + "\n" + "Parent Action = "
                + this.parentAction + "\n" + "Sat1: Battery = " + this.SAT1.getBattery() + "\n" + "\tObservations = "
                + this.SAT1.getObservations() + "\n" + "\tWatchArea = " + this.SAT1.isWatchArea() + "\n"
                + "Sat2: Battery = " + this.SAT2.getBattery() + "\n" + "\tObservations = " + this.SAT2.getObservations()
                + "\n" + "\tWatchArea = " + this.SAT2.isWatchArea() + "\n" + areaToString();
    }

    private String[][] area2(State a) {
        String aa[][] = new String[a.getArea().length][a.getArea()[0].length];
        for (int i = 0; i < aa.length; i++) {
            for (int j = 0; j < aa[i].length; j++) {
                aa[i][j] = a.getArea()[i][j];
            }
        }

        return aa;
    }

    public String getStateHash() {
        String stateString = "Franja horaria: " + this.getJ() + "\n" + "Sat1: Battery = " + this.SAT1.getBattery()
                + "\n" + "\tObservations = " + this.SAT1.getObservations() + "\n" + "\tWatchArea = "
                + this.SAT1.isWatchArea() + "\n" + "Sat2: Battery = " + this.SAT2.getBattery() + "\n"
                + "\tObservations = " + this.SAT2.getObservations() + "\n" + "\tWatchArea = " + this.SAT2.isWatchArea()
                + "\n" + areaToString();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     
        md.update(stateString.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();

        // Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it
        // should be "%064x"
        String hex = String.format("%32x", new BigInteger(1, digest));
        return hex;
    }
    
    public ArrayList<State> getPath() {
        State finalState = this;
        ArrayList<State> camino = new ArrayList<State>();
        while(finalState.getParent() != null) {
            camino.add(finalState);
            finalState = finalState.getParent();
        }
        camino.add(finalState);
        return camino;
    }

}
