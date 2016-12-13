package utility;

import solver.Unit;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ja on 2016-12-13.
 */
public class Randomizer {

    private Random random = new Random();

    public ArrayList<Unit> randomizeUnits(ArrayList<Unit>  population) {
        int unit1Index = random.nextInt(population.size());
        int unit2Index = random.nextInt(population.size());
        while (unit1Index == unit2Index) {
            unit2Index = random.nextInt(population.size());
        }
        Unit unit1 = population.get(unit1Index);
        Unit unit2 = population.get(unit2Index);
        ArrayList<Unit> units = new ArrayList<Unit>();
        units.add(unit1);
        units.add(unit2);
        return units;
    }

}