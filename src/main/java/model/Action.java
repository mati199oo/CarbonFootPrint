package model;

import java.util.ArrayList;

/**
 * Created by Ja on 2016-11-07.
 */
public class Action {

    private String title;
    private String type;
    private ArrayList<Parameter> parameters = new ArrayList<Parameter>();
    private String method;
    private ArrayList<String> footprints = new ArrayList<String>();

    private ArrayList<Action> footprintActions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Parameter> parameters) {
        this.parameters = parameters;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public ArrayList<String> getFootprints() {
        return footprints;
    }

    public void setFootprints(ArrayList<String> footprints) {
        this.footprints = footprints;
    }

    public ArrayList<Action> getFootprintActions() {
        return footprintActions;
    }

    public void setFootprintActions(ArrayList<Action> footprintActions) {
        this.footprintActions = footprintActions;
    }

}