package com.erc.example;

public class BinarySearch {

    public static void main(String[] args) {

    }

    private static boolean binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                left = mid + 1;

            }else {
                right = mid - 1;
            }
        }
        return false;
    }
}
