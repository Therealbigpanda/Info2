package u6a2;

import org.junit.Test;
import org.junit.Assert;

/**
 * Tests for the dynamically growing stack.
 */
public class Tests {

    /**
     * Check if empty for new stack, full stack, emptied stack
     */
    @Test
    public void empty() {
        IStack stack = StackFactory.create();
        Assert.assertTrue("new stack with zero elements is not empty", stack.empty());
        
        for (int i=0; i<8; i++) {
            stack.push(i);
        }
        Assert.assertFalse("stack with elements is empty", stack.empty());

        for (int i=8; i>0; i--) {
            stack.pop();
        }
        Assert.assertTrue("stack with zero elements is not empty", stack.empty());

    }
    
    
    /**
     * Create a small stack and push one item onto it.
     */
    @Test
    public void create() {
        IStack stack = StackFactory.create();
        Assert.assertEquals("size is wrong", 0, stack.size());

        stack.push(23);
        Assert.assertEquals("size is wrong", 1, stack.size());
    }

    /**
     * Check toString for empty stack.
     */
    @Test
    public void toStringEmpty() {
        IStack stack = StackFactory.create();
        Assert.assertEquals("string representation is wrong", "[]", stack.toString());
    }

    /**
     * Check toString for filled stack.
     */
    @Test
    public void toStringGeneric() {
        IStack stack = StackFactory.create();
        for (int i = 0; i < 30; i++) {
            stack.push(i);
        }
        Assert.assertEquals("string representation is wrong",
                "[29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]",
                stack.toString());
    }

    /**
     * Check behavior of push, peek and pop without reaching the initial capacity.
     */
    private void fillAndEmpty(IStack stack) {
        final int max = 100;
        Assert.assertEquals("size is wrong", 0, stack.size());

        for (int i = 0; i < max; i++) {
            stack.push(i);
            Assert.assertEquals("size is wrong", i + 1, stack.size());
        }

        for (int i = max - 1; i >= 0; i--) {
            Assert.assertEquals("top number is wrong", i, stack.peek());
            Assert.assertEquals("size is wrong", i + 1, stack.size());

            Assert.assertEquals("top number is wrong after first peek", i, stack.peek());
            Assert.assertEquals("size is wrong", i + 1, stack.size());

            Assert.assertEquals("popped number is wrong", i, stack.pop());
            Assert.assertEquals("size is wrong", i, stack.size());
        }
    }

    @Test
    public void pushPeekAndPop() {
        IStack stack = StackFactory.create();
        fillAndEmpty(stack);
        fillAndEmpty(stack);
    }
}
