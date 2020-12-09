package com.ohwow.oms.products;

import java.util.HashMap;
import java.util.Map;

/**
 * @author carlo
 *
 */
public class YeetCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		YeetCode yeetCode = new YeetCode();

		int[] array = { 2, 7, 11, 15 };
		int[] output = yeetCode.twoSumLinearTime(array, 17);

		System.out.println(output[0] + ", " + output[1]);

	}

	/**
	 * Solution for Two-Sum problem (O(n^2)).
	 * 
	 * Problem: Given an array of integers nums and an integer target, return
	 * indices of the two numbers such that they add up to target.
	 * 
	 * You may assume that each input would have exactly one solution, and you may
	 * not use the same element twice.
	 * 
	 * You can return the answer in any order.
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] nums, int target) {
		int[] output = new int[2];

		for (int i = 0; i <= nums.length - 1; i++) {
			int num1 = nums[i];
			for (int x = 0; x <= nums.length - 1; x++) {
				int num2 = nums[x];
				if (num1 != num2) {
					int sum = num1 + num2;
					if (sum == target) {
						output[0] = i;
						output[1] = x;
					}
				}
			}
		}

		return output;
	}

	/**
	 * Solution for Two-Sum problem (O(n)).
	 * 
	 * Problem: Given an array of integers nums and an integer target, return
	 * indices of the two numbers such that they add up to target.
	 * 
	 * You may assume that each input would have exactly one solution, and you may
	 * not use the same element twice.
	 * 
	 * You can return the answer in any order.
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSumLinearTime(int[] nums, int target) {
		int[] output = new int[2];
		Map<Integer, Integer> indexMap = new HashMap<>();

		for (int i = 0; i <= nums.length - 1; i++) {
			int number = nums[i];
			int complement = target - number;

			if (indexMap.containsKey(complement)) {
				output[0] = indexMap.get(complement);
				output[1] = i;
			} else {
				indexMap.put(number, i);
			}
		}

		return output;
	}

}
