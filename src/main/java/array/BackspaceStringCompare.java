package array;

import java.util.Arrays;

public class BackspaceStringCompare {
    public static void main(String[] args) {
        String s = "ab#c", t = "ad#c";
        System.out.println(compute(s, t));

        System.out.println(compute("ab#d", "ad#b"));
    }

    public static boolean compute(String s, String t) {
        return (getFinal(s).equals(getFinal(t)));
    }

    public static String getFinal(String s) {
        int i = 0, x = -1;
        char[] newS = new char[s.length()];
        Arrays.fill(newS, ' ');
        while (i < s.length()) {
            if (s.charAt(i) == '#') {
                if (x >= 0) {
                    newS[x--] = ' ';
                }
            } else {
                newS[++x] = s.charAt(i);
            }
            i++;
        }
        return new String(newS);
    }
}
