package utility;

import model.Action;
import model.Parameter;
import solver.Unit;
import java.util.ArrayList;

/**
 * Created by Ja on 2016-11-20.
 */
public class CopyMaker {

    public Unit copyUnit(Unit unit) {
        Unit newUnit = new Unit();
        newUnit.setSolution(copyActionWithFootprints(unit.getSolution()));
        newUnit.setFootprint(unit.getFootprint());
        return newUnit;
    }

    private Action copyActionWithFootprints(Action action) {
        Action newAction = copyAction(action);
        ArrayList<Action> newFootprintActions = new ArrayList<Action>();
        for (Action footprintAction: action.getFootprintActions()) {
            Action newFootPrintAction = copyActionWithFootprints(footprintAction);
            newFootprintActions.add(newFootPrintAction);
        }
        newAction.setFootprintActions(newFootprintActions);
        return newAction;
    }

    public Action copyAction(Action action) {
        Action newAction = new Action();
        newAction.setTitle(action.getTitle());
        newAction.setType(action.getType());
        newAction.setDefaultActions(action.getDefaultActions());
        newAction.setParameters(copyActionParameters(action.getParameters()));
        newAction.setMethod(action.getMethod());
        newAction.setFootprints(action.getFootprints());
        return newAction;
    }

    public void copyActionAttributes(Action actionTarget, Action actionSource) {
        actionTarget.setTitle(actionSource.getTitle());
        actionTarget.setType(actionSource.getType());
        actionTarget.setDefaultActions(actionSource.getDefaultActions());
        actionTarget.setParameters(copyActionParameters(actionSource.getParameters()));
        actionTarget.setMethod(actionSource.getMethod());
        actionTarget.setFootprints(actionSource.getFootprints());
    }

    private ArrayList<Parameter> copyActionParameters(ArrayList<Parameter> parameters) {
        ArrayList<Parameter> actionParametersList = new ArrayList<Parameter>();
        for (Parameter parameter: parameters) {
            actionParametersList.add(copyParameter(parameter));
        }
        return actionParametersList;
    }

    private Parameter copyParameter(Parameter parameter) {
        Parameter newParameter = new Parameter();
        newParameter.setName(parameter.getName());
        newParameter.setValue(parameter.getValue());
        newParameter.setConfigurable(parameter.isConfigurable());
        newParameter.setMin(parameter.getMin());
        newParameter.setMax(parameter.getMax());
        return newParameter;
    }

}