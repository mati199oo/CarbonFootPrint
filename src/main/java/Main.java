import org.jdom.Document;
import solver.Solver;
import utility.ActionCreator;
import utility.FileReader;

import static utility.Constants.NUMBER_OF_ITERATIONS;
import static utility.Constants.SOURCE_FILE_PATH;
import static utility.Experiment.makeExperiment;

public class Main {

	public static void main(String[] args) {

		FileReader fileReader = new FileReader();
		Document document = fileReader.readFile(SOURCE_FILE_PATH);

		ActionCreator actionCreator = new ActionCreator();
		actionCreator.createActions(document);

		/* Single simulation */
		//Solver solver = new Solver(actionCreator.getTypes(), actionCreator.getActions(), actionCreator, actionCreator.getTarget());
		//solver.solve(NUMBER_OF_ITERATIONS);

		/* Multiple simulations */
		makeExperiment(actionCreator);

	}

}