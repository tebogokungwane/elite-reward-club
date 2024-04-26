package com.erc.programming;

import java.util.List;

public class FP01Function {

    public static void main(String[] args) {

        String name = "this is a lot of string";

        int i = name.indexOf('4');// expected is a lot
        System.out.println(i);


    }

    private static void printAllNumbersInListStructure(List<Number> numbers){
        System.out.println(numbers);
    }

}
