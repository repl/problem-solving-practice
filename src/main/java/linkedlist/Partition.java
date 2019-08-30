package linkedlist;

public class Partition {

    public static void main(String[] args) {
        Node n10 = new Node(2);
        Node n9 = new Node(6, n10);
        Node n8 = new Node(10, n9);
        Node n7 = new Node(4, n8);
        Node n6 = new Node(8, n7);
        Node n5 = new Node(2, n6);
        Node n4 = new Node(6, n5);
        Node n3 = new Node(1, n4);
        Node n2 = new Node(4, n3);
        Node n1 = new Node(9, n2);
        int partitionkey = 5;
        System.out.println(listToString(partition(n1, partitionkey)));
    }

    public static String listToString(Node n) {
        StringBuffer buff = new StringBuffer();
        Node temp = n;
        while (temp != null) {
            buff.append(temp.data + ",");
            temp = temp.next;
        }
        return buff.toString();
    }

    public static Node partition(Node n, int key) {
        if (n == null || n.next == null)
            return null;
        Node current = n;
        Node partition = n;
        while (current != null) {
            if (current.data < key) {
                int temp = current.data;
                current.data = partition.data;
                partition.data = temp;
                partition = partition.next;
            }
            current = current.next;
        }
        return n;
    }
}
