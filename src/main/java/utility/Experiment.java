package utility;

import com.opencsv.CSVWriter;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import solver.Solver;
import solver.Unit;

import java.io.FileWriter;
import java.io.IOException;

import static utility.Constants.NUMBER_OF_ITERATIONS;
import static utility.Constants.SAMPLES_IN_ITERATION;

public class Experiment {

    public static void makeExperiment(ActionCreator actionCreator) {
        CSVWriter writer;
        Unit bestSolution = null;
        Printer printer = new Printer();
        try {
            writer = new CSVWriter(new FileWriter("target/experiment.csv"), ',', '\0');
            for (int i = 1; i <= NUMBER_OF_ITERATIONS; i++) {
                double[] results = new double[SAMPLES_IN_ITERATION];
                for (int j = 0; j < SAMPLES_IN_ITERATION; j++) {
                    Solver solver = new Solver(actionCreator.getTypes(), actionCreator.getActions(), actionCreator, actionCreator.getTarget());
                    Unit solution = solver.solve(i);
                    results[j] = solution.getFootprint();
                    bestSolution = (bestSolution == null) ? solution : bestSolution;
                    bestSolution = (solution.getFootprint() < bestSolution.getFootprint()) ? solution : bestSolution;
                }
                StandardDeviation sd = new StandardDeviation();
                Mean mn = new Mean();
                double deviation = sd.evaluate(results);
                double mean = mn.evaluate(results);
                double devMinus = mean - deviation;
                double devPlus = mean + deviation;
                writer.writeNext((i + "#" + devMinus + "#" + mean + "#" + devPlus).split("#"));
            }
            printer.printBestSolution(bestSolution);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
