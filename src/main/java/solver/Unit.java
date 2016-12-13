package solver;

import model.Action;

/**
 * Created by Ja on 2016-12-12.
 */
public class Unit {

    private Action solution;
    private double footprint;

    public Action getSolution() {
        return solution;
    }

    public void setSolution(Action solution) {
        this.solution = solution;
    }

    public double getFootprint() {
        return footprint;
    }

    public void setFootprint(double footprint) {
        this.footprint = footprint;
    }

}