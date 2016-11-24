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
    
    public HashMap<String, Double> getCoalCarbonEmissionForKWh(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<String, Double>();
        result.put("CarbonEmissionForKWh", 0.9124);
        return result;
    }
    
    public HashMap<String, Double> getWindCarbonEmissionForKWh(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<String, Double>();
        result.put("CarbonEmissionForKWh", 0.4);
        return result;
    }
    
    public HashMap<String, Double> getWaterCarbonEmissionForKWh(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<String, Double>();
        result.put("CarbonEmissionForKWh", 0.3);
        return result;
    }
    
    public HashMap<String, Double> calculateCarbonEmissionForMgOfRawMaterial(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<String, Double>();
        double carbonEmission = ins.get("CarbonEmissionForKWh");
        double heatCost = ins.get("CarbonFromHeat");
        double cost = carbonEmission*39.158;
        cost += heatCost*0.261;
        cost += parameters.get(0).getValue()*20.6027;
        cost += 229.553;
        result.put("CarbonEmissionForMgOfRawMaterial", cost);
        return result;
    }

    public HashMap<String, Double> getCoalHeatForMg(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<String, Double>();
        result.put("CarbonFromHeat", 63.46);
        return result;
    }
    public HashMap<String, Double> getGeyserHeatForMg(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<String, Double>();
        result.put("CarbonFromHeat", 0.0);
        return result;
    }

    public HashMap<String, Double> calculateCompleteCarbonEmission(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<String, Double>();
        double transportCost = ins.get("DeliveryCost");
        double materialCost = ins.get("CarbonEmissionForMgOfRawMaterial");
        double cost = transportCost;
        cost += (materialCost*5);
        result.put("CarbonCost", cost);
        return result;
    }
    
}