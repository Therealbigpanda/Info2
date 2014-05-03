package u8a4;

import java.util.ArrayList;

/**
 *
 * @author Andrea-Pascal Willi
 */
public class BacktrackingRucksack implements IRucksack {

    private ArrayList<Integer> values;
    private ArrayList<Integer> weights;
    private int maxWeight;

    @Override
    public Selection findBest(ArrayList<Integer> values, ArrayList<Integer> weights, int maxWeight) {
        this.values = values;
        this.weights = weights;
        this.maxWeight = maxWeight;
        return backtracking(new Selection(0), 0);
    }

    public Selection backtracking(Selection best, int currentWeight) {
        int depth = best.size();
        if (depth == values.size()) {
            return best;
        }

        Selection without = new Selection(depth + 1, best.bits());
        without.set(depth, false); // sets undefined bit to false
        without = backtracking(without, currentWeight);

        if (currentWeight + weights.get(depth) <= maxWeight) {
            Selection with = new Selection(depth + 1, without.bits());
            with.set(depth, true);
            with = backtracking(with, currentWeight + weights.get(depth));

            if (with.sum(values) > without.sum(values)) {
                return with;
            }
        }
        return without;
    }
}
