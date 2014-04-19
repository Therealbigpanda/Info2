package u8a4;

import java.util.ArrayList;

public interface IRucksack {
	/**
	 * Find an optimal solution for the Rucksack problem
	 *  
	 * Values, weights and selections are related by common indices in the respective collections.
	 * 
	 * @param values the values of the items. Each of them is greater or equal to zero.
	 * @param weights the weights of the items. Each of them is greater or equal to zero.
	 * @param maxWeight the maximum weights, greater or equal to zero
	 * @return a selection of items which represent an optimal solution
	 */
	public Selection findBest(ArrayList<Integer> values, ArrayList<Integer> weights, int maxWeight);
}
