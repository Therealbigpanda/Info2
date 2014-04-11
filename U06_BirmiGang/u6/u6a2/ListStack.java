package u6a2;

import java.util.EmptyStackException;

/**
 * Dynamically growing stack based on Linked Lists.
 */
public class ListStack implements IStack {
    private List list;
    private int size;

    /**
     * Creates a new stack
     */
    public ListStack() {
        list = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder("[");
        for (List lst = list; lst != null; lst = lst.next) {
            buf.append(lst.value);
            if (lst.next != null) {
                buf.append(", ");
            }
        }
        buf.append("]");
        return buf.toString();
    }

    @Override
    public void push(int number) {
        list = new List(number, list);
        size += 1;
    }

    @Override
    public int pop() throws EmptyStackException {
        if (list == null) {
            throw new EmptyStackException();
        }
        int res = list.value;
        list = list.next;
        size -= 1;
        return res;
    }

    @Override
    public int peek() throws EmptyStackException {
        if (list == null) {
            throw new EmptyStackException();
        }
        return list.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }  
}
