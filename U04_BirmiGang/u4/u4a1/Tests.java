package u4a1;

import org.junit.Test;
import org.junit.Assert;

/**
 * Tests for the dynamically growing stack.
 */
public class Tests {
	/**
	 * Create a small stack and push one item.
	 */
	@Test public void create()
	{
		Stack stack = new Stack(1);
		Assert.assertEquals("capacity is wrong", 1, stack.capacity());
		Assert.assertEquals("size is wrong", 0, stack.size());
		Assert.assertTrue(stack.empty());
		
		stack.push(23);
		Assert.assertEquals("capacity is wrong", 1, stack.capacity());
		Assert.assertEquals("size is wrong", 1, stack.size());
		Assert.assertFalse(stack.empty());
	}

	/**
	 * Check toString for empty stack.
	 */
	@Test public void toStringEmpty()
	{
		Stack stack = new Stack(3);
		Assert.assertEquals("string representation is wrong", "[]", stack.toString());
	}

	/**
	 * Check toString for filled stack.
	 */
	@Test public void toStringGeneric()
	{
		Stack stack = new Stack(3);
		stack.push(1);
		stack.push(2);
		Assert.assertEquals("string representation is wrong", "[1, 2]", stack.toString());
	}
	
	/**
	 * Check that pop throws an EmptyStackException
	 */
	@Test(expected = java.util.EmptyStackException.class)
	public void popFail()
	{
		new Stack(1).pop();
	}

	/**
	 * Check that peek throws an EmptyStackException
	 */
	@Test(expected = java.util.EmptyStackException.class)
	public void peekFail()
	{
		new Stack(1).peek();
	}
	
	/**
	 * Check behavior of push, peek and pop without reaching the initial capacity.
	 */
	@Test public void pushPeekAndPop()
	{
		final int max = 5;
		Stack stack = new Stack(max);
		Assert.assertEquals("capacity is wrong", max, stack.capacity());
		Assert.assertEquals("size is wrong", 0, stack.size());
		Assert.assertTrue(stack.empty());
		
		for (int i=0; i<max; i++) {
			stack.push(i);
			Assert.assertEquals("capacity is wrong", max, stack.capacity());
			Assert.assertEquals("size is wrong", i+1, stack.size());
		}

		Assert.assertFalse(stack.empty());
		
		for (int i=max-1; i>=0; i--) {
			Assert.assertEquals("top string is wrong", i, stack.peek());
			Assert.assertEquals("capacity is wrong", max, stack.capacity());
			Assert.assertEquals("size is wrong", i+1, stack.size());

			Assert.assertEquals("top string is wrong after first peek", i, stack.peek());
			Assert.assertEquals("capacity is wrong", max, stack.capacity());
			Assert.assertEquals("size is wrong", i+1, stack.size());

			Assert.assertEquals("popped string is wrong", i, stack.pop());
			Assert.assertEquals("capacity is wrong", max, stack.capacity());
			Assert.assertEquals("size is wrong", i, stack.size());
		}
		
		Assert.assertTrue(stack.empty());
	}
	
	/**
	 * Check behavior of push, peek and pop when forcing the stack to grow once.
	 */
	@Test public void grow()
	{
		final int max = 5;
		Stack stack = new Stack(max);
		Assert.assertEquals("capacity is wrong", max, stack.capacity());
		Assert.assertEquals("size is wrong", 0, stack.size());
		Assert.assertTrue(stack.empty());
		
		for (int i=0; i<max; i++) {
			stack.push(i);
			Assert.assertEquals("capacity is wrong", max, stack.capacity());
			Assert.assertEquals("size is wrong", i+1, stack.size());
		}

		Assert.assertFalse(stack.empty());
		
		stack.push(max);
		Assert.assertEquals("capacity is wrong", 2*max, stack.capacity());
		Assert.assertEquals("size is wrong", max+1, stack.size());
		
		
		for (int i=max; i>=0; i--) {
			Assert.assertEquals("top number is wrong", i, stack.peek());
			Assert.assertEquals("capacity is wrong", 2*max, stack.capacity());
			Assert.assertEquals("size is wrong", i+1, stack.size());

			Assert.assertEquals("top number is wrong after first peek", i, stack.peek());
			Assert.assertEquals("capacity is wrong", 2*max, stack.capacity());
			Assert.assertEquals("size is wrong", i+1, stack.size());

			Assert.assertEquals("popped number is wrong", i, stack.pop());
			Assert.assertEquals("capacity is wrong", 2*max, stack.capacity());
			Assert.assertEquals("size is wrong", i, stack.size());
		}
		
		Assert.assertTrue(stack.empty());		
	}
}
