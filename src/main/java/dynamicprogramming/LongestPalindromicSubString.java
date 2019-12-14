package dynamicprogramming;

public class LongestPalindromicSubString {
    /*
      https://leetcode.com/problems/longest-palindromic-substring/
      Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
      Example 1:
    
      Input: "babad"
      Output: "bab"
      Note: "aba" is also a valid answer.
    
      Example 2:
    
      Input: "cbbd"
      Output: "bb"    
    */
    public static void main(String[] args) {
        LongestPalindromicSubString instance = new LongestPalindromicSubString();
        System.out.println(instance.longestPalindrome("babad"));
        System.out.println(instance.longestPalindrome("cbbd"));
    }

    public String longestPalindrome(String s) {
        return null;
    }
}
