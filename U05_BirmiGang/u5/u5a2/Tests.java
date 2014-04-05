package u5a2;

import list.List;
import org.junit.Test;

public class Tests extends list.Tests {
	@Test(expected = IllegalArgumentException.class)
	public void appendFail()
	{
		MutableLists.append(null, 1);
	}
	
	private void checkAppend(List list, int value, String expected)
	{
		MutableLists.append(list, value);
		check(list, expected);
	}
	
	@Test
	public void append()
	{
		checkAppend(create(1), 2, "1, 2, null");
		checkAppend(create(1,2), 3, "2, 1, 3, null");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void concatFail()
	{
		MutableLists.concat(null, create(1));
	}
	
	private void checkConcat(List first, List second, String expected)
	{
		MutableLists.concat(first, second);
		check(first, expected);
	}
	
	@Test
	public void concat()
	{
		checkConcat(create(1), null, "1, null");
		checkConcat(create(1,2), null, "2, 1, null");
		checkConcat(create(1), create(2), "1, 2, null");
		checkConcat(create(1,2), create(3,4), "2, 1, 4, 3, null");
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void insertAtFail1()
	{
		MutableLists.insertAt(null, 0, 0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void insertAtFail2()
	{
		MutableLists.insertAt(create(1), 1, 0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void insertAtFail3()
	{
		MutableLists.insertAt(create(1,2), 2, 0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void insertAtFail4()
	{
		MutableLists.insertAt(create(1,2), -1, 0);
	}
	
	private void checkInsertAt(List list, int index, int value, String expected)
	{
		MutableLists.insertAt(list, index, value);
		check(list, expected);
	}
	
	@Test
	public void insertAt()
	{
		checkInsertAt(create(1), 0, 2, "1, 2, null");
		checkInsertAt(create(1,2), 1, 3, "2, 1, 3, null");
		checkInsertAt(create(1,2,3), 2, 4, "3, 2, 1, 4, null");
		checkInsertAt(create(1,2,3), 1, 4, "3, 2, 4, 1, null");
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void insertAtFailList1()
	{
		MutableLists.insertAt(null, 0, new List(1, null));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void insertAtFailList2()
	{
		MutableLists.insertAt(create(1), 1, new List(1, null));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void insertAtFailList3()
	{
		MutableLists.insertAt(create(1,2), 2, new List(1, null));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void insertAtFailList4()
	{
		MutableLists.insertAt(create(1,2), -1, new List(1, null));
	}
	
	private void checkInsertAtList(List list, int index, List newList, String expected)
	{
		MutableLists.insertAt(list, index, newList);
		check(list, expected);
	}
	
	@Test
	public void insertAtList()
	{
		List newList = new List(7, new List(8, new List(9, null)));
		
		checkInsertAtList(create(1), 0, newList, "1, 7, 8, 9, null");
		checkInsertAtList(create(1,2), 1, newList, "2, 1, 7, 8, 9, null");
		checkInsertAtList(create(1,2,3), 2, newList, "3, 2, 1, 7, 8, 9, null");
		checkInsertAtList(create(1,2,3), 1, newList, "3, 2, 7, 8, 9, 1, null");
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void removeFail1()
	{
		MutableLists.remove(null, 0);		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void removeFail2()
	{
		MutableLists.remove(create(1), 1);		
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void removeFail3()
	{
		MutableLists.remove(create(1,2), 2);		
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void removeFail4()
	{
		MutableLists.remove(create(1,2), -1);		
	}
	
	private void checkRemove(List list, int position, String expected)
	{
		check(MutableLists.remove(list, position), expected);
	}
	
	@Test
	public void remove()
	{
		checkRemove(create(1), 0, "null");
		checkRemove(create(1,2), 0, "1, null");
		checkRemove(create(1,2), 1, "2, null");
		checkRemove(create(1,2,3), 1, "3, 1, null");
	}
}
