package tree.tries;

public class SearchContacts {
    /*
        Implements a contacts directory which supports lookup by string prefix.
        We will be using tries
                               s(5)
                  a(2)                       u(3)
            s(2)         n(2)        m(1)           s(2)
         h(2)        j(1)    t(1)    i(1)       a(1)   y(1)
     a(1)  i(1)      u(1)                       n(1)

     */
    static class Node {
        static final int NUM_CHARS = 26;
        int size;

        Node[] children = new Node[NUM_CHARS];

        public int getCharIndex(char c) {
            return c - 'a';
        }
        public Node getNode(char c) {
            return children[getCharIndex(c)];
        }
        public Node add(char c) {
            Node child = getNode(c);
            if (child == null) {
                size++;
                child = new Node();
                child.size = 1;
                children[getCharIndex(c)] = child;
            }
            return child;
        }
        public void add(String s) {
            if (s == null || s.length() == 0) return;
            add(s, 0);
        }
        public void add(String s, int index) {
            if (index == s.length()) return;
            Node child = add(s.charAt(index));
            child.add(s, index + 1);
        }
        public int lookup(String s) {
            if (s.length() == 0) {
                return size;
            }
            Node n = getNode(s.charAt(0));
            if (n == null) {
                return 0;
            }
            return n.lookup(s.substring(1, s.length()));
        }
    }
    
    public static void main(String[] args) {
        Node root = new Node();
        root.add("sasha");
        root.add("sashi");
        root.add("sanju");
        root.add("sant");
        root.add("sumi");
        root.add("susan");
        root.add("susy");
        System.out.println(root.lookup("sas"));
    }
}
