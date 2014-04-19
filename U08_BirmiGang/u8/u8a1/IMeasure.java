package u8a1;

/**
 * Support statistics of binary search algorithms
 */
public interface IMeasure {
	/** 
	 * Set the factor for the binary search.
	 * 
	 * A factor of two means that the search space is split into half-half in each recursion step.
	 * A factor of three means that the search space is split into one third and two thirds in each recursion step.
	 * In each case integer division is assumed, which means fractions are rounded down.
	 * 
	 * This method is called first after instantiation.
	 * 
	 * @param factor
	 *            an integer value
	 */
	public void setFactor(int factor);

	/**
	 * Retrieve the statistics.
	 * 
	 * @return the number of recursive calls of the binary search algorithm
	 *         performed since instantiation.
	 */
	public int getNumberofCalls();
}
