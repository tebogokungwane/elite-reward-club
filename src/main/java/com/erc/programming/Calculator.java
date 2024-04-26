package com.erc.programming;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first Number");
        int number1 = scanner.nextInt();

        System.out.println("Enter second Number");
        int number2 = scanner.nextInt();

        System.out.println("Chose operation : ");
        System.out.println("1 - ADD");
        System.out.println("2 - SUBTRACT");
        System.out.println("3 - DIVIDE");
        System.out.println("4 - MULTIPLY");
        System.out.println("Enter second Number");
        int option = scanner.nextInt();

        int total = total(option, number1, number2);

        System.out.println("total = "+ total);

    }

    private static int total(int choice ,int number1, int number2){

        if (choice == 1){
            return number1 + number2;
        } else if (choice == 2) {
            return number1 - number2;

        }else if (choice == 3) {
            return number1 / number2;

        }else if (choice == 4) {
            return number1 * number2;

        }else {
            return -1;
        }

    }

}
