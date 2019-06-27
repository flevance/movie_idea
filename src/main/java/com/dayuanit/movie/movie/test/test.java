package com.dayuanit.movie.movie.test;

public class test {

    public static void main(String[] args) {
        int[] nums = {2,5,3,1,7,9,4};
        charu(nums);
        for (int num : nums){
            System.out.print(num + " ");
        }
    }

    private static void xuanze(int[] nums){
        for (int i = 0;i<nums.length;i++){
            for (int j = i + 1;j < nums.length;j++){
                if (nums[i] > nums[j]){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    private static void charu(int[] nums){
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1;j > 0;j--){
                if (nums[j] < nums[j - 1]){
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                }
            }
        }
    }
}
