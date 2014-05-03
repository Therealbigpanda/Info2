package u8a4;

import java.util.ArrayList;

/**
 *
 * @author Andrea-Pascal Willi
 */
public class BruteforceRucksack implements IRucksack {

    @Override
    public Selection findBest(ArrayList<Integer> values, ArrayList<Integer> weights, int maxWeight) {
        Selection bestSelection = null;
        int maxValue = -1;
        for (int i = 0; i < Math.pow(2, values.size()); i++) {
            Selection currentSelection = new Selection(values.size(), i);
            if (currentSelection.sum(weights) <= maxWeight) {
                if (currentSelection.sum(values) > maxValue) {
                    maxValue = currentSelection.sum(values);
                    bestSelection = currentSelection;
                }
            }  
        }
        return bestSelection;
    }
}
