package list;

import org.junit.Assert;


import u5a1.Lists;

public class Tests {
	public void check(List list, String expected) 
	{ 
		Assert.assertEquals(expected, Lists.toString(list));	
	}
	
	public List create(int... values) 
	{
		List list = null;
		for (int i=0; i<values.length; i++) {
			list = Lists.add(list, values[i]);
		}
		return list;
	}
}
