package u4a1;

import java.util.EmptyStackException;

/**
 * Dynamically growing stack.
 */
public class Stack {
	private int[] buffer;
	private int size;

	/**
	 * Creates a new stack
	 * 
	 * @param capacity the initial capacity of the stack
	 */
	public Stack(int capacity)
	{
	}

    /**
	 * Converts stack to a string representation.
	 * 
	 * @return A ", "-separated list of the numbers, enclosed in square brackets. Bottom numbers come first. 
	 */
	public String toString() 
	{
	}
	
	/**
	 * Doubles the capacity of the stack.
	 * 
	 * Copies all objects to a new buffer of doubled size.
	 */
	private void grow()
	{
	}
	
	/**
	 * Pushes a number onto the top of this stack.
	 * 
	 * Grows the stack if necessary.
	 * 
	 * @param number the number to be pushed onto this stack. 
	 */
	public void push(int number)
	{
	}
	
	/**
	 * Removes the number at the top of this stack and returns it as the value of this function. 
	 * 
	 * @return The number at the top of this stack
	 * @throws EmptyStackException if this stack is empty
	 */
	public int pop() throws EmptyStackException 
	{
	}
	
	/**
	 * Looks at the number at the top of this stack without removing it from the stack. 
	 * 
	 * @return the number at the top of this stack
	 * @throws EmptyStackException if this stack is empty
	 */
	public int peek() throws EmptyStackException
	{
	}

	/**
	 * Tests if this stack is empty. 
	 * 
	 * @return true if and only if this stack contains no items; false otherwise.
	 */
	public boolean empty() 
    {
	}
	
	/**
	 * Get the size of this stack.
	 * 
	 * @return the current number of items on this stack
	 */
	public int size() 
    {
	}
	
	/**
	 * Get the capacity of this stack.
	 *    
	 * @return the maximum number of items this stack can hold without having to grow
	 */
	public int capacity() 
    {
	}
}
