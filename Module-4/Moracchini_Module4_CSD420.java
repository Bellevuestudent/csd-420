/*
 * Patrice Moracchini
 * CSD 420
 * Module 4.2
 * Assignment: LinkedList Traversal Timing
 * April 12, 2026
 *
 * This program stores integers in a LinkedList and compares the time
 * needed to traverse the list in two ways:
 * 1. Using an iterator
 * 2. Using the get(index) method
 *
 * It tests two list sizes:
 * - 50,000 integers
 * - 500,000 integers
 *
 * Results:
 * The iterator is much faster for a LinkedList because it moves from
 * node to node in sequence. A LinkedList is designed for this kind of traversal.
 * 
 * The get(index) method is much slower because LinkedList does not jump
 * directly to an index like an ArrayList. Each time get(index) is called,
 * Java must move through the list to find that position.
 *
 * With 50,000 values, iterator traversal should already be faster.
 * With 500,000 values, the difference becomes much larger because
 * get(index) keeps repeating a search for every position.
 *
 * So:
 * - Iterator traversal grows but the time remains reasonable even for 500,000 elements (a few milliseconds).
 * - get(index) becomes very inefficient on large LinkedLists. (almost 2 min for 500,000 elements)
 *
 * Test code is included to make sure:
 * - The list is created with the correct size
 * - Iterator traversal visits every element
 * - get(index) traversal visits every element
 *
 */

import java.util.Iterator;
import java.util.LinkedList;

public class Moracchini_Module4_CSD420 {

    public static void main(String[] args) {
        System.out.println("--------------------------------");
        System.out.println("LinkedList Traversal Timing Test");
        System.out.println("--------------------------------");
        // Run tests for both sizes
        runTest(50000);
        System.out.println();
        runTest(500000);
    }
    // Method to run the test for a given size of LinkedList
    public static void runTest(int size) {
        // Create a LinkedList to hold integers
        LinkedList<Integer> numbers = new LinkedList<>();

        // Fill the LinkedList with integers from 0 up to size - 1
        for (int i = 0; i < size; i++) {
            numbers.add(i);   // autoboxing: int becomes Integer
        }

        // Simple test to ensure the list was created with the correct size
        if (numbers.size() != size) {
            System.out.println("Test failed: list size is incorrect.");
            return;
        }
        // Count elements using both methods to ensure they visit all elements
        long iteratorCount = countWithIterator(numbers);
        long getIndexCount = countWithGetIndex(numbers);
        // Verify that both methods counted the same number of elements
        if (iteratorCount != size) {
            System.out.println("Test failed: iterator did not visit all elements.");
            return;
        }
        // Verify that get(index) counted the same number of elements
        if (getIndexCount != size) {
            System.out.println("Test failed: get(index) did not visit all elements.");
            return;
        }
        // Measure the time taken for both traversal methods
        long iteratorTime = timeIteratorTraversal(numbers);
        long getIndexTime = timeGetIndexTraversal(numbers);
        // Print results
        System.out.println("List size: " + size);
        System.out.println("Iterator traversal time: " + iteratorTime + " ms");
        System.out.println("get(index) traversal time: " + getIndexTime + " ms");
        System.out.println("Difference: " + (getIndexTime - iteratorTime) + " ms");
    }

    // Counts elements using an iterator
    public static long countWithIterator(LinkedList<Integer> list) {
        long count = 0;
        Iterator<Integer> iterator = list.iterator();
        
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }

        return count;
    }

    // Counts elements using get(index)
    public static long countWithGetIndex(LinkedList<Integer> list) {
        long count = 0;

        for (int i = 0; i < list.size(); i++) {
            list.get(i);
            count++;
        }

        return count;
    }

    // Measures traversal time using an iterator
    public static long timeIteratorTraversal(LinkedList<Integer> list) {
        // Start timing using System.nanoTime() for better precision
        long startTime = System.nanoTime();

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        // End timing and convert to milliseconds
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    // Measures traversal time using get(index)
    public static long timeGetIndexTraversal(LinkedList<Integer> list) {
        long startTime = System.nanoTime();

        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }
}