/*
 * Patrice Moracchini
 * Assignment 2.2 Data File Write Program
 * Date: 04/05/2026
 * This program writes random integers and doubles to a data file that can be read by the ReadDataFile program.
 */

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class WriteDataFile {
    public static void main(String[] args) {
        String fileName = "moracchini_datafile.dat";  // Name of the data file to write to    

        int[] intArray = new int[5];  // Array to hold 5 random integers
        double[] doubleArray = new double[5];  // Array to hold 5 random doubles

        Random random = new Random();                           

        // Fill the integer array with random values
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = random.nextInt(100); // 0 to 99
        }

        // Fill the double array with random values
        for (int i = 0; i < doubleArray.length; i++) {
            doubleArray[i] = random.nextDouble() * 100; // 0.0 to 100.0
        }

        // Write data to the file in append mode
        try (DataOutputStream output =
                     new DataOutputStream(new FileOutputStream(fileName, true))) {

            // Write the integers to the file with a for-each loop
            for (int value : intArray) {
                output.writeInt(value);
            }

            // Write the doubles to the file with a for-each loop
            for (double value : doubleArray) {
                output.writeDouble(value);
            }

            System.out.println("Data written successfully to " + fileName);   // Confirmation message
            System.out.println();

            System.out.println("Integer array:");  // Print the integer array to the console
            for (int value : intArray) {
                System.out.print(value + " ");
            }

            System.out.println("\n");                                      

            System.out.println("Double array:");  // Print the double array to the console
            for (double value : doubleArray) {
                System.out.printf("%.2f ", value);
            }

            System.out.println();                                             

        } catch (IOException e) {  // Handle any I/O exceptions that may occur during file writing
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}