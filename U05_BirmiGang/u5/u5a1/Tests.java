package u5a1;

import java.util.NoSuchElementException;

import list.List;

import org.junit.Test;
import org.junit.Assert;


public class Tests extends list.Tests {
	@Test
	public void add()
	{
		List list = null;
		check(list, "null");
		List list2 = Lists.add(list, 1);
		check(list, "null");
		check(list2, "1, null");
		List list3 = Lists.add(list2, 2);
		check(list, "null");
		check(list2, "1, null");
		check(list3, "2, 1, null");
	}
	
	private void checkSize(List list, int expected)
	{
		Assert.assertEquals(expected, Lists.size(list));
	}
	
	@Test
	public void size()
	{
		checkSize(null, 0);
		checkSize(create(1), 1);
		checkSize(create(1,2), 2);
	}	
	
	private void checkSum(List list, int expected)
	{
		Assert.assertEquals(expected, Lists.sum(list));
	}
	
	@Test
	public void sum()
	{
		checkSum(null, 0);
		checkSum(create(1), 1);
		checkSum(create(1,2), 3);
		checkSum(create(1,2,3), 6);
	}
	
	private void checkLast(List list, String expected)
	{
		check(Lists.last(list), expected);
	}
	
	@Test
	public void last()
	{
		checkLast(null, "null");
		checkLast(create(1), "1, null");
		checkLast(create(1,2), "1, null");
		checkLast(create(1,2,3), "1, null");
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void sublistFail1()
	{
		Lists.sublist(null, 0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void sublistFail2()
	{
		Lists.sublist(create(1), 1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void sublistFail3()
	{
		Lists.sublist(create(1,2), 2);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void sublistFail4()
	{
		Lists.sublist(create(1,2), -1);
	}
	
	private void checkSublist(List list, int index, String expected)
	{
		check(Lists.sublist(list, index), expected);
	}

	@Test
	public void sublist()
	{
		checkSublist(create(1), 0, "1, null");
		checkSublist(create(1,2), 0, "2, 1, null");
		checkSublist(create(1,2), 1, "1, null");
		checkSublist(create(1,2,3), 0, "3, 2, 1, null");
		checkSublist(create(1,2,3), 1, "2, 1, null");
		checkSublist(create(1,2,3), 2, "1, null");
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void valueAtFail1()
	{
		Lists.valueAt(null, 0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void valueAtFail2()
	{
		Lists.valueAt(create(1), 1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void valueAtFail3()
	{
		Lists.valueAt(create(2), 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void valueAtFail4()
	{
		Lists.valueAt(create(2), -1);
	}
	
	private void checkValue(List list, int index, int value)
	{
		Assert.assertEquals(value, Lists.valueAt(list, index));
	}
	
	@Test
	public void valueAt()
	{
		checkValue(create(1), 0, 1);
		checkValue(create(1,2), 0, 2);
		checkValue(create(1,2), 1, 1);
		checkValue(create(1,2,3), 0, 3);
		checkValue(create(1,2,3), 1, 2);
		checkValue(create(1,2,3), 2, 1);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void findFail1()
	{
		Lists.index(null, 1);
	}

	@Test(expected = NoSuchElementException.class)
	public void findFail2()
	{
		Lists.index(create(1), 2);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void findFail3()
	{
		Lists.index(create(1,2), 3);
	}
	
	private void checkFind(List list, int value, int index)
	{
		Assert.assertEquals(index, Lists.index(list, value));
	}
	
	@Test
	public void find()
	{
		checkFind(create(1), 1, 0);
		checkFind(create(1,2), 1, 1);
		checkFind(create(1,2), 2, 0);
		checkFind(create(1,2,3), 1, 2);
		checkFind(create(1,2,3), 2, 1);
		checkFind(create(1,2,3), 3, 0);	
	}
}
