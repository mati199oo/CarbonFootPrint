package utility;

import model.Action;
import model.Parameter;
import org.jdom.Document;
import org.jdom.Element;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import static utility.Constants.*;

/**
 * Created by Ja on 2016-11-07.
 */
public class ActionCreator {

    private HashMap<String, ArrayList<String>> types = new HashMap<String, ArrayList<String>>();
    private HashMap<String, Action> actions = new HashMap<String, Action>();
    private String target;

    private MapFiller mapFiller = new MapFiller();
    private CopyMaker copyMaker = new CopyMaker();
    private Generator generator;
    private Random random = new Random();

    public HashMap<String, ArrayList<String>> getTypes() {
        return types;
    }

    public HashMap<String, Action> getActions() {
        return actions;
    }

    public String getTarget() {
        return target;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public void createActions(Document document) {
        Element classElement = document.getRootElement();
        List<Element> children = classElement.getChildren();
        for (Element child: children) {
            if (ACTIONS_TAG.equals(child.getName())) {
                List<Element> actions = child.getChildren();
                for (Element action: actions) {
                    createAction(action);
                }
            } else if (TARGET_TAG.equals(child.getName())) {
                target = child.getText();
            }
        }
    }

    private void createAction(Element action) {
        List<Element> children = action.getChildren();
        Action actionObject = new Action();
        for (Element child: children) {
            String tagName = child.getName();
            if (TITLE_TAG.equals(tagName)) {
                actionObject.setTitle(child.getText());
            } else if (TYPE_TAG.equals(tagName)) {
                mapFiller.addTypeToMap(types, child.getText(), actionObject.getTitle());
                actionObject.setType(child.getText());
            } else if (PARAMETERS_TAG.equals(tagName)) {
                actionObject.setParameters(createParametersList(child.getChildren()));
            } else if (METHOD_TAG.equals(tagName)) {
                actionObject.setMethod(child.getText());
            } else if (FOOTPRINTS_TAG.equals(tagName)) {
                actionObject.setFootprints(createFootprintsList(child.getChildren()));
            }
        }
        actions.put(actionObject.getTitle(), actionObject);
    }

    private ArrayList<Parameter> createParametersList(List<Element> parameters) {
        ArrayList<Parameter> parametersList = new ArrayList<Parameter>();
        for (Element parameter: parameters) {
            parametersList.add(createParameter(parameter));
        }
        return parametersList;
    }

    private Parameter createParameter(Element parameter) {
        Parameter parameterObject = new Parameter();
        List<Element> children = parameter.getChildren();
        for (Element child: children) {
            String tagName = child.getName();
            if (NAME_TAG.equals(tagName)) {
                parameterObject.setName(child.getText());
            } else if (VALUE_TAG.equals(tagName)) {
                parameterObject.setValue(Double.parseDouble(child.getText()));
            } else if (CONFIGURABLE_TAG.equals(tagName)) {
                if (TRUE_VALUE.equals(child.getText())) {
                    parameterObject.setConfigurable(true);
                } else {
                    parameterObject.setConfigurable(false);
                    parameterObject.setMin(NULL_VALUE);
                    parameterObject.setMax(NULL_VALUE);
                    return  parameterObject;
                }
            } else if (MIN_TAG.equals(tagName)) {
                parameterObject.setMin(Double.parseDouble(child.getText()));
            } else if (MAX_TAG.equals(tagName)) {
                parameterObject.setMax(Double.parseDouble(child.getText()));
            }
        }
        return parameterObject;
    }

    private ArrayList<String> createFootprintsList(List<Element> footprints) {
        ArrayList<String> footprintsList = new ArrayList<String>();
        for (Element footprint: footprints) {
            footprintsList.add(footprint.getText());
        }
        return footprintsList;
    }

    public Action createNewAction(HashMap<String, ArrayList<String>> availableTypes, HashMap<String, ArrayList<Double>> availableParameters,
                                   HashMap<String, Action> availableActions, String actionName) {
        Action newAction = copyMaker.copyAction(availableActions.get(actionName));
        for (Parameter parameter: newAction.getParameters()) {
            String parameterName = parameter.getName();
            ArrayList<Double> values = availableParameters.get(parameterName);
            int valueIndex = random.nextInt(values.size());
            parameter.setValue(values.get(valueIndex));
        }

        ArrayList<Action> footprintActions = new ArrayList<Action>();
        createFootprintActions(availableTypes, availableActions, newAction, footprintActions);
        newAction.setFootprintActions(footprintActions);
        return newAction;
    }

    public void createFootprintActions(HashMap<String, ArrayList<String>> availableTypes, HashMap<String, Action> availableActions,
                                       Action newAction, ArrayList<Action> footprintActions) {
        int actionNameIndex;
        for (String footprintType: newAction.getFootprints()) {
            ArrayList<String> actionsNames = availableTypes.get(footprintType);
            actionNameIndex = random.nextInt(actionsNames.size());
            // TODO: Probably there is a bug, we should check only in available actions, not all of them
            Action footprintAction = generator.generateAction(actionsNames.get(actionNameIndex), availableActions, availableTypes);
            footprintActions.add(footprintAction);
        }
    }

}