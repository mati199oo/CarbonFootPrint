package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Population {

	private int POPULATION_SIZE = 20;
	private Solution[] individuals = new Solution[POPULATION_SIZE];

	public void generateFromSeed(Solution seed, HashMap<String, ArrayList<String>> types,
			HashMap<String, Action> actions) {
		for (int i = 0; i < POPULATION_SIZE; i++) {
			individuals[i] = seed.mutate(types, actions);
		}
	}

	public void crossPopulation() {
		Solution[] nextGeneration = new Solution[POPULATION_SIZE];
		for (int i = 0; i < POPULATION_SIZE / 2; i++) {
			nextGeneration[i] = individuals[i].cross(individuals[i + POPULATION_SIZE / 2]);
			nextGeneration[i + POPULATION_SIZE / 2] = individuals[i].cross(individuals[i + POPULATION_SIZE / 2]);
		}
		individuals = nextGeneration;
	}

	public void printPopulation() {
		System.out.println(
				"#####################################################################################################################################################");
		System.out.println(
				"#####################################################################################################################################################");
		System.out.println(
				"#####################################################################################################################################################");
		for (int i = 0; i < POPULATION_SIZE; i++) {
			individuals[i].print();
			System.out.println("-------------------------------------------------------------");
		}

		System.out.println(
				"#####################################################################################################################################################");
		System.out.println(
				"#####################################################################################################################################################");
		System.out.println(
				"#####################################################################################################################################################");

	}

}
