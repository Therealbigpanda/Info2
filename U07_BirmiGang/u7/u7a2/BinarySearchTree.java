package u7a2;

/**
 * generic binary search-tree with integer keys.
 * Empty trees are encoded as null references.
 *
 * @param <T> The type of the things which are managed in the binary search tree
 */

public class BinarySearchTree<T> {
	/**
	 * The key by which the thing is refered to. Must be unique.
	 */
	public int key;
	
	/**
	 * The thing itself.
	 */
	public T thing;
	
	/**
	 * The left sub-tree
	 */
	public BinarySearchTree<T> left;
	
	/**
	 * The right sub-tree
	 */
	public BinarySearchTree<T> right;
	
	/**
	 * Create a new binary search tree without children.
	 * @param key the key by which the thing is refered to
	 * @param thing the new thing
	 */
	public BinarySearchTree(int key, T thing)
	{
		this.key = key;
		this.thing = thing;
		this.left = null;
		this.right = null;
	}
	
	/**
	 * Create a new binary search tree
	 * @param key the key by which the thing is refered to
	 * @param thing the thing which is managed by the new binary search tree
	 * @param left the left sub-tree of the new binary search tree
	 * @param right the right sub-tree of the new binary search tree
	 */
	public BinarySearchTree(int key, T thing, BinarySearchTree<T> left, BinarySearchTree<T> right)
	{
		this.key = key;
		this.thing = thing;
		this.left = left;
		this.right = right;
	}
}
