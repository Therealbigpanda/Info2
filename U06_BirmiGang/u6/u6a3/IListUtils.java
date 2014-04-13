package u6a3;

public interface IListUtils {

    /**
     * Returns a string representation of the list.
     *
     * @return a comma-separated list of the list values
     */
    public String toString(GenericList list);

    /**
     * Adds a value to at the beginning of a list.
     *
     * @param list the list to which the value is added
     * @param value the value which is added to the list
     * @return a new list with the new element first followed by the given list
     */
    public GenericList add(GenericList list, Object value);

    /**
     * Retrieves the size of a list.
     *
     * @param list the list in question
     * @return the number of values in the list.
     */
    public int size(GenericList list);

    /**
     * Sorts a list of {@link Comparable} objects in ascending order.
     *
     * @param list the list which is sorted. After the operation the state of this list is
     * undefined. Don't use it any more. Use the returned list instead.
     * @return the sorted variant of the given list
     */
    public GenericList sort(GenericList list);
}
