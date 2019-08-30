package array;

public class StringCompression {

    public static void main(String[] args) {
        String input = "cdjkvhaaaajkjyyyywqwwwq";
        System.out.println(compress(input));
    }

    public static String compress(String input) {
        if (input.length() == 0)
            return input;
        StringBuffer strBuff = new StringBuffer();
        int numSame = 1;
        char prev = input.charAt(0);
        for (int i = 1; i < input.length(); i++) {
            char curr = input.charAt(i);
            if (curr == prev) {
                numSame++;
            } else {
                strBuff.append(prev);
                if (numSame > 1) {
                    strBuff.append(numSame);
                }
                numSame = 1;
                prev = curr;
            }
        }
        strBuff.append(prev);
        if (numSame > 1) {
            strBuff.append(numSame);
        }
        return strBuff.toString();
    }
}
