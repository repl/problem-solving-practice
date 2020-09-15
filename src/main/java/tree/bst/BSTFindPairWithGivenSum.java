package tree.bst;

import java.util.*;
import tree.Node;
import tree.BTreePrinter;

public class BSTFindPairWithGivenSum {
    public static void main(final String[] args) {
        Node<Integer> root = null;
        for (int elem: Arrays.asList(15, 10, 20, 8, 12,16, 25)) {
            root = BSTUtils.insert(root, elem);
        }
        BTreePrinter.printNode(root);
        int sum = 42;
        int[] pair = findPairImpl1(root, sum);
        if (pair == null) {
            System.out.println("No pair found!");
        } else {
            System.out.println("Pair found: " + pair[0] + ", " + pair[1]);
        }
    }

    private static int[] findPairImpl1(Node root, int sum) {
        //inorder traversal. check if the diff is present in the set, then the pair is found
        //else store the element in a set.
        Set store = new HashSet<>();
        return inorderSearch(root, sum, store);
    }

    private static int[] inorderSearch(Node<Integer> node, int sum, Set store) {
        if (node == null) {
            return null;
        }
        int[] fromLeft = inorderSearch(node.left, sum, store);
        if (fromLeft != null) {
            return fromLeft;
        } else {
            int diff = sum - node.data;
            if (store.contains(diff)) {
                int[] ret = {diff, node.data};
                return ret;
            } else {
                store.add(node.data);
                return inorderSearch(node.right, sum, store);
            }
        }
    }

    private static int[] findPairImpl2(Node root, int sum) {
        //assuming this is a BST.
        //inorder and reverse inorder traversal simultaneously.
        //else store the element in a set.
        return inorderForwardAndReverseSearch(root, sum);
    }

    private static int[] inorderForwardAndReverseSearch(Node root, int sum) {
        return null;
    }
}
