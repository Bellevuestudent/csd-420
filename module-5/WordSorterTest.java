/*
 * Patrice Moracchini
 * Assignment module 5.2
 * April 19, 2026
 * This program tests the WordSorter logic by verifying file reading, word counting,
 * and sorting in both ascending and descending order.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class WordSorterTest {

    // Keep track of how many tests pass or fail
    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {

        System.out.println("Running tests...\n");

        testFileCanBeOpened();
        testMapIsNotEmpty();
        testMapCountsAreCorrect();
        testMapKeyIsUnique();
        testExpectedWordCount();
        testAscendingOrder();
        testDescendingOrder();
        testFirstWordAscending();
        testLastWordAscending();

        // Print final results
        System.out.println("\n------------------------------");
        System.out.println(passed + " passed, " + failed + " failed");
    }

    // Helper method to print pass or fail
    static void check(boolean condition, String testName) {
        if (condition) {
            System.out.println("  PASS: " + testName);
            passed++;
        } else {
            System.out.println("  FAIL: " + testName);
            failed++;
        }
    }

    // Helper method to build the word count map from the file (same logic as WordSorter)
    static HashMap<String, Integer> buildWordMap(String fileName) {

        // A HashMap to store each word and how many times it appears
        // Key   = the word        (e.g. "honda")
        // Value = the word count  (e.g. 4)
        HashMap<String, Integer> wordCount = new HashMap<String, Integer>();

        // Try to open the file and read through it word by word, building the word count map
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            // Go through every word in the file one by one
            while (scanner.hasNext()) {
                String word = scanner.next();

                // If the word is already in the map, increase its count by 1
                // If the word is NOT in the map yet, add it with a count of 1
                if (wordCount.containsKey(word)) {
                    wordCount.put(word, wordCount.get(word) + 1);
                } else {
                    wordCount.put(word, 1);
                }
            }

            scanner.close();

        // If the file can't be found, print an error message and return an empty map
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find file " + fileName);
        }

        return wordCount;
    }

    // Test 1 - Make sure the file exists and can be opened
    static void testFileCanBeOpened() {
        File file = new File("collection_of_words.txt");
        check(file.exists(), "File 'collection_of_words.txt' exists");
    }

    // Test 2 - Make sure the map has entries in it after reading the file
    static void testMapIsNotEmpty() {
        HashMap<String, Integer> wordCount = buildWordMap("collection_of_words.txt");
        check(wordCount.size() > 0, "Map is not empty after reading file");
    }

    // Test 3 - Check that specific words have the correct counts in the map
    static void testMapCountsAreCorrect() {
        HashMap<String, Integer> wordCount = buildWordMap("collection_of_words.txt");

        // "honda" appears on lines 1, 2, 4 and 5 = 4 times
        check(wordCount.get("honda") == 4, "Map count for 'honda' is 4");

        // "toyota" appears on lines 1, 3, 4 and 5 = 4 times
        check(wordCount.get("toyota") == 4, "Map count for 'toyota' is 4");

        // "porsche" appears only once
        check(wordCount.get("porsche") == 1, "Map count for 'porsche' is 1");
    }

    // Test 4 - A map can't have duplicate keys, so every key should be a unique word
    static void testMapKeyIsUnique() {
        HashMap<String, Integer> wordCount = buildWordMap("collection_of_words.txt");

        // Put the map keys into a list and check for duplicates manually
        ArrayList<String> keys = new ArrayList<String>(wordCount.keySet());
        boolean hasDuplicate = false;
        // Check every key against every other key to see if there are any duplicates
        for (int i = 0; i < keys.size(); i++) {
            for (int j = i + 1; j < keys.size(); j++) {
                if (keys.get(i).equals(keys.get(j))) {
                    hasDuplicate = true;
                    break;
                }
            }
        }

        check(!hasDuplicate, "Map keys have no duplicates");
    }

    // Test 5 - The file has 16 unique words, the map should have 16 keys
    static void testExpectedWordCount() {
        HashMap<String, Integer> wordCount = buildWordMap("collection_of_words.txt");
        check(wordCount.size() == 16, "Map has 16 unique words (got " + wordCount.size() + ")");
    }

    // Test 6 - After sorting the map keys, they should be in A to Z order
    static void testAscendingOrder() {
        HashMap<String, Integer> wordCount = buildWordMap("collection_of_words.txt");
        ArrayList<String> words = new ArrayList<String>(wordCount.keySet());
        Collections.sort(words);
        // Check that each word is less than or equal to the next word in the list
        boolean isSorted = true;
        for (int i = 0; i < words.size() - 1; i++) {
            if (words.get(i).compareTo(words.get(i + 1)) > 0) {
                isSorted = false;
                break;
            }
        }

        check(isSorted, "Map keys sorted in ascending (A to Z) order");
    }

    // Test 7 - Looping backwards through the sorted list gives Z to A order
    static void testDescendingOrder() {
        HashMap<String, Integer> wordCount = buildWordMap("collection_of_words.txt");
        ArrayList<String> words = new ArrayList<String>(wordCount.keySet());
        Collections.sort(words);

        // Build the descending list by going backwards
        ArrayList<String> descending = new ArrayList<String>();
        for (int i = words.size() - 1; i >= 0; i--) {
            descending.add(words.get(i));
        }

        boolean isSorted = true;
        for (int i = 0; i < descending.size() - 1; i++) {
            if (descending.get(i).compareTo(descending.get(i + 1)) < 0) {
                isSorted = false;
                break;
            }
        }

        check(isSorted, "Map keys sorted in descending (Z to A) order");
    }

    // Test 8 - After sorting A to Z, the first word should be "audi"
    static void testFirstWordAscending() {
        HashMap<String, Integer> wordCount = buildWordMap("collection_of_words.txt");
        ArrayList<String> words = new ArrayList<String>(wordCount.keySet());
        Collections.sort(words);
        check(words.get(0).equals("audi"), "First word in ascending order is 'audi'");
    }

    // Test 9 - After sorting A to Z, the last word should be "volvo"
    static void testLastWordAscending() {
        HashMap<String, Integer> wordCount = buildWordMap("collection_of_words.txt");
        ArrayList<String> words = new ArrayList<String>(wordCount.keySet());
        Collections.sort(words);
        check(words.get(words.size() - 1).equals("volvo"), "Last word in ascending order is 'volvo'");
    }
}
