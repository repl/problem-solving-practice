package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BTreePrinter {

    public static <T extends Comparable<?>> void printNode(final Node<T> root) {
        final int maxLevel = maxLevel(root);

        BTreePrinter.printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(final List<Node<T>> nodes,
                                                                    final int level,
                                                                    final int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        final int floor = maxLevel - level;
        final int endgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));
        final int firstSpaces = (int) Math.pow(2, floor) - 1;
        final int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

        printWhitespaces(firstSpaces);

        final List<Node<T>> newNodes = new ArrayList<Node<T>>();
        for (final Node<T> node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println();

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println();
        }

        BTreePrinter.printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(final int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(final Node<T> node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(final List<T> list) {
        for (final Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}
