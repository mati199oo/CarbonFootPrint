package utility;

import model.Action;
import model.Parameter;
import solver.Unit;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ja on 2016-12-13.
 */
public class Analyzer {

    private MapFiller mapFiller = new MapFiller();

    public void findAvailableTypesAndParameters(HashMap<String, ArrayList<String>> availableTypes, HashMap<String, ArrayList<Double>> availableParameters,
                                                 HashMap<String, Action> availableActions, Unit unit1, Unit unit2) {
        analyzeAction(availableTypes, availableParameters, availableActions, unit1.getSolution());
        analyzeAction(availableTypes, availableParameters, availableActions, unit2.getSolution());
    }

    private void analyzeAction(HashMap<String, ArrayList<String>> availableTypes, HashMap<String, ArrayList<Double>> availableParameters, HashMap<String, Action> availableActions, Action action) {
        mapFiller.addTypeToMap(availableTypes, action.getType(), action.getTitle());
        mapFiller.addActionToMap(availableActions, action.getTitle(), action);
        for (Parameter parameter: action.getParameters()) {
            mapFiller.addParameterToMap(availableParameters, parameter.getName(), parameter.getValue());
        }
        for (Action footprintAction: action.getFootprintActions()) {
            analyzeAction(availableTypes, availableParameters, availableActions, footprintAction);
        }
    }

}