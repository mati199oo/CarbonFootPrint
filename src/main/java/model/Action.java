package model;

import java.util.ArrayList;

/**
 * Created by Ja on 2016-11-07.
 */
public class Action {

    private String title;
    private double carbon;
    private ArrayList<String> substitutes;
    private ArrayList<String> footprints;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCarbon() {
        return carbon;
    }

    public void setCarbon(double carbon) {
        this.carbon = carbon;
    }

    public ArrayList<String> getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(ArrayList<String> substitutes) {
        this.substitutes = substitutes;
    }

    public ArrayList<String> getFootprints() {
        return footprints;
    }

    public void setFootprints(ArrayList<String> footprints) {
        this.footprints = footprints;
    }

}