package u6a2;

import u6a4.ChunkedStack;

public class StackFactory {

    public static IStack create() {
        return new ChunkedStack();
    }
}
