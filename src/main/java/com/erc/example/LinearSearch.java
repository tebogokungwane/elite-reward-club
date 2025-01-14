package com.erc.example;

public class LinearSearch {

    public static void main(String[] args) {

        int numbers[] = {12,34,45,56,67,687,89,90,};

        System.out.println(linearSearch(numbers,67));
    }

    private static boolean linearSearch(int [] array, int target){
        for(int i = 0; i < array.length; i++){
            if(array[i] == target){
                return true;
            }
        }
        return false;
    }
}
