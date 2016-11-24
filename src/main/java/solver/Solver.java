package solver;

import model.Action;
import utility.CopyMaker;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ja on 2016-11-07.
 */
public class Solver {

    private HashMap<String, ArrayList<String>> types;
    private HashMap<String, Action> actions;
    private String target;

    private CopyMaker copyMaker = new CopyMaker();
    private MethodsContainer methodsContainer = new MethodsContainer();

    public Solver(HashMap<String, ArrayList<String>> types, HashMap<String, Action> actions, String target) {
        this.types = types;
        this.actions = actions;
        this.target = target;
    }

    public void solve() {
    	System.out.println(target);
        Action defaultSolution = createDefaultSolution(target);
        HashMap<String, Double> result = calculateFootprintCost(defaultSolution);
        System.out.println(result.get("CarbonCost"));
    }

    private Action createDefaultSolution(String defaultAction) {
        Action action = copyMaker.copyAction(actions.get(defaultAction));
        ArrayList<Action> footprintActions = new ArrayList<Action>();
        for (String defaultActionName: action.getDefaultActions()) {
            Action defaultActionObject = createDefaultSolution(defaultActionName);
            footprintActions.add(defaultActionObject);
        }
        action.setFootprintActions(footprintActions);
        return action;
    }

    private HashMap<String, Double> calculateFootprintCost(Action action) {
        HashMap<String, Double> resultMap = new HashMap<String, Double>();
        for (Action footprintAction: action.getFootprintActions()) {
            resultMap.putAll(calculateFootprintCost(footprintAction));
        }
        try {
            Method m = methodsContainer.getClass().getMethod(action.getMethod(), HashMap.class, ArrayList.class);
            return (HashMap<String, Double>) m.invoke(methodsContainer, resultMap, action.getParameters());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new NullPointerException();
    }

}