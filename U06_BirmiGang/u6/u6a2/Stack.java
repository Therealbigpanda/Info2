package u6a2;

import java.util.EmptyStackException;

/**
 * Dynamically growing stack.
 */
public class Stack implements IStack {
	class List {
		List(int value, List next)
		{
			this.value = value;
			this.next = next;
		}
		int value;
		List next;
	}
	private List list;
	private int size;

	/**
	 * Creates a new stack
	 */
	public Stack()
	{
		list = null;
		size = 0;
	}

	public String toString() 
	{
		StringBuffer buf = new StringBuffer("[");
		for(List lst=list; lst !=null; lst=lst.next) {
			buf.append(lst.value);
			if (lst.next != null) buf.append(", ");
		}
		buf.append("]");
		return buf.toString();
	}
	
	public void push(int number)
	{
		list = new List(number, list);
		size += 1;
	}
	
	public int pop() throws EmptyStackException 
	{
		if (list == null) throw new EmptyStackException();
		int res = list.value;
		list = list.next;
		size -= 1;
		return res;
	}
	
	public int peek() throws EmptyStackException
	{
		if (list == null) throw new EmptyStackException();
		return list.value;
	}

	public int size() 
    {
		return size;
	}
}
