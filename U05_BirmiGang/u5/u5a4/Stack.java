package u5a4;

import java.util.EmptyStackException;

import list.List;
import u5a1.Lists;

/**
 * Dynamically growing stack.
 */
public class Stack {
    private List list;

    /**
     * Creates a new stack
     */
    public Stack() {
        list = null;
    }

    /**
     * Converts the stack to a string representation.
     * 
     * @return A ", "-separated list of the numbers, enclosed in square brackets. Top numbers come first. 
     */
    public String toString() {
        StringBuffer buf = new StringBuffer("[");
        if (list != null) {
            buf.append(list.value);
            toString(list.next, buf);
        }
        buf.append("]");
        return buf.toString();
    }

    private static void toString(List lst, StringBuffer buf)
    {
        if (lst == null) return;
        buf.append(", ");
        buf.append(lst.value);
        toString(lst.next, buf);
    }

    /**
     * Pushes a number onto the top of this stack.
     * 
     * @param number the number which is pushed onto this stack. 
     */
    public void push(int number) {
        list = new List(number, list);
    }

    /**
     * Removes the number at the top of this stack and returns it as the value of this function. 
     * 
     * @return The number at the top of this stack
     * @throws EmptyStackException if this stack is empty
     */
    public int pop() throws EmptyStackException {
        if (list == null) throw new EmptyStackException();
        int value = list.value;
        list = list.next;
        return value;
    }

    /**
     * Looks at the number at the top of this stack without removing it.
     * 
     * @return the number at the top of this stack
     * @throws EmptyStackException if this stack is empty
     */
    public int peek() throws EmptyStackException {
        if (list == null) throw new EmptyStackException();
        return list.value;
    }

    /**
     * Tests if this stack is empty. 
     * 
     * @return true if and only if this stack contains no items; false otherwise.
     */
    public boolean empty() {
        return list == null;
    }

    /**
     * Get the size of this stack.
     * 
     * @return the current number of items on this stack
     */
    public int size() {
        return Lists.size(list);
    }
}
