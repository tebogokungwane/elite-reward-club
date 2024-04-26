package com.erc.programming;

import java.util.List;

public class FunctionalDemo {

    public static void main(String[] args) {

        List<String> animals = List.of("Dog","Elephant","Cat","Bat","Snake");

        someAnimals(animals);
    }

    private static void someAnimals(List<String> animalList){
        animalList.stream()
                .filter(animal -> animal.endsWith("at"))
                .forEach( animal-> System.out.println("Animal(s) -> "+ animal));
    }
}
