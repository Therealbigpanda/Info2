package u1a3;

/**
 * @version 1.0
 * @author Andrea-Pascal Willi & Andrea Tuccillo
 */
public class Mult {
    
    /**
     * 
     * @param a
     * @param b
     * @return a*b
     */
    private static int f(int a, int b) {
        if (b==1) return a;  if (b%2 == 0) return f(2*a, b/2);
        else return a + f(2*a, b/2);
    }
    
    /**
     *
     * @param a must be positive
     * @param b must be positive && nonzero
     * @return f(a,b);
     * @throws IllegalArgumentException if (a < 1 || b < 1) (TEST)
     */
    public static int mult(int a, int b) throws IllegalArgumentException {
        if (a < 1) throw new IllegalArgumentException("IllegalArgumentException: " + "value a is smaller than 1 " + "(" + a + ")");
        if (b < 1) throw new IllegalArgumentException("IllegalArgumentException: " + "value b is smaller than 1 " + "(" + b + ")");
        return f(a, b);
    }
}
