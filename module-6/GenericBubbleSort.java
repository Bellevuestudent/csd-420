import java.util.Comparator;
/**
 * Patrice Moracchini
 * Assignment Module 6.2
 * Generic Bubble Sort
 * This program sorts arrays using two methods:
 *   1. Using Comparable (the object compares itself)
 *   2. Using Comparator (we pass in a custom comparison rule)
 * Only Comparator needs to be imported — Comparable is built into java.lang.
 * We test both methods with integers and strings, 
 * and also show how to sort strings by length using a custom Comparator.
 */

public class GenericBubbleSort {

    
    // METHOD 1: Bubble sort using Comparable
    // <E extends Comparable<E>> means: E must know how to compare itself
    public static <E extends Comparable<E>> void bubbleSortComparable(E[] array) {

        // Outer loop: how many passes we make
        for (int i = 0; i < array.length - 1; i++) {

            // Inner loop: compare each pair of neighbors
            for (int j = 0; j < array.length - 1 - i; j++) {

                // compareTo returns positive if array[j] is greater than array[j+1]
                // That means they are in the wrong order -> swap them
                if (array[j].compareTo(array[j + 1]) > 0) {

                    // Swap using a temp variable
                    E temp         = array[j];
                    array[j]       = array[j + 1];
                    array[j + 1]   = temp;
                }
            }
        }
    }

    // METHOD 2: Bubble sort using Comparator
    // We pass in a Comparator object that defines the sort rule
    // This lets us sort in any custom order we want
    public static <E> void bubbleSortComparator(E[] array, Comparator<E> comparator) {

        // Outer loop: how many passes we make
        for (int i = 0; i < array.length - 1; i++) {

            // Inner loop: compare each pair of neighbors
            for (int j = 0; j < array.length - 1 - i; j++) {

                // comparator.compare returns positive if array[j] should come after array[j+1]
                // That means they are in the wrong order -> swap them
                if (comparator.compare(array[j], array[j + 1]) > 0) {

                    // Swap using a temp variable (same logic as Method 1)
                    E temp         = array[j];
                    array[j]       = array[j + 1];
                    array[j + 1]   = temp;
                }
            }
        }
    }

    // HELPER: Prints any array to the screen
    public static <E> void printArray(E[] array) {

        System.out.print("[ ");
        for (E element : array) {
            System.out.print(element + " ");
        }
        System.out.println("]");
    }

    // MAIN: Test code
    public static void main(String[] args) {
        // Test 1: Sort integers using Comparable, which is the natural order for numbers,
        // using integer wrapper class (not int primitive)
        System.out.println("=== TEST 1: Sort Integers with Comparable ===");
        Integer[] numbers = {5, 3, 8, 1, 9, 2, 7, 4, 6};    
        System.out.print("Before: "); 
        printArray(numbers);             
        bubbleSortComparable(numbers);    
        System.out.print("After:  ");  
        printArray(numbers);
        
        System.out.println();

        // Test 2: Sort strings alphabetically using Comparable, which is the natural order for strings.
        System.out.println("=== TEST 2: Sort Strings alphabetically with Comparable ===");
        String[] words = {"Banana", "Apple", "Mango", "Cherry", "Date"}; 
        System.out.print("Before: ");  
        printArray(words);             
        bubbleSortComparable(words);  
        System.out.print("After:  ");
        printArray(words);

        System.out.println();

        // Test 3: Sort strings by length using a Comparator, which is a custom sorting rule that we define on the fly.
        System.out.println("=== TEST 3: Sort Strings by Length with Comparator ===");
        String[] words2 = {"Banana", "Hi", "Minion", "Ok", "Fluffy", "A"};
        System.out.print("Before: ");
        printArray(words2);

        // We create a Comparator that compares strings by their length (shorter strings come first)
        Comparator<String> byLength = (String a, String b) -> a.length() - b.length();

        bubbleSortComparator(words2, byLength);
        System.out.print("After:  ");
        printArray(words2);

        System.out.println();

        // Test 4: Already sorted array, which should remain unchanged, 
        // using integer wrapper class (not int primitive)
        System.out.println("=== TEST 4: Already sorted array ===");
        Integer[] sorted = {1, 2, 3, 4, 5};
        System.out.print("Before: ");
        printArray(sorted);
        bubbleSortComparable(sorted);
        System.out.print("After:  ");
        printArray(sorted);

        System.out.println();

        // Test 5: Reverse sorted array, which is the worst case for bubble sort, 
        // using integer wrapper class (not int primitive)
        System.out.println("=== TEST 5: Reverse sorted array ===");
        Integer[] reversed = {9, 8, 7, 6, 5, 4, 3, 2, 1};    
        System.out.print("Before: ");                       
        printArray(reversed);                               
        bubbleSortComparable(reversed);
        System.out.print("After:  ");
        printArray(reversed);
    }
}
