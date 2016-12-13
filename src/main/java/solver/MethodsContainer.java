package solver;

import model.Parameter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ja on 2016-11-20.
 */
public class MethodsContainer {

    public HashMap<String, Double> calculateDeliveryCost(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<String, Double>();
        double cost = ins.get("TruckCreationCost");
        cost += parameters.get(0).getValue();
        cost += parameters.get(1).getValue();
        result.put("DeliveryCost", cost);
        return result;
    }

    public HashMap<String, Double> getTruckCreationCost(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<String, Double>();
        result.put("TruckCreationCost", 4000.0);
        return result;
    }

    public HashMap<String, Double> getBoatCreationCost(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<String, Double>();
        result.put("TruckCreationCost", 10000.0);
        return result;
    }

}