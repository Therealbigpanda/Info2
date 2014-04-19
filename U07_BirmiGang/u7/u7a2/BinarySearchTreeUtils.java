package u7a2;

import java.util.ArrayList;

/**
 *
 * @author Andrea-Pascal Willi
 * @param <T> thing
 */
public class BinarySearchTreeUtils<T> implements IBinarySearchTreeUtils {

    BinarySearchTree tree;

    public BinarySearchTreeUtils(int key, T thing) {
        this.tree = new BinarySearchTree(key, thing);
    }

    @Override
    public int height(BinarySearchTree tree) {
        if (tree == null) {
            return 0;
        }
//        int leftHeight = height(tree.left)+1;
//        int rightHeight = height(tree.right)+1;
//        return leftHeight > rightHeight ? leftHeight : rightHeight;
        return 1 + Math.max(height(tree.left), height(tree.right));
    }

    @Override
    public boolean isLeaf(BinarySearchTree tree) {
        return (tree.left == null && tree.right == null);
    }

    @Override
    public boolean hasOneChild(BinarySearchTree tree) {
        return (tree.left == null) ^ (tree.right == null); //XOR
    }

    @Override
    public ArrayList preOrder(BinarySearchTree tree) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(tree.thing);
        if (tree.left != null) {
            arrayList.addAll(preOrder(tree.left));
        }
        if (tree.right != null) {
            arrayList.addAll(preOrder(tree.right));
        }
        return arrayList;
    }

    @Override
    public ArrayList inOrder(BinarySearchTree tree) {
        ArrayList arrayList = new ArrayList();
        if (tree.left != null) {
            arrayList.addAll(inOrder(tree.left));
        }
        arrayList.add(tree.thing);
        if (tree.right != null) {
            arrayList.addAll(inOrder(tree.right));
        }
        return arrayList;
    }

    @Override
    public ArrayList postOrder(BinarySearchTree tree) {
        ArrayList arrayList = new ArrayList();
        if (tree.left != null) {
            arrayList.addAll(postOrder(tree.left));
        }
        if (tree.right != null) {
            arrayList.addAll(postOrder(tree.right));
        }
        arrayList.add(tree.thing);
        return arrayList;
    }

    @Override
    public BinarySearchTree insert(BinarySearchTree tree, int key, Object thing) {
        if (tree == null) {
            return new BinarySearchTree(key, thing);
        }
        if (key < tree.key) {
            tree.left = insert(tree.left, key, thing);
            return tree;
        }
        if (key > tree.key) {
            tree.right = insert(tree.right, key, thing);
            return tree;
        }
        tree.thing = thing;
        return tree;
    }

    @Override
    public Object find(BinarySearchTree tree, int key) {
        if (tree == null) {
            return null;
        }
        if (key < tree.key) {
            return find(tree.left, key);
        }
        if (key > tree.key) {
            return find(tree.right, key);
        }
        if (key == tree.key) {
            return tree.thing;
        }
        return null;
    }

    @Override
    public UnlinkSmallestResult unlinkSmallest(BinarySearchTree tree) {
        if (tree == null) {
            return null;
        }
        if (tree.left == null) {
            return new UnlinkSmallestResult(tree, tree.right);
        }
        UnlinkSmallestResult result = unlinkSmallest(tree.left);
        tree.left = result.tree;
        result.tree = tree;
        return result;
    }

    @Override
    public BinarySearchTree remove(BinarySearchTree tree, int key) {
        if (tree == null) {
            return null;
        }
        if (key > tree.key) {
            tree.right = remove(tree.right, key);
            return tree;
        }
        if (key < tree.key) {
            tree.left = remove(tree.left, key);
            return tree;
        }

        if (isLeaf(tree)) {
            return null;
        }
        if (tree.right == null) {
            return tree.left;
        }
        if (tree.left == null) {
            return tree.right;
        }

        UnlinkSmallestResult result = unlinkSmallest(tree.right);
        result.smallest.left = tree.left;
        result.smallest.right = result.tree;
        tree = result.smallest;
        return tree;
    }
}
