package solver;

import model.Action;
import model.Parameter;
import utility.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import static utility.Constants.*;

/**
 * Created by Ja on 2016-11-07.
 */
public class Solver {

    private HashMap<String, ArrayList<String>> types;
    private HashMap<String, Action> actions;
    private String target;

    private CopyMaker copyMaker = new CopyMaker();
    private Generator generator;
    private Calculator calculator = new Calculator();
    private ActionCreator actionCreator;
    private Printer printer = new Printer();
    private Analyzer analyzer = new Analyzer();
    private Randomizer randomizer = new Randomizer();
    private Random random = new Random();

    private ArrayList<Unit> population;
    private Unit bestSolution;

    public Solver(HashMap<String, ArrayList<String>> types, HashMap<String, Action> actions, ActionCreator actionCreator, String target) {
        this.types = types;
        this.actions = actions;
        this.target = target;
        this.actionCreator = actionCreator;
        generator = new Generator(actionCreator);
        actionCreator.setGenerator(generator);
        population = generateInitialPopulation(CARDINALITY);
        bestSolution = findBestSolution();
        calculator.calculateUnitsFootprintCost(population);
    }

    private ArrayList<Unit> generateInitialPopulation(int cardinality) {
        ArrayList<Unit> population = new ArrayList<Unit>();
        for (int i = 0; i < cardinality; i++) {
            population.add(generator.generateUnit(target, actions, types));
        }
        return population;
    }

    private Unit findBestSolution() {
        Unit bestSolution = population.get(0);
        for (int i = 1; i < population.size(); i++) {
            if (population.get(i).getFootprint() < bestSolution.getFootprint()) {
                bestSolution = population.get(i);
            }
        }
        return bestSolution;
    }

    public Unit solve(int iterations) {
        Unit iterationBestSolution;
        for (int i = 0; i < iterations; i++) {
            selection();
            crossingOver();
            mutation();
            calculator.calculateUnitsFootprintCost(population);
            iterationBestSolution = findBestSolution();
            if (iterationBestSolution.getFootprint() < bestSolution.getFootprint()) {
                bestSolution = iterationBestSolution;
            }
        }
        printer.printBestSolution(bestSolution);
        return bestSolution;
    }

    private void selection() {
        ArrayList<Unit> newPopulation = new ArrayList<Unit>();
        for (int i = 0; i < CARDINALITY / 2; i++) {
            Unit winner = fight();
            newPopulation.add(copyMaker.copyUnit(winner));
        }
        population = newPopulation;
    }

    private Unit fight() {
        ArrayList<Unit> units = randomizer.randomizeUnits(population);
        if (units.get(0).getFootprint() < units.get(1).getFootprint()) {
            return units.get(0);
        }
        return units.get(1);
    }

    private void crossingOver() {
        ArrayList<Unit> newPopulation = new ArrayList<Unit>();
        for (int i = 0; i < CARDINALITY; i++) {
            Unit child = crossOver();
            newPopulation.add(child);
        }
        population = newPopulation;
    }

    private Unit crossOver() {
        ArrayList<Unit> units = randomizer.randomizeUnits(population);
        Unit child = new Unit();
        HashMap<String, ArrayList<String>> availableTypes = new HashMap<String, ArrayList<String>>();
        HashMap<String, ArrayList<Double>> availableParameters = new HashMap<String, ArrayList<Double>>();
        HashMap<String, Action> availableActions = new HashMap<String, Action>();
        analyzer.findAvailableTypesAndParameters(availableTypes, availableParameters, availableActions,  units.get(0), units.get(1));
        child.setSolution(actionCreator.createNewAction(availableTypes, availableParameters, availableActions, target));
        return child;
    }

    private void mutation() {
        for (Unit unit: population) {
            mutate(unit.getSolution(), false);
        }
    }

    private boolean mutate(Action action, boolean isMutated) {
        if (!isMutated) {
            if (random.nextInt(100) < MUTATION_CHANCE) {
                int mutationType = random.nextInt(2);
                if (mutationType == 0) {
                    isMutated = mutateAction(action);
                } else {
                    isMutated = mutateParameter(action);
                }
            }
            ArrayList<Action> footprintActions = action.getFootprintActions();
            int index = 0;
            while (!isMutated && index < footprintActions.size()) {
                isMutated = mutate(footprintActions.get(index), false);
                index++;
            }
        }
        return isMutated;
    }

    private boolean mutateAction(Action action) {
        String type = action.getType();
        ArrayList<String> actionsNames = types.get(type);
        if (actionsNames.size() > 1) {
            int index = random.nextInt(actionsNames.size());
            while (actionsNames.get(index).equals(action.getTitle())) {
                index = random.nextInt(actionsNames.size());
            }
            copyMaker.copyActionAttributes(action, actions.get(actionsNames.get(index)));
            ArrayList<Action> footprintActions = new ArrayList<Action>();
            actionCreator.createFootprintActions(types, actions, action, footprintActions);
            action.setFootprintActions(footprintActions);
            return true;
        }
        return false;
    }

    private boolean mutateParameter(Action action) {
        ArrayList<Parameter> parameters = action.getParameters();
        int numberOfConfigurableParameters = 0;
        for (Parameter parameter: parameters) {
            if (parameter.isConfigurable()) {
                numberOfConfigurableParameters++;
            }
        }
        if (parameters.size() > 0 && numberOfConfigurableParameters > 0) {
            int index = random.nextInt(parameters.size());
            while (!parameters.get(index).isConfigurable()) {
                index = random.nextInt(parameters.size());
            }
            Parameter parameter = parameters.get(index);
            double value = parameter.getValue();
            double min = parameter.getMin();
            double max = parameter.getMax();
            double newValue = min + (max - min) * random.nextDouble();
            while (value == newValue) {
                newValue = min + (max - min) * random.nextDouble();
            }
            parameter.setValue(newValue);
            return true;
        }
        return false;
    }

}