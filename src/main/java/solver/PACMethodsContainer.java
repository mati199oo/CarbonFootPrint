package solver;

import model.Parameter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Bartłomiej Grochal
 */
@SuppressWarnings("unused")
public class PACMethodsContainer {

    /* Extraction footprint */

    public HashMap<String, Double> getElectricityUsageForExtraction(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("ElectricityUsageForExtraction", 39.158);
        return result;
    }

    public HashMap<String, Double> getEmissionByElectricity(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("EmissionByElectricity", 0.9124);
        return result;
    }

    public HashMap<String, Double> getHeatUsage(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("HeatUsage", -0.261);
        return result;
    }

    public HashMap<String, Double> getEmissionByHeat(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("EmissionByHeat", 63.46);
        return result;
    }

    public HashMap<String, Double> calculateEmissionByEnergy(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double electricityUsageForExtraction = ins.get("ElectricityUsageForExtraction");
        double emissionByElectricity = ins.get("EmissionByElectricity");
        double heatUsage = ins.get("HeatUsage");
        double emissionByHeat = ins.get("EmissionByHeat");

        double combined = electricityUsageForExtraction * emissionByElectricity + heatUsage * emissionByHeat;

        result.put("EmissionByEnergy", combined);
        return result;
    }

    public HashMap<String, Double> getEmissionByMethaneBurning(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("EmissionByMethaneBurning", 20.6027);
        return result;
    }

    public HashMap<String, Double> getMethaneEmission(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("MethaneEmission", 229.553);
        return result;
    }

    public HashMap<String, Double> calculateExtractionFootprint(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double emissionByEnergy = ins.get("EmissionByEnergy");
        double emissionByMethaneBurning = ins.get("EmissionByMethaneBurning");
        double methaneEmission = ins.get("MethaneEmission");

        double combined = emissionByEnergy + emissionByMethaneBurning + methaneEmission;

        result.put("ExtractionFootprint", combined);
        return result;
    }

    /* Transport footprint */

    public HashMap<String, Double> getElectricityUsageForTransport(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("ElectricityUsageForTransport", 0.0789);
        return result;
    }

    public HashMap<String, Double> calculateEmissionByTransport(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double emissionByElectricity = ins.get("EmissionByElectricity");
        double electricityUsageForTransport = ins.get("ElectricityUsageForTransport");

        double combined = emissionByElectricity * electricityUsageForTransport;

        result.put("EmissionByTransport", combined);
        return result;
    }

    public HashMap<String, Double> getDistance(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("Distance", 500.0);
        return result;
    }

    public HashMap<String, Double> calculateTransportFootprint(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double emissionByTransport = ins.get("EmissionByTransport");
        double distance = ins.get("Distance");

        double combined = emissionByTransport * distance;

        result.put("TransportFootprint", combined);
        return result;
    }

    /* Activation footprint */

    public HashMap<String, Double> getElectricityUsageForActivation(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("ElectricityUsageForActivation", 1087.1);
        return result;
    }

    public HashMap<String, Double> calculateEmissionBySorbentProduction(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double emissionByElectricity = ins.get("EmissionByElectricity");
        double electricityUsageForActivation = ins.get("ElectricityUsageForActivation");

        double combined = emissionByElectricity * electricityUsageForActivation;

        result.put("EmissionBySorbentProduction", combined);
        return result;
    }

    public HashMap<String, Double> getPyrolyticGasEmission(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("PyrolyticGasEmission", 2345.66);
        return result;
    }

    public HashMap<String, Double> calculateEmissionByActivationFirstBranch(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double emissionBySorbentProduction = ins.get("EmissionBySorbentProduction");
        double pyrolyticGasEmission = ins.get("PyrolyticGasEmission");

        double combined = emissionBySorbentProduction + pyrolyticGasEmission;

        result.put("ActivationFirstBranch", combined);
        return result;
    }

    public HashMap<String, Double> getCoalCharge(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        result.put("CoalCharge", 2.8);
        return result;
    }

    public HashMap<String, Double> calculateOverallPAC(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double extractionFootprint = ins.get("ExtractionFootprint");
        double transportFootprint = ins.get("TransportFootprint");

        double combined = extractionFootprint + transportFootprint;

        result.put("OverallPAC", combined);
        return result;
    }

    public HashMap<String, Double> calculateEmissionByActivationSecondBranch(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double coalCharge = ins.get("CoalCharge");
        double overallPAC = ins.get("OverallPAC");

        double combined = coalCharge * overallPAC;

        result.put("ActivationSecondBranch", combined);
        return result;
    }

    /* Final footprint */

    public HashMap<String, Double> calculateActivationFootprint(HashMap<String, Double> ins, ArrayList<Parameter> parameters) {
        HashMap<String, Double> result = new HashMap<>();
        double activationFirstBranch = ins.get("ActivationFirstBranch");
        double activationSecondBranch = ins.get("ActivationSecondBranch");

        double combined = activationFirstBranch + activationSecondBranch;

        result.put("ActivationFootprint", combined);
        return result;
    }

}
