package com.yurii.pavlenko.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Application demonstrating collection filtering via List.removeIf using an explicit anonymous inner class.
 */
public class ZooManagerApp {

    public static void main(String[] args) {
        List<String> animals = new ArrayList<>(Arrays.asList("dog", "cow", "horse", "sheep"));

        System.out.println("=== Digital Zoo Asset Management ===");
        System.out.println("Original Animal List: " + animals);
        System.out.println("Filtering underperforming assets...");

        // Filtering the list using an anonymous inner class that implements the Predicate interface
        animals.removeIf(new Predicate<String>() {
            @Override
            public boolean test(String name) {
                return name.length() < 4;
            }
        });

        System.out.println("Cleaned Zoo Exhibit: " + animals);
    }
}