package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import solver.Solver;
import utility.CopyMaker;

public class Solution {

	Action head;
	List<String> nodes;
	private CopyMaker copyMaker = new CopyMaker();
	private Random rand = new Random();
	private Solver solver;

	private void dupa(String depth, Action action) {
		depth += "  ";
		for (Action act : action.getFootprintActions()) {
//			System.out.println(depth + "-" + act.getTitle());
			dupa(depth, act);
		}
	}

	public Solution(Action head, Solver solver) {
		this.head = head;
		this.solver = solver;
		nodes = new ArrayList<String>();
		nodes.add(head.getTitle());
		String depth = "";
//		System.out.println(head.getTitle());
		dupa(depth, head);
	}

	private Action crossIfPossible(Action a1, Action a2) {
		Action a;
		if (a1.getTitle().equals(a2.getTitle())) {
			ArrayList<Action> footActions = new ArrayList<Action>();
			ArrayList<String> foot = new ArrayList<String>();
			a = copyMaker.copyAction(a1);
			for (int i = 0; i < a1.getFootprintActions().size(); i++) {
				footActions.add(crossIfPossible(a1.getFootprintActions().get(i), a2.getFootprintActions().get(i)));
				foot.add(footActions.get(footActions.size() - 1).getTitle());
			}
			a.setFootprintActions(footActions);
			a.setFootprints(foot);
		} else if (rand.nextBoolean())
			a = a1;
		else
			a = a2;
		return a;
	}

	public Solution cross(Solution other) {
		return new Solution(crossIfPossible(head, other.head), solver);
	}

	private Action findSubstitute(Action actual, HashMap<String, ArrayList<String>> types, HashMap<String, Action> actions) {
		List<String> substitutes = new ArrayList<String>();
		substitutes.addAll(types.get(actual.getType()));
		substitutes.remove(actual.getTitle());
		if (substitutes.isEmpty())
			return actual;
		else
			return actions.get(substitutes.get(rand.nextInt(substitutes.size())));
	}

	private Action mutateIfPossible(Action a, HashMap<String, ArrayList<String>> types, HashMap<String, Action> actions) {
		Action a0 = copyMaker.copyAction(a);
		a0.setFootprintActions(new ArrayList<Action>());
		a0.setFootprints(new ArrayList<String>());
		if (types.get(a.getType()).size() > 1) {
			if (rand.nextBoolean()) {
				a0 = solver.createDefaultSolution(findSubstitute(a, types, actions).getTitle());
			}
			for (int i = 0; i < a0.getFootprintActions().size(); i++) {
				a0.getFootprintActions().add(mutateIfPossible(a0.getFootprintActions().get(i), types, actions));
			}
		} else {
			for (int i = 0; i < a.getFootprintActions().size(); i++) {
				a0.getFootprintActions().add(mutateIfPossible(a.getFootprintActions().get(i), types, actions));
			}
		}
		return a0;
	}

	public Solution mutate(HashMap<String, ArrayList<String>> types, HashMap<String, Action> actions) {
		return new Solution(mutateIfPossible(head, types, actions), solver);
	}
	
	private void printChildren(String depth, Action action) {
		depth += "  ";
		for (Action act : action.getFootprintActions()) {
			System.out.println(depth + "-" + act.getTitle());
			printChildren(depth, act);
		}
	}
	public void print() {
		System.out.println(head.getTitle());
		printChildren("", head);
	}
}
