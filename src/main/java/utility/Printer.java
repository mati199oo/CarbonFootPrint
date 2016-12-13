package utility;

import model.Action;
import solver.Unit;
import static utility.Constants.NUMBER_OF_SPACES;

/**
 * Created by Ja on 2016-12-13.
 */
public class Printer {

    public void printBestSolution(Unit bestSolution) {
        System.out.println("Best footprint is: " + bestSolution.getFootprint());
        System.out.println("And best combination looks like: ");
        int level = 0;
        printActionTitle(bestSolution.getSolution(), level);
    }

    private void printActionTitle(Action action, int level) {
        for (int i = 0; i < level * NUMBER_OF_SPACES; i++) {
            System.out.print(" ");
        }
        System.out.println(action.getTitle());
        for (Action footprintAction: action.getFootprintActions()) {
            printActionTitle(footprintAction, level + 1);
        }
    }

}