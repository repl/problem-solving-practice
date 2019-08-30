package tree.bst;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BSTOperations {

    /*
    https://www.codechef.com/problems/BSTOPS
    Write a program that processes the following queries on a Binary Search Tree:
        i x: Insert x in the BST
        d x: Delete x from the BST
    Input format
        Line 1 contains an integer Q, the number of queries
        The next Q lines are of the form i x or d x
    Output format
        For each query, print the position of x in the BST
        If the position of a node is p, the positions of its left and right children are 2*p and 2*p+1 respectively
        Position of the root node is 1
    Sample Input
        5
        i 1
        i 2
        i 0
        d 2
        i 3
    Sample Output
        1
        3
        2
        3
        3
     */
    public static void main(String[] args) {
        BST<String, Integer> st = new BST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            if ((st.size() > 1) && (st.floor(key) != st.floor2(key)))
                throw new RuntimeException("floor() function inconsistent");
            st.put(key, i);
        }

        for (String s : st.keys())
            StdOut.println(s + " " + st.rank(s));
    }
}
