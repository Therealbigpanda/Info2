package u8a4;

import java.util.ArrayList;

/**
 * Efficient array of booleans.
 * 
 * The booleans are stored in an integer.  Starting from the less significant bit up to 
 * the index {@link Selection#size()}  the respective boolean is true iff the bit is 1.
 * 
 * The values of all other bits are undefined.
 */
public class Selection {
	private int bits;
	private int size;

	/**
	 * Create a selection
	 * @param size the size of the selection
	 * @param bits the values of the booleans encoded as integer
	 */
	public Selection(int size, int bits)
	{
		setSize(size);
		setBits(bits);
	}
	
	/**
	 * Create a selection with all booleans set to false
	 * 
	 * @param size the size of the selection. 0 <= size <= 32.
	 */
	public Selection(int size) 
	{
		setSize(size);
		setBits(0);
	}

	/**
	 * Set the boolean values
	 * 
	 * @param bits the boolean values encoded as integer.
	 */
	public void setBits(int bits)
	{
		this.bits = bits;
	}
	
	/**
	 * Get the boolean values
	 * 
	 * @return the boolean values encoded as integer.
	 */
	public int bits()
	{
		return bits;
	}
	
	/**
	 * Set the size of the selection.
	 * 
	 * If the size is increased the values of the new booleans are undefined.
	 * 
	 * @param size the new size of the selection. 0 <= size <= 32
	 */
	public void setSize(int size)
	{
		if (size > 32) throw new IllegalArgumentException("a maximum of 32 bits is supported only");
		if (size < 0) throw new IllegalArgumentException("the size must be greater or equal to zero");
		this.size = size;
	}

	/**
	 * Get the size of the selection
	 * @return the size of the selection
	 */
	public int size()
	{
		return size;
	}
	
	/**
	 * Set a boolean value
	 * 
	 * @param index the index of the boolean
	 * @param value the value of the boolean
	 */
	public void set(int index, boolean value)
	{
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (value) {
			int mask = 1 << index;
			bits = bits | mask;
		} else {
			int mask = ~(1 << index);
			bits = bits & mask;
		}
	}
	
	/**
	 * Get a boolean values
	 * 
	 * @param index the index of the boolean
	 * @return the value of the selected boolean
	 */
	public boolean get(int index)
	{
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		int mask = 1 << index;
		return (bits & mask) != 0;
	}
	
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("[");
		for (int i=0; i<size; i++) {
			if (i != 0) buf.append(", ");
			if (get(i)) {
				buf.append("true");
			} else {
				buf.append("false");
			}
		}
		buf.append("]");
		return buf.toString();
	}
	
	/**
	 * Calculates a selected sum
	 * @param addends a vector of integers. The vector must be as large as the selection.
	 * @return the sum of all addends for which the corresponding booleans are true.
	 */
	public int sum(ArrayList<Integer> addends)
	{
		if (addends.size() != size()) throw new IllegalArgumentException("Not enough or too many addends");
		int sum = 0;
		for (int i=0; i<addends.size(); i++) {
			if (get(i)) {
				sum += addends.get(i);
			}
		}
		return sum;
	}
}