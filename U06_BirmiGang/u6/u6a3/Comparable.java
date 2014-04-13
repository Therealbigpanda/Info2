package u6a3;

/**
 * Interface for objects which can be compared with each other
 */
public interface Comparable {
	/**
	 * Operator <
	 * 
	 * @param rhs the right-hand side of the operation
	 * @return true iff this < rhs; false otherwise
	 */
	public boolean smallerThan(Comparable rhs);
}
