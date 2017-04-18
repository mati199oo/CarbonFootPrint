package solver;

import model.Parameter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Bartłomiej Grochal
 */
@SuppressWarnings("unused")
public class MutationTestMethodContainer {

    /* Extraction of a raw material */

    public HashMap<String, Double> getEmissionForFirstMethodOfExtraction(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("EmissionForFirstMethodOfExtraction", parameters.get(0).getValue());
        return result;
    }

    public HashMap<String, Double> getEmissionForSecondMethodOfExtraction(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("EmissionForSecondMethodOfExtraction", parameters.get(0).getValue());
        return result;
    }

    public HashMap<String, Double> getEmissionForThirdMethodOfExtraction(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("EmissionForThirdMethodOfExtraction", parameters.get(0).getValue());
        return result;
    }

    public HashMap<String, Double> getConstantEmissionForExtraction(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("ExtractionEmissionConstant", 30.0);
        return result;
    }

    public HashMap<String, Double> getOverallEmissionForFirstMethodOfExtraction(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double methodEmission = ins.get("EmissionForFirstMethodOfExtraction");
        double constantEmission = ins.get("ExtractionEmissionConstant");

        double combined = 100 + Math.log(10 * (1 + Math.abs(130 - methodEmission))) + constantEmission;

        result.put("OverallEmissionForExtraction", combined);
        return result;
    }

    public HashMap<String, Double> getOverallEmissionForSecondMethodOfExtraction(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double methodEmission = ins.get("EmissionForSecondMethodOfExtraction");
        double constantEmission = ins.get("ExtractionEmissionConstant");

        double combined = 105 + (methodEmission - 115) * Math.sin(113 - methodEmission) * Math.cos(117 - methodEmission)
                + constantEmission;

        result.put("OverallEmissionForExtraction", combined);
        return result;
    }

    public HashMap<String, Double> getOverallEmissionForThirdMethodOfExtraction(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double methodEmission = ins.get("EmissionForThirdMethodOfExtraction");
        double constantEmission = ins.get("ExtractionEmissionConstant");

        double combined = 100 + (123 - methodEmission) * Math.sin(3 * methodEmission) + constantEmission;

        result.put("OverallEmissionForExtraction", combined);
        return result;
    }

    /* Transport */

    public HashMap<String, Double> getEmissionForFuelConsumption(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("EmissionForFuelConsumption", 0.1);
        return result;
    }

    public HashMap<String, Double> getFuelConsumptionForConventionalTruck(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("FuelConsumption", 0.26);
        return result;
    }

    public HashMap<String, Double> getFuelConsumptionForEcologicalTruck(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("FuelConsumption", 0.20);
        return result;
    }

    public HashMap<String, Double> getEmissionForWheelTransport(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double fuelConsumptionEmission = ins.get("EmissionForFuelConsumption");
        double fuelConsumption = ins.get("FuelConsumption");

        double combined = fuelConsumptionEmission * fuelConsumption;

        result.put("EmissionForWheelTransport", combined);
        return result;
    }

    public HashMap<String, Double> getWheelDistance(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("WheelDistance", parameters.get(0).getValue());
        return result;
    }

    public HashMap<String, Double> getEmissionForRailwayTransport(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("EmissionForRailwayTransport", 0.018);
        return result;
    }

    public HashMap<String, Double> getRailwayDistance(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("RailwayDistance", 700.0);
        return result;
    }

    public HashMap<String, Double> getOverallEmissionForWheelTransport(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double wheelTransportEmission = ins.get("EmissionForWheelTransport");
        double wheelDistance = ins.get("WheelDistance");

        double combined = (wheelDistance + Math.pow(530 - wheelDistance, 2.0)) * wheelTransportEmission;

        result.put("OverallEmissionForTransport", combined);
        return result;
    }

    public HashMap<String, Double> getOverallEmissionForRailwayTransport(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double railwayTransportEmission = ins.get("EmissionForRailwayTransport");
        double railwayDistance = ins.get("RailwayDistance");

        double combined = railwayDistance * railwayTransportEmission;

        result.put("OverallEmissionForTransport", combined);
        return result;
    }

    /* Production */

    public HashMap<String, Double> getEmissionForFirstMethodOfProduction(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("EmissionForProduction", 40.0);
        return result;
    }

    public HashMap<String, Double> getEmissionForSecondMethodOfProduction(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("EmissionForProduction", 60.0);
        return result;
    }

    public HashMap<String, Double> getConstantEmissionForProduction(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("ProductionEmissionConstant", 10.0);
        return result;
    }

    public HashMap<String, Double> getOverallEmissionForProduction(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double productionEmission = ins.get("EmissionForProduction");
        double constantEmission = ins.get("ProductionEmissionConstant");

        double combined = productionEmission + constantEmission;

        result.put("OverallEmissionForProduction", combined);
        return result;
    }

    /* Final */

    public HashMap<String, Double> getOverallEmission(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double extractionEmission = ins.get("OverallEmissionForExtraction");
        double transportEmission = ins.get("OverallEmissionForTransport");
        double productionEmission = ins.get("OverallEmissionForProduction");

        double combined = extractionEmission + transportEmission + productionEmission;

        result.put("OverallEmission", combined);
        return result;
    }

}
