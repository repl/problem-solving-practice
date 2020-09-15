package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import tree.bst.BSTUtils;

public class CountLeafNodes {
    public static void main(final String[] args) {
        Node root = null;
        int count = 10;
        final Random random = new Random();
        while (count-- > 0) {
            root = BSTUtils.insert(root, random.nextInt(100));
        }
        BTreePrinter.printNode(root);

        System.out.print("\nNumber of leaves: " + count(root));
    }

    private static int count(Node node) {
        if (node == null) {
          return 0;
        } else {
          if (node.left == null && node.right == null) {
            return 1;
          } else {
            return count(node.left) + count(node.right);
          }
        }
    }

    private static void preorderNoRec(Node node) {
        Stack<Node> stack = new Stack<Node>();
        stack.push(node);
        while (!stack.empty()) {
            Node n = stack.pop();
            System.out.print(n.data + " ");
            if (n.right != null) {
                stack.push(n.right);
            }
            if (n.left != null) {
                stack.push(n.left);
            }
        }
    }
}
