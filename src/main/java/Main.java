import model.Action;
import solver.Solver;
import utility.ActionCreator;
import utility.FileReader;
import java.io.FileInputStream;
import java.util.HashMap;
import static utility.Constants.SOURCE_FILE_PATH;

/**
 * Created by Ja on 2016-11-07.
 */
public class Main {

    public static void main(String[] args) {

        FileReader fileReader = new FileReader();
        FileInputStream file = fileReader.readFile(SOURCE_FILE_PATH);

        ActionCreator actionCreator = new ActionCreator();
        HashMap<String, Action> actions = actionCreator.createActions(file);

        Solver solver = new Solver();
        solver.setActions(actions);
        solver.solve();

    }

}