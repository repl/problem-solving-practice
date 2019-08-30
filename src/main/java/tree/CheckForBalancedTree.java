package tree;

import java.util.Scanner;
import java.util.*;
import java.lang.*;
import java.io.*;

public class CheckForBalancedTree {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);        
        int t = sc.nextInt();
        while (t > 0) {
            HashMap<Integer, Node> m = new HashMap<Integer, Node>();
            int n = sc.nextInt();
            Node root = null;
            while (n > 0) {

                int n1 = sc.nextInt();
                int n2 = sc.nextInt();
                char lr = sc.next().charAt(0);
                
                //  cout << n1 << " " << n2 << " " << (char)lr << endl;
                Node parent = m.get(n1);
                if (parent == null) {
                    parent = new Node(n1);
                    m.put(n1, parent);
                    if (root == null)
                        root = parent;
                }
                Node child = new Node(n2);
                if (lr == 'L')
                    parent.left = child;
                else
                    parent.right = child;
                m.put(n2, child);
                n--;
            }

            GFG g = new GFG();            
            
            //boolean res = g.isHeap(root);
            if (g.isBalanced(root) == true)
                System.out.println(1);
            else
                System.out.println(0);
            //CLN.inorder(root);
            t--;
            // System.out.println();
        }
    }
}

class GFG {
    /* This function should return tree if passed  tree
     is balanced, else false.  Time complexity should
     be O(n) where n is number of nodes in tree */
    boolean isBalanced(Node root) {
        if (root == null) return true;
        int[] rootData = getData(root);
        if (rootData[1] == 1) return true;
        return false;
    }

    private int[] getData(Node tree) {
        int[] retVal = new int[2];
        if (tree == null) {
            retVal[0] = -1;
            retVal[1] = 1;
        } else if (tree.left == null && tree.right == null) {
            retVal[0] = 0;
            retVal[1] = 1;
        } else {
            int leftTreeHeight = -1;
            int leftTreeBalanced = 1;
            if (tree.left != null) {
                int[] leftTreeData = getData(tree.left);
                leftTreeHeight = leftTreeData[0];
                leftTreeBalanced = leftTreeData[1];
            }
            System.out.println("left tree height at node " + tree.data + " is " +  leftTreeHeight);
            System.out.println("left tree balance at node " + tree.data + " is " +  leftTreeBalanced);
            int rightTreeHeight = -1;
            int rightTreeBalanced = 1;
            if (tree.right != null) {
                int[] rightTreeData = getData(tree.right);
                rightTreeHeight = rightTreeData[0];
                rightTreeBalanced = rightTreeData[1];
            }
            System.out.println("right tree height at node " + tree.data + " is " +  rightTreeHeight);
            System.out.println("right tree balance at node " + tree.data + " is " +  rightTreeBalanced);
            retVal[0] = 1 + ((leftTreeHeight > rightTreeHeight)? leftTreeHeight : rightTreeHeight);
            System.out.println("tree height at node " + tree.data + " is " +  retVal[0]);
            if (leftTreeBalanced == 0 || rightTreeBalanced == 0) {
                retVal[1] = 0;
            } else {
                retVal[1] = (leftTreeHeight == rightTreeHeight
                        || Math.abs(leftTreeHeight - rightTreeHeight) == 1) ? 1 : 0;
            }
            System.out.println("tree balance status at node " + tree.data + " is " +  retVal[1]);
        }
        return retVal;
    }
}
