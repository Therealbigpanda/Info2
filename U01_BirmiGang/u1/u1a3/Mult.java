package u1a3;

/**
 * @version 1.0
 * @author Andrea-Pascal Willi & Andrea Tuccillo
 */
public class Mult {
    private static int f(int a, int b) {
        if (b==1) return a;  if (b%2 == 0) return f(2*a, b/2);
        else return a + f(2*a, b/2);
    }
    
    /**
     *
     * @param a
     * @param b
     * @return f(a,b);
     * @throws IllegalArgumentException
     */
    public static int mult(int a, int b) {
        if (a < 1 || b < 1) throw new IllegalArgumentException();
        return f(a, b);
    }
}
