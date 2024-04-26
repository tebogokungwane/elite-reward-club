package com.erc.programming;

public class DaysOfTheWeek {

    public static void main(String[] args) {

        String weekDays = weekDays(51);
        System.out.println(weekDays);

    }

    private static String weekDays(int number){

        switch (number){
            case 0: return "Sunday";
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
        }
        return "Invalid option";
    }
}
