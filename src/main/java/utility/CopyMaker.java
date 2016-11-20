package utility;

import model.Action;
import model.Parameter;
import java.util.ArrayList;

/**
 * Created by Ja on 2016-11-20.
 */
public class CopyMaker {

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