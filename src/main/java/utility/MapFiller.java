package utility;

import model.Action;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ja on 2016-12-13.
 */
public class MapFiller {

    public void addTypeToMap(HashMap<String, ArrayList<String>> availableTypes, String type, String title) {
        if (!availableTypes.containsKey(type)) {
            ArrayList<String> titles = new ArrayList<String>();
            titles.add(title);
            availableTypes.put(type, titles);
        } else {
            availableTypes.get(type).add(title);
        }
    }

    public void addActionToMap(HashMap<String, Action> availableActions, String title, Action action) {
        availableActions.put(title, action);
    }

    public void addParameterToMap(HashMap<String, ArrayList<Double>> availableParameters, String name, Double value) {
        if (!availableParameters.containsKey(name)) {
            ArrayList<Double> values = new ArrayList<Double>();
            values.add(value);
            availableParameters.put(name, values);
        } else {
            availableParameters.get(name).add(value);
        }
    }

}