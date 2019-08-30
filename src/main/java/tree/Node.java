package tree;

public class Node<T extends Comparable<?>> {
    public T data;
    public Node<T> left;
    public Node<T> right;

    public Node(final T data) {
        this.data = data;
    }
}
