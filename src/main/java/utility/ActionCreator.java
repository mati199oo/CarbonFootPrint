package utility;

import model.Action;
import model.Parameter;
import org.jdom.Document;
import org.jdom.Element;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static utility.Constants.*;

/**
 * Created by Ja on 2016-11-07.
 */
public class ActionCreator {

    private HashMap<String, ArrayList<String>> types;
    private HashMap<String, Action> actions;
    private String target;

    public HashMap<String, ArrayList<String>> getTypes() {
        return types;
    }

    public HashMap<String, Action> getActions() {
        return actions;
    }

    public String getTarget() {
        return target;
    }

    public ActionCreator(){
        types = new HashMap<String, ArrayList<String>>();
        actions = new HashMap<String, Action>();
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
                addTypeToMap(child.getText(), actionObject.getTitle());
                actionObject.setType(child.getText());
            } else if (DEFAULT_ACTIONS_TAG.equals(tagName)) {
                actionObject.setDefaultActions(createDefaultActionsList(child.getChildren()));
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

    private void addTypeToMap(String type, String title) {
        if (!types.containsKey(type)) {
            ArrayList<String> titles = new ArrayList<String>();
            titles.add(title);
            types.put(type, titles);
        } else {
            types.get(type).add(title);
        }
    }

    private ArrayList<String> createDefaultActionsList(List<Element> defaultActions) {
        ArrayList<String> actionsList = new ArrayList<String>();
        for (Element defaultAction: defaultActions) {
            actionsList.add(defaultAction.getText());
        }
        return actionsList;
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

}