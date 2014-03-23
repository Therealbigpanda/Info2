package u4a2;

/**
 * Recursive implementation of the Ackermann function.
 */
public class RecursiveAckermann {
	/**
	 * Recursive implementation of the Ackermann function.
	 * 
	 * @param n parameter n
	 * @param m parameter m
	 * @return Ackermann(n,m)
	 */
	public int A(int n, int m)
	{
		if (n == 0) return m + 1;
		if (m == 0) return A(n-1, 1);
		return A(n-1, A(n, m-1));
	}
}
