package tree.bst;

import java.util.Random;
import tree.Node;
import tree.BTreePrinter;

public class BSTUtils {
    public static Node insert(final Node<Integer> root, final int data) {
        if (root == null) {
            System.out.println("Next data to insert: " + data);
            return new Node(data);
        }
        if (data < root.data) {
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }
        return root;
    }

    public static int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int lh = getHeight(node.left);
        int rh = getHeight(node.right);
        if (lh > rh) {
            return lh + 1;
        }
        return rh + 1;
    }

    public static void main(final String[] args) {
        Node root = null;
        int count = 10;
        final Random random = new Random();
        while (count-- > 0) {
            root = insert(root, random.nextInt(100));
        }
        BTreePrinter.printNode(root);
    }
}

