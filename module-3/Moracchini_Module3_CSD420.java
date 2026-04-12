/*
 * Patrice Moracchini
 * CSD420
 * Module 3.2
 * Assignment: Remove Duplicates
 * April 12, 2026
 *
 * This program creates an ArrayList with 50 random numbers from 1 to 20.
 * It then uses a generic method to create a new ArrayList with duplicates removed.
 */

import java.util.ArrayList;

public class Moracchini_Module3_CSD420 {

    public static void main(String[] args) {

        // ArrayList<Integer> uses the wrapper class Integer, not primitive int
        ArrayList<Integer> originalList = new ArrayList<>();

        // Add 50 random numbers from 1 to 20 to the list
        for (int i = 0; i < 50; i++) {

            // This creates an int value
            // Autoboxing will convert the int into an Integer when adding it to the ArrayList
            originalList.add((int) (Math.random() * 20) + 1);
        }

        // Show the original list
        System.out.println("Original List:");
        System.out.println(originalList);

        // Call the generic method to remove duplicates
        ArrayList<Integer> newList = removeDuplicates(originalList);

        // Show the new list without duplicates
        System.out.println("\nList Without Duplicates:");
        System.out.println(newList);
    }

    // Generic method to remove duplicates from an ArrayList
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> newList = new ArrayList<>();
        // Loop through the original list and add elements to the new list if they are not already present
        for (E element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        // Return the new list with duplicates removed
        return newList;
    }
}