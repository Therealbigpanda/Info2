package u7a2;

import java.util.ArrayList;

/**
 * Utility functions for binary search trees containing generic things
 *
 * @param <T> The type of the things which are managed in the binary search tree
 */
public interface IBinarySearchTreeUtils<T> {

    /**
     * Determines the height of a tree.
     *
     * The empty tree has height zero. The tree consisting only of the root has height one.
     *
     * @param tree the tree in question
     * @return the height of the given tree
     */
    public int height(BinarySearchTree<T> tree);

    /**
     * Checks if a tree is actually a leaf node.
     *
     * A tree is a leave node if and only if (iff) it has no children.
     *
     * @param tree the tree in question. May not be null.
     * @return true iff the given tree is a leaf node.
     */
    public boolean isLeaf(BinarySearchTree<T> tree);

    /**
     * Checks if a tree has only one child.
     *
     * @param tree the tree in question
     * @return true iff the given tree has only one child.
     */
    public boolean hasOneChild(BinarySearchTree<T> tree);

    /**
     * Returns the things of a tree in pre-order.
     *
     * @param tree the tree containing the things
     * @return a ArrayList of the things of the given tree, sorted in pre-order.
     */
    public ArrayList<T> preOrder(BinarySearchTree<T> tree);

    /**
     * Returns the things of a tree in in-order.
     *
     * @param tree the tree containing the things
     * @return a ArrayList of the things of the given tree, sorted in in-order.
     */
    public ArrayList<T> inOrder(BinarySearchTree<T> tree);

    /**
     * Returns the things of a tree in post-order.
     *
     * @param tree the tree containing the things
     * @return a ArrayList of the things of the given tree, sorted in post-order.
     */
    public ArrayList<T> postOrder(BinarySearchTree<T> tree);

    /**
     * Inserts an thing into a tree
     *
     * If an thing with the given key already exists in the tree it is overwritten.
     *
     * @param tree the tree into which the thing is inserted. After the call this tree should not be
     * used any more. Use the returned tree instead.
     * @param key the key of the thing
     * @param thing the thing which is inserted into the tree
     * @return a new tree with the given thing inserted.
     */
    public BinarySearchTree<T> insert(BinarySearchTree<T> tree, int key, T thing);

    /**
     * Finds an thing in a tree.
     *
     * @param tree the tree in which the thing is searched for.
     * @param key the key of the thing which is searched
     * @return the thing if it exists in the tree, and null otherwise.
     */
    public T find(BinarySearchTree<T> tree, int key);

    /**
     * Result of the unlinkSmallest operation
     */
    class UnlinkSmallestResult<T> {

        /**
         * the tree with the smallest key
         */
        public BinarySearchTree<T> smallest;

        /**
         * the remainder of the tree with the smallest tree removed
         */
        public BinarySearchTree<T> tree;

        public UnlinkSmallestResult(BinarySearchTree<T> smallest, BinarySearchTree<T> tree) {
            this.smallest = smallest;
            this.tree = tree;
        }
    }

    /**
     * Removes the node with the smallest key.
     *
     * @param tree the tree from where to remove the node. After this call this tree should not be
     * used any more. Use the returned {@link UnlinkSmallestResult#tree} instead.
     * @return both the tree with the smallest key and the remaining tree with the smallest removed
     */
    public UnlinkSmallestResult<T> unlinkSmallest(BinarySearchTree<T> tree);

    /**
     * Removes a thing from a tree.
     *
     * If the thing is in a leaf node this node is simply removed If the thing is in node with one
     * child this node is replaced by its child. Otherwise the removed node is replaced by the
     * smallest node of its right subtree.
     *
     * @param tree the tree from which the thing is removed. After this call this tree should not be
     * used any more. Use the returned tree instead.
     * @param key the key of the thing
     * @return either a new tree with the given thing removed or the simply the given tree if the
     * thing could not be found.
     */
    public BinarySearchTree<T> remove(BinarySearchTree<T> tree, int key);
}
