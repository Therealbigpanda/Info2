package u4a2;

import u4a1.Stack;

public class IterativeAckermann {
    /**
     * Stack-based iterative implementation of the Ackermann function.
     * 
     * @param n parameter n
     * @param m parameter m
     * @return Ackermann(n,m)
     */
    public int A(int n, int m) {
        Stack stack = new Stack(1);
        while(stack.size() > 0 || n != 0) {
            if (n == 0) {
                ++m;
                n = stack.pop();
            }
            else if (m == 0) {
                --n;
                ++m;
            } else {
                stack.push(n-1);
                --m;
            }
        }
        return ++m;
    }
}
