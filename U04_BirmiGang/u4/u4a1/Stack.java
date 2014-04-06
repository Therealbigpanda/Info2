package u4a1;

import java.util.Arrays;
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
            size = 0;
            buffer = new int[capacity];
	}

        /**
	 * Converts stack to a string representation.
	 * 
	 * @return A ", "-separated list of the numbers, enclosed in square brackets. Bottom numbers come first. 
	 */
        @Override
	public String toString() 
	{
            StringBuilder strb = new StringBuilder();
            
            strb.append('[');
            for(int i = 0; i < size; i++)
            {
                strb.append(buffer[i]);
                strb.append(", ");
            }
            if(strb.length() > 1)
            strb.replace(strb.length()-2, strb.length(), "]");
            else strb.append(']');
            
            return strb.toString();
	}
	
	/**
	 * Doubles the capacity of the stack.
	 * 
	 * Copies all objects to a new buffer of doubled size.
	 */
	private void grow()
	{
            if(size < 1) buffer = new int[1];
            else
            {
                buffer = Arrays.copyOf(buffer, size*2);
            }
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
            if(size > buffer.length-1) grow();
            buffer[size++] = number;
	}
	
	/**
	 * Removes the number at the top of this stack and returns it as the value of this function. 
	 * 
	 * @return The number at the top of this stack
	 * @throws EmptyStackException if this stack is empty
	 */
	public int pop() throws EmptyStackException 
	{
            if(size <= 0) throw new EmptyStackException();
            return buffer[--size];
	}
	
	/**
	 * Looks at the number at the top of this stack without removing it from the stack. 
	 * 
	 * @return the number at the top of this stack
	 * @throws EmptyStackException if this stack is empty
	 */
	public int peek() throws EmptyStackException
	{
            if(size <= 0) throw new EmptyStackException();
            return buffer[size-1];
	}

	/**
	 * Tests if this stack is empty. 
	 * 
	 * @return true if and only if this stack contains no items; false otherwise.
	 */
	public boolean empty() 
        {
            return size < 1;
	}
	
	/**
	 * Get the size of this stack.
	 * 
	 * @return the current number of items on this stack
	 */
	public int size() 
        {
            return size;
	}
	
	/**
	 * Get the capacity of this stack.
	 *    
	 * @return the maximum number of items this stack can hold without having to grow
	 */
	public int capacity() 
        {
            return buffer.length;
	}
}