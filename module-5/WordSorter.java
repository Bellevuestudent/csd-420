/*
 * Patrice Moracchini
 * Assignment module 5.2
 * April 19, 2026
 * This program reads a file of words, counts how many times each word appears,
 * and then prints the unique words in both ascending and descending order.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class WordSorter {

    public static void main(String[] args) {

        // The Word file we want to read from
        String fileName = "collection_of_words.txt";

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
        
        // If the file can't be found, print an error message and exit the program
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find the file " + fileName);
            return;
        }

        // Print the word frequency map so we can see the counts
        System.out.println("Word counts (from the HashMap):");
        for (String word : wordCount.keySet()) {
            System.out.println("  " + word + " -> " + wordCount.get(word));
        }

        // Get all the unique words from the map's keys and put them in a list
        // Since a map can't have duplicate keys, every word here is already unique
        ArrayList<String> uniqueWords = new ArrayList<String>(wordCount.keySet());

        // Sort the list in ascending (A to Z) order
        Collections.sort(uniqueWords);

        // Print ascending order
        System.out.println("\nUnique words in ascending order:");
        for (int i = 0; i < uniqueWords.size(); i++) {
            System.out.println("  " + uniqueWords.get(i));
        }

        // Print descending order by looping backwards through the same list
        System.out.println("\nUnique words in descending order:");
        for (int i = uniqueWords.size() - 1; i >= 0; i--) {
            System.out.println("  " + uniqueWords.get(i));
        }
    }
}
