/*
 * Patrice Moracchini
 * 05/03/2026
 * Assignment 8.2
 * This program shows the use of three threads to output random characters to the console.
 * It generates random lowercase letters, digits, and special characters and outputs them
 * to the console using three concurrent threads.
 * it Uses the Runnable interface pattern (implements Runnable)
 */
import java.util.Random;

public class PatriceThreeThreads {

    public static final int    MIN_COUNT = 10000;
    public static final char[] LETTERS   = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public static final char[] DIGITS    = "0123456789".toCharArray();
    public static final char[] SPECIALS  = {'!', '@', '#', '$', '%', '&', '*'};

    private int letterCount  = 0;
    private int digitCount   = 0;
    private int specialCount = 0;

    /*
     * synchronized so only one thread prints at a time
     * Each character is displayed as it is generated
     */
    public synchronized void printChar(char c) {

        System.out.print(c);
    }

    public synchronized void incrementLetterCount()  { letterCount++;  }
    public synchronized void incrementDigitCount()   { digitCount++;   }
    public synchronized void incrementSpecialCount() { specialCount++; }

    // getters for testing purposes
    public int getLetterCount()  { return letterCount;  }
    public int getDigitCount()   { return digitCount;   }
    public int getSpecialCount() { return specialCount; }

    public void run() throws InterruptedException {

        LetterRunnable  letterRunnable  = new LetterRunnable(this);
        DigitRunnable   digitRunnable   = new DigitRunnable(this);
        SpecialRunnable specialRunnable = new SpecialRunnable(this);

        Thread letterThread  = new Thread(letterRunnable);
        Thread digitThread   = new Thread(digitRunnable);
        Thread specialThread = new Thread(specialRunnable);

        letterThread.setName("LetterThread");
        digitThread.setName("DigitThread");
        specialThread.setName("SpecialThread");

        letterThread.start();
        digitThread.start();
        specialThread.start();

        /*
         * join() - waits for all three threads to finish before continuing
         */
        letterThread.join();
        digitThread.join();
        specialThread.join();

        System.out.println();
        System.out.println("\nAll threads complete.");
        System.out.println("Letters:  " + letterCount);
        System.out.println("Digits:   " + digitCount);
        System.out.println("Specials: " + specialCount);
    }

    public static void main(String[] args) throws InterruptedException {

        PatriceThreeThreads app = new PatriceThreeThreads();
        app.run();
    }
}

// -----------------------------------------------------------------
// Thread 1 - outputs random lowercase letters  a-z
// -----------------------------------------------------------------
class LetterRunnable implements Runnable {

    private PatriceThreeThreads app;

    public LetterRunnable(PatriceThreeThreads app) {

        this.app = app;
    }

    @Override
    public void run() {

        Random random = new Random();

        for (int i = 0; i < PatriceThreeThreads.MIN_COUNT; i++) {

            char c = PatriceThreeThreads.LETTERS[random.nextInt(PatriceThreeThreads.LETTERS.length)];
            app.printChar(c);
            app.incrementLetterCount();

            try {

                Thread.sleep(0);
            }
            catch (InterruptedException exception) {

                exception.printStackTrace();
            }
        }

        System.out.println("\n\t\t" + Thread.currentThread().getName() + " is complete.");
    }
}

// -----------------------------------------------------------------
// Thread 2 - outputs random digit characters  0-9
// -----------------------------------------------------------------
class DigitRunnable implements Runnable {

    private PatriceThreeThreads app;

    public DigitRunnable(PatriceThreeThreads app) {

        this.app = app;
    }

    @Override
    public void run() {

        Random random = new Random();

        for (int i = 0; i < PatriceThreeThreads.MIN_COUNT; i++) {

            char c = PatriceThreeThreads.DIGITS[random.nextInt(PatriceThreeThreads.DIGITS.length)];
            app.printChar(c);
            app.incrementDigitCount();

            try {

                Thread.sleep(0);
            }
            catch (InterruptedException exception) {

                exception.printStackTrace();
            }
        }

        System.out.println("\n\t\t" + Thread.currentThread().getName() + " is complete.");
    }
}

// -----------------------------------------------------------------
// Thread 3 - outputs random special characters  ! @ # $ % & *
// -----------------------------------------------------------------
class SpecialRunnable implements Runnable {

    private PatriceThreeThreads app;

    public SpecialRunnable(PatriceThreeThreads app) {

        this.app = app;
    }

    @Override
    public void run() {

        Random random = new Random();

        for (int i = 0; i < PatriceThreeThreads.MIN_COUNT; i++) {

            char c = PatriceThreeThreads.SPECIALS[random.nextInt(PatriceThreeThreads.SPECIALS.length)];
            app.printChar(c);
            app.incrementSpecialCount();

            try {

                Thread.sleep(0);
            }
            catch (InterruptedException exception) {

                exception.printStackTrace();
            }
        }

        System.out.println("\n\t\t" + Thread.currentThread().getName() + " is complete.");
    }
}
