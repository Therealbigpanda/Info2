package u6a3;

/**
 * A single-linked list of Objects
 */
public class GenericList {
	public Object value;
	public GenericList next;
	
	public GenericList(Object value, GenericList next)
	{
		this.value = value;
		this.next = next;
	}
}
