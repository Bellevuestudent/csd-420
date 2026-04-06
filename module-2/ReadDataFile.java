/*
 * Patrice Moracchini
 * Assignment 2.2 Data File Read Program
 * Date: 04/05/2026
 * This program reads integers and doubles from a data file created by the WriteDataFile program and displays them on the console.
 */

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadDataFile {
    public static void main(String[] args) {
        String fileName = "moracchini_datafile.dat";  // Name of the data file to read from

        // Read data from the file using a try-with-resources statement
        try (DataInputStream input =
                     new DataInputStream(new FileInputStream(fileName))) {

            int recordNumber = 1;  // Variable to keep track of the record number being read from the file

            // Loop to read records until the end of the file is reached
            while (true) {   
                System.out.println("Record " + recordNumber + ":");
                
                System.out.print("Integers: ");
                for (int i = 0; i < 5; i++) {  // Loop to read and print 5 integers from the file
                    System.out.print(input.readInt() + " ");  // Read and print each integer from the file
                }

                System.out.println();  // Print a newline for formatting

                System.out.print("Doubles: ");
                for (int i = 0; i < 5; i++) {  // Loop to read and print 5 doubles from the file
                    System.out.printf("%.2f ", input.readDouble());  // Read and print each double from the file formatted to 2 decimal places
                }

                System.out.println("\n");  // Print a newline for formatting
                recordNumber++;  // Increment the record number for the next set of data read from the file
            }

        // Catch block to handle the end-of-file exception
        } catch (EOFException e) {
            System.out.println("End of file reached.");
        } catch (IOException e) {  // Handle any I/O exceptions that may occur during file reading
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}