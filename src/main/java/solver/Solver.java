package solver;

import model.Action;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ja on 2016-11-07.
 */
public class Solver {

    private HashMap<String, ArrayList<String>> types;
    private HashMap<String, Action> actions;
    private String target;

    public Solver(HashMap<String, ArrayList<String>> types, HashMap<String, Action> actions, String target) {
        this.types = types;
        this.actions = actions;
        this.target = target;
    }

    public void solve() {

    }

}