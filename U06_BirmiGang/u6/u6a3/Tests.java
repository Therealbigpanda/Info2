package u6a3;

import org.junit.*;

public class Tests {
	private IListUtils utils;
	private GenericList list;
	
	private void add(Object o)
	{
		list = utils.add(list, o); 
	}
	
	private void sort()
	{
		list = utils.sort(list);
	}
	
	private void check(int size, String expected)
	{
		Assert.assertEquals("wrong size", size, utils.size(list));
		Assert.assertEquals(expected, utils.toString(list));
	}
	
	@Before public void setup()
	{
		utils = ListUtilsFactory.create();
		list = null;
	}
	
	@Test public void integers()
	{
		check(0, "null");
		add(23);
		check(1, "23, null");
		add(42);
		check(2, "42, 23, null");
	}
	
	@Test public void geometricObjects()
	{
		check(0, "null");
		add(new Triangle(2, 3));
		check(1, "Triangle(2,3), null");
		add(new Rectangle(8, 9));
		check(2, "Rectangle(8,9), Triangle(2,3), null");
	}
	
	@Test public void mixed()
	{
		check(0, "null");
		add(23);
		check(1, "23, null");
		add(new Triangle(2, 3));
		check(2, "Triangle(2,3), 23, null");
	}
	
	@Test public void sortEmpty()
	{
		sort();
		check(0, "null");
	}

	@Test public void sortOne()
	{
		add(new Triangle(2, 3));
		sort();
		check(1, "Triangle(2,3), null");
	}

	@Test public void sortTwo()
	{
		add(new Triangle(2, 3));
		add(new Rectangle(2, 2));
		sort();
		check(2, "Triangle(2,3), Rectangle(2,2), null");
	}
	
	@Test public void sortGeneric()
	{
		add(new Triangle(2,3));
		add(new Rectangle(2,2));
		add(new Triangle(2,2));
		add(new Rectangle(2,3));
		sort();
		check(4, "Triangle(2,2), Triangle(2,3), Rectangle(2,2), Rectangle(2,3), null");
	}
}
