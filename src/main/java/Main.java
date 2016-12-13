import org.jdom.Document;
import solver.Solver;
import utility.ActionCreator;
import utility.FileReader;
import static utility.Constants.SOURCE_FILE_PATH;

/**
 * Created by Ja on 2016-11-07.
 */
public class Main {

    public static void main(String[] args) {

        FileReader fileReader = new FileReader();
        Document document = fileReader.readFile(SOURCE_FILE_PATH);

        ActionCreator actionCreator = new ActionCreator();
        actionCreator.createActions(document);

        Solver solver = new Solver(actionCreator.getTypes(), actionCreator.getActions(), actionCreator, actionCreator.getTarget());
        solver.solve();

    }

}