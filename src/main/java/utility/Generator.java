package utility;

import model.Action;
import model.Parameter;
import solver.Unit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Ja on 2016-12-13.
 */
public class Generator {

    private ActionCreator actionCreator;
    private CopyMaker copyMaker = new CopyMaker();
    private Random random = new Random();

    public Generator(ActionCreator actionCreator) {
        this.actionCreator = actionCreator;
    }

    public Unit generateUnit(String actionName, HashMap<String, Action> availableActions, HashMap<String, ArrayList<String>> availableTypes) {
        Unit unit = new Unit();
        unit.setSolution(generateAction(actionName, availableActions, availableTypes));
        return unit;
    }

    public Action generateAction(String actionName, HashMap<String, Action> availableActions, HashMap<String, ArrayList<String>> availableTypes) {
        Action action = copyMaker.copyAction(availableActions.get(actionName));
        for (Parameter parameter: action.getParameters()) {
            if (parameter.isConfigurable()) {
                double min = parameter.getMin();
                double max = parameter.getMax();
                double parameterValue = min + (max - min) * random.nextDouble();
                parameter.setValue(parameterValue);
            }
        }
        ArrayList<Action> footprintActions = new ArrayList<Action>();
        actionCreator.createFootprintActions(availableTypes, availableActions, action, footprintActions);
        action.setFootprintActions(footprintActions);
        return action;
    }

}