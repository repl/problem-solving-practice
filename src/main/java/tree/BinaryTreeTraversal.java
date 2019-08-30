package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import tree.bst.BSTUtils;

public class BinaryTreeTraversal {
    public static void main(final String[] args) {
        Node root = null;
        int count = 10;
        final Random random = new Random();
        while (count-- > 0) {
            root = BSTUtils.insert(root, random.nextInt(100));
        }
        BTreePrinter.printNode(root);

        System.out.println("");
        System.out.print("Inorder: ");
        inorder(root);
        System.out.println("");
        System.out.print("Inorder No Rec: ");
        inorderItr(root);
        System.out.println("");
        System.out.print("Preorder: ");
        preorder(root);
        System.out.println("");
        System.out.print("Preorder No Rec: ");
        preorderNoRec(root);

        System.out.println("");
        System.out.print("Morris Inorder: ");
        morrisInorder(root);

        System.out.println("");
        System.out.print("Level Order: ");
        levelOrder(root);
        System.out.println("");
        System.out.print("Level Order Itr: ");
        levelOrderItr(root);

    }

    private static void preorder(Node node) {
        if (node != null) {
            System.out.print(node.data.toString() + " ");
            preorder(node.left);
            preorder(node.right);
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

    private static void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data.toString() + " ");
            inorder(node.right);
        }
    }

    private static void inorderItr(Node node) {
        Stack<Node> stack = new Stack<Node>();
        Node l = node;
        while (l != null) {
            stack.push(l);
            l = l.left;
        }
        while (!stack.empty()) {
            Node n = stack.pop();
            System.out.println(n.data + " ");
            n = n.right;
            while (n != null) {
                stack.push(n);
                n = n.left;
            }
        }
    }

    private static void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.data.toString() + " ");
        }
    }

    private static void morrisInorder(Node node) {
        Node current = node, pre = null;
        while (current != null) {
            if (current.left == null) {
                System.out.print(current.data.toString() + " ");
                current = current.right;
            } else {
                // Find inorder predecessor of the left tree
                pre = current.left;
                while (pre.right != null && pre.right != current) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = current;
                    current = current.left;
                } else {
                    pre.right = null;
                    System.out.print(current.data.toString() + " ");
                    current = current.right;
                }
            }
        }
    }

    private static void levelOrder(Node node) {
        List[] store = new List[10];
        levelOrderRecur(node, 0, store);
        System.out.print("[");
        for (List l : store) {
            if (l != null) {
                System.out.print(l.toString() + ", ");
            }
        }
        System.out.print("]");
    }

    private static void levelOrderRecur(Node node, int level, List[] store) {
        if (node == null) {
            return;
        }
        List list = store[level];
        if (list == null) {
            list = new ArrayList();
            store[level] = list;
        }
        list.add(node.data.toString());
        levelOrderRecur(node.left, level + 1, store);
        levelOrderRecur(node.right, level + 1, store);
    }

    private static void levelOrderItr(Node node) {        
        List<List> result = new ArrayList();
        LinkedList<AbstractMap.SimpleEntry<Node, Integer>> queue = new LinkedList<>();
        queue.offer(new AbstractMap.SimpleEntry(node, 0));
        while (!queue.isEmpty()) {
            AbstractMap.Entry<Node, Integer> entry = queue.poll();
            Node n = entry.getKey();
            int level = entry.getValue();
            if (result.size() < (level + 1)) {
                List<String> levelArray = new ArrayList();
                levelArray.add(n.data.toString());
                result.add(levelArray);
            } else {
                result.get(level).add(n.data.toString());
            }
            if (n.left != null) 
                queue.offer(new AbstractMap.SimpleEntry(n.left, level + 1));
            if (n.right != null) 
                queue.offer(new AbstractMap.SimpleEntry(n.right, level + 1));
         }
        System.out.println(result);
    }
}
