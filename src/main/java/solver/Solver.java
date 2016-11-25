package solver;

import model.Action;
import model.Solution;
import utility.CopyMaker;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Ja on 2016-11-07.
 */
public class Solver {

    private HashMap<String, ArrayList<String>> types;
    private HashMap<String, Action> actions;
//    private HashMap<String, List<Action>> actionsByType; 
    private String target;

    private CopyMaker copyMaker = new CopyMaker();
    private MethodsContainer methodsContainer = new MethodsContainer();

    public Solver(HashMap<String, ArrayList<String>> types, HashMap<String, Action> actions, String target) {
        this.types = types;
        this.actions = actions;
        this.target = target;
    }
    
//    private Action switchOnType(Action actual) {
//    	List<String> substitutes = new ArrayList<String>();
//    	substitutes.addAll(types.get(actual.getType()));
//    	substitutes.remove(actual.getTitle());
//    	Random rand = new Random();
//    	if (substitutes.isEmpty())
//    		return actual;
//    	else
//    		return actions.get(substitutes.get(rand.nextInt(substitutes.size())));
//    }

    public void solve() {
    	System.out.println(target);
        Action defaultSolution = createDefaultSolution(target);
        Solution solution = new Solution(defaultSolution, this);
        System.out.println();

        Solution mutated1 = solution.mutate(types, actions);
        Solution mutated2 = solution.mutate(types, actions);
        Solution mutated3 = solution.mutate(types, actions);
        Solution mutated4 = solution.mutate(types, actions);
        System.out.println();

        Solution crossed1 = solution.cross(mutated1);
        Solution crossed1b = solution.cross(mutated1);
        Solution crossed2 = solution.cross(mutated2);
        Solution crossed2b = solution.cross(mutated2);
        Solution crossed3 = solution.cross(mutated3);
        Solution crossed3b = solution.cross(mutated3);
        Solution crossed4 = solution.cross(mutated4);
        Solution crossed4b = solution.cross(mutated4);
        
//        Action maxMutatedSolution = createMaxMutatedSolution(target);
//        HashMap<String, Double> result = calculateFootprintCost(defaultSolution);
//        System.out.println("default: " + result.get("CarbonCost"));
//        result = calculateFootprintCost(maxMutatedSolution);
//        System.out.println("maxMutated: " + result.get("CarbonCost"));
//        Solution solution2 = new Solution(maxMutatedSolution, this);
//        System.out.println();
//        solution.cross(solution2);
    }

	public Action createDefaultSolution(String defaultAction) {
        Action action = copyMaker.copyAction(actions.get(defaultAction));
        ArrayList<Action> footprintActions = new ArrayList<Action>();
        for (String defaultActionName: action.getDefaultActions()) {
            Action defaultActionObject = createDefaultSolution(defaultActionName);
            footprintActions.add(defaultActionObject);
        }
        action.setFootprintActions(footprintActions);
        return action;
    }
	
//	private Action createMaxMutatedSolution(String Action) {
//        Action action = copyMaker.copyAction(actions.get(Action));
//        ArrayList<Action> footprintActions = new ArrayList<Action>();
//        for (String ActionName: action.getDefaultActions()) {
//        	Action a = actions.get(ActionName);
//        	a = switchOnType(a);
//        	if (a != actions.get(ActionName)) System.out.println(ActionName + " -> " + a.getTitle());
//            Action ActionObject = createMaxMutatedSolution(a.getTitle());
//            footprintActions.add(ActionObject);
//        }
//        action.setFootprintActions(footprintActions);
//        return action;
//    }

    private HashMap<String, Double> calculateFootprintCost(Action action) {
        HashMap<String, Double> resultMap = new HashMap<String, Double>();
        for (Action footprintAction: action.getFootprintActions()) {
            resultMap.putAll(calculateFootprintCost(footprintAction));
        }
        try {
            Method m = methodsContainer.getClass().getMethod(action.getMethod(), HashMap.class, ArrayList.class);
            return (HashMap<String, Double>) m.invoke(methodsContainer, resultMap, action.getParameters());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new NullPointerException();
    }

}