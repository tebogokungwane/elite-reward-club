package com.erc.example;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {

        int num[] ={54,76,98,43,76,12,34,56,78,90};

        System.out.println("Unsorted Array "+ Arrays.toString(num));
        System.out.println();
         bubbleSort(num);
        System.out.println("Sorted Array "+ Arrays.toString(num));


    }

    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                }
            }
        }
    }
}
