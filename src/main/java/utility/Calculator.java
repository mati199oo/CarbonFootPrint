package utility;

import model.Action;
import solver.MethodsContainer;
import solver.PACMethodsContainer;
import solver.Unit;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ja on 2016-12-13.
 */
public class Calculator {

//    private MethodsContainer methodsContainer = new MethodsContainer();
    private PACMethodsContainer methodsContainer = new PACMethodsContainer();

    public void calculateUnitsFootprintCost(ArrayList<Unit> population) {
        for (Unit unit: population) {
            HashMap<String, Double> resultMap = calculateFootprintCost(unit.getSolution());
            double result = 0.0;
            for (String key: resultMap.keySet()) {
                result += resultMap.get(key);
            }
            unit.setFootprint(result);
        }
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