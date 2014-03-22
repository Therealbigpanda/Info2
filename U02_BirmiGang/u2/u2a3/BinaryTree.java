package u2a3;
/**
 *
 * @author Birmigang: Andrea-Pascal Willi & Andrea Tuccillo
 */
public class BinaryTree {
	private char[] tree;
	
	/**
	 * Determines the index of the father.
	 * 
	 * The root is its own father.
	 * 
	 * @param node index of the children
	 * @return index of the father
	 */
	public static int father(int node)
	{
            if(node == 0) return 0;
            if((node % 2) == 0) return node/2-1;
            return node/2;
	}
	
	/**
	 * Determines the index of the left child.
	 * 
	 * @param node index of the father
	 * @return index of the left child
	 */
	public static int leftChild(int node)
	{
            return node*2+1;
	}
	
	/**
	 * Determines the index of the right child.
	 * 
	 * @param node index of the father
	 * @return index of the right child
	 */
	public static int rightChild(int node)
	{
            return node*2+2;
	}
	
	/**
	 * Check if the given array represents a valid binary tree.
	 * 
	 * @param array a binary tree encoded as char array
	 * @throws IllegalArgumentException if check fails
	 */
	private static void checkTree(char[] array)
	{
            if (array.length == 0) throw new IllegalArgumentException();
            for(int i = 0; i < array.length; i++){
                if (array[father(i)] == ' ' && array[i] != ' ') throw new IllegalArgumentException();
            }    
	}
	
	/**
	 * Create a new binary tree from the given array representation.
	 * 
	 * The array stores the values of the binary tree in breadth-first order. 
	 * A space encodes a missing node. 
	 * 
	 * @param array the new tree
	 * @throws IllegalArgumentException if array does not represent a valid binary tree.
	 */
	public BinaryTree(char[] array)
	{
		checkTree(array);
		this.tree = array;
	}
	        
	/**
         * @return String of generatestring(0,0)
	 */
        @Override
	public String toString() {return generatestring(0,0);}
        
        /**
         * Builds a string representing a binary tree in indented form
         * @param node: root of current recursion
         * @param depth: # of recursions
         * @return String of binary tree in indented form
         */
        private String generatestring(int node, int depth)
        {
            if(node >= tree.length || tree[node] == ' ') return "";
            String returnstring = "";
            for(int i = 0; i < depth; i++) returnstring += " "; 
            returnstring += (tree[node] + "\n");
            returnstring += generatestring(leftChild(node), depth+1);
            returnstring += generatestring(rightChild(node), depth+1);
            return returnstring;
        }
}