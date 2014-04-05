package u5a2;

import list.List;

public class MutableLists {
	/**
	 * Appends a value at the end of a list.
	 * 
	 * @param list the list in question; may not be empty
	 * @param value the value which is appended
	 * @throws IllegalArgumentException if list is empty
	 */
	public static void append(List list, int value) throws IllegalArgumentException
	{
	}
	
	/**
	 * Concatenates two lists.
	 * 
	 * @param head the list to which the other list is appended; may not be empty
	 * @param tail the list which is appended to the other list
	 * @throws IllegalArgumentException if head is empty
	 */
	public static void concat(List head, List tail) throws IllegalArgumentException
	{
	}

	/**
	 * Inserts a new value into a list at an arbitrary position.
	 * 
	 * @param list the list into which the value is inserted.
	 * @param index the position after which the given value is inserted, starting with zero for the head
	 * @param value the value which is added
	 * @throws IndexOutOfBoundsException if the position is greater than the size of the list
	 */
	public static void insertAt(List list, int index, int value) throws IndexOutOfBoundsException
	{
	}
	
	/**
	 * Inserts a list into a list at an arbitrary position.
	 * 
	 * @param list the list into which the other list is inserted.
	 * @param index the position after which the other list is inserted, starting with zero for the head
	 * @param newList the list which is inserted into the other list
	 * @throws IndexOutOfBoundsException if the position is greater than the size of the list
	 */
	public static void insertAt(List list, int index, List newList) throws IndexOutOfBoundsException
	{
	}

	/**
	 * Removes a value from a list
	 * 
	 * @param list the list from which the value is removed. 
	 * After the operation the state of this list is undefined.
	 * Don't use it any more. Use the returned list instead.
	 * @param index the position of the value which is removed, starting with zero for the head
	 * @return the list with the required value removed
	 * @throws IndexOutOfBoundsException if position is greater than the size of the list
	 */
	public static List remove(List list, int index) throws IndexOutOfBoundsException
	{
	}
}
