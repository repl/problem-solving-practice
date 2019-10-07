package array;

import java.util.Arrays;

public class SearchInRotatedSortedArray {
    /*
      LeetCode 33. Search in Rotated Sorted Array
      Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

      (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

      You are given a target value to search. If found in the array return its index, otherwise return -1.

      You may assume no duplicate exists in the array.

      Your algorithm's runtime complexity must be in the order of O(log n).

      Example 1:

      Input: nums = [4,5,6,7,0,1,2], target = 0
      Output: 4

      Example 2:

      Input: nums = [4,5,6,7,0,1,2], target = 3
      Output: -1    
     */

    public static void main(String[] args) {
        SearchInRotatedSortedArray instance = new SearchInRotatedSortedArray();
        int[] input = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(instance.search(input, 0));
        System.out.println(instance.search(input, 3));
        System.out.println(instance.search(input, 4));
        System.out.println(instance.search(input, 2));
        System.out.println(instance.search(input, 7));
        int[] input2 = {};
        System.out.println(instance.search(input2, 7));
        int[] input3 = {1};
        System.out.println(instance.search(input3, 2));
        int[] input4 = {4,5,6,7,8,9,1,2,3};
        System.out.println(instance.search(input4, 1));

        System.out.println(instance.search2(input4, 0, 8, 1));
    }

    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int l = 0, h = nums.length - 1;
        int mid = 0;
        while (l <= h) {
            mid = l + (h - l) / 2;
            if (nums[l] == target) {
                return l;
            } else if (nums[h] == target) {
                return h;
            }
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > nums[h]) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        System.out.println("mid: " + mid + ", value: " + nums[mid]);
        if (target < nums[nums.length -1]) {
            l = mid + 1;
            h = nums.length - 1;
        } else {
            l = 0;
            h = mid - 1;
        }
        while (l<=h) {
            mid = l + (h - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                h = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    //Alternate solution
    public int search2(int[] nums, int left, int right, int target) {
        if (left == right) {
            return (nums[left] == target)? left: -1;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] == target)
            return mid;

        //pivot is on the right of the mid element
        if (nums[left] <= nums[mid]) {
            if (target >= nums[left] && target <= nums[mid]) {
                return search2(nums, left, mid - 1, target);
            } else {
                return search2(nums, mid + 1, right, target);
            }
        } else {
            //pivot is on the left of the mid element
            if (target >= nums[mid] && target <= nums[right]) {
                return search2(nums, mid + 1, right, target);
            } else {
                return search2(nums, left, mid - 1, target);                
            }
        }        
    }
}
