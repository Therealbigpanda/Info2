package u6a2;

import java.util.EmptyStackException;

public interface IStack {

    /**
     * Tests if this stack is empty.
     *
     * @return true if and only if this stack contains no items; false otherwise.
     */
    public boolean empty();

    /**
     * Converts the stack to a string representation.
     *
     * @return A ", "-separated list of the numbers, enclosed in square brackets. Top numbers come first.
     */
    @Override
    public String toString();

    /**
     * Pushes a number onto the top of this stack.
     *
     * @param number the number which is pushed onto this stack.
     */
    public void push(int number);

    /**
     * Removes the number at the top of this stack and returns it as the value of this function.
     *
     * @return The number at the top of this stack
     * @throws EmptyStackException if this stack is empty
     */
    public int pop() throws EmptyStackException;

    /**
     * Looks at the number at the top of this stack without removing it.
     *
     * @return the number at the top of this stack
     * @throws EmptyStackException if this stack is empty
     */
    public int peek() throws EmptyStackException;

    /**
     * Get the size of this stack.
     *
     * @return the current number of items on this stack
     */
    public int size();
}
