package array;

public class MinimumTimesA {
    public static void main(String[] args) {
        String a = "abcd", b = "cdabcdab";
        System.out.println(minimumTimes(a, b));

        System.out.println(minimumTimes("ab", "cab"));        
    }

    private static int minimumTimes(String a, String b) {
        int ret = 0;
        StringBuffer buff = new StringBuffer();
        buff.append(a);
        while (buff.length() < b.length()) {
            ret += 1;
            buff.append(a);
        }
        if (buff.toString().contains(b)) {
            return ret;
        } else {
            ret = ret + 1;
            buff.append(a);
            if (buff.toString().contains(b)) {
                return ret;
            } else {
                return -1;
            }
        }
    }
}
