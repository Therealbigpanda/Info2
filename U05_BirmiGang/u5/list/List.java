package list;

/**
 * Linked list with int values.
 * 
 * The empty list is represented by a null reference.
 */
public class List {
	public int value;
	public List next;

	/**
	 * Creates a new list
	 * 
	 * @param value value of the head of the list
	 * @param next reference to rest of the list; may be null
	 */
	public List(int value, List next) 
	{
		this.value = value;
		this.next = next;
	}
}
