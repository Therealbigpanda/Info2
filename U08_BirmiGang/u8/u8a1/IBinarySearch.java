package u8a1;

import java.util.ArrayList;

/**
 * Binary search on things with comparable keys.
 * 
 * @param <Key>
 *            The type of the key of the things. Must implement the {@link Comparable} interface.
 * @param <Value>
 *            The type of the things themselves
 */
public interface IBinarySearch<Key extends Comparable<Key>, Value> {
	/**
	 * Performs a binary search on the vector of {@link Unit}s.
	 * 
	 * @param needle
	 *            We are looking for something whose key equals this needle with
	 *            respect to the {@link Comparable} interface.
	 * @param haystack
	 *            The vector of {@link Unit}s where we are searching for the
	 *            needle. This vector must be sorted ascending with respect to
	 *            the keys and the {@link Comparable} interface.
	 * @return A thing from the haystack whose key equals the needle or null
	 *         if no such thing is in the haystack.
	 */
	public Value find(ArrayList<Unit<Key, Value>> haystack, Key needle);
}
