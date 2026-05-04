/*
 * Patrice Moracchini
 * Bellevue University
 *
 * Test class for PatriceThreeThreads
 * Verifies that all three Runnable classes and the main class function correctly
 */
import java.util.Random;

public class PatriceThreeThreadsTest {

    private static int passed = 0;
    private static int failed = 0;

    private static void assertTrue(String testName, boolean condition) {

        if (condition) {
            System.out.println("PASS: " + testName);
            passed++;
        }
        else {
            System.out.println("FAIL: " + testName);
            failed++;
        }
    }

    private static boolean charInArray(char c, char[] array) {

        for (int i = 0; i < array.length; i++) {

            if (array[i] == c) {
                return true;
            }
        }
        return false;
    }

    // -----------------------------------------------------------------
    // Test: counters begin at zero before any thread runs
    // -----------------------------------------------------------------
    public static void testInitialCountersAreZero() {

        PatriceThreeThreads app = new PatriceThreeThreads();

        assertTrue("letterCount starts at 0",  app.getLetterCount()  == 0);
        assertTrue("digitCount starts at 0",   app.getDigitCount()   == 0);
        assertTrue("specialCount starts at 0", app.getSpecialCount() == 0);
    }

    // -----------------------------------------------------------------
    // Test: LETTERS array contains only lowercase a-z
    // -----------------------------------------------------------------
    public static void testLettersArrayContentsAreValid() {

        boolean ok = true;

        for (int i = 0; i < PatriceThreeThreads.LETTERS.length; i++) {

            char c = PatriceThreeThreads.LETTERS[i];

            if (c < 'a' || c > 'z') {
                ok = false;
            }
        }

        assertTrue("LETTERS array contains only a-z", ok);
        assertTrue("LETTERS array has 26 characters", PatriceThreeThreads.LETTERS.length == 26);
    }

    // -----------------------------------------------------------------
    // Test: DIGITS array contains only 0-9
    // -----------------------------------------------------------------
    public static void testDigitsArrayContentsAreValid() {

        boolean ok = true;

        for (int i = 0; i < PatriceThreeThreads.DIGITS.length; i++) {

            char c = PatriceThreeThreads.DIGITS[i];

            if (c < '0' || c > '9') {
                ok = false;
            }
        }

        assertTrue("DIGITS array contains only 0-9", ok);
        assertTrue("DIGITS array has 10 characters", PatriceThreeThreads.DIGITS.length == 10);
    }

    // -----------------------------------------------------------------
    // Test: SPECIALS array contains only expected characters
    // -----------------------------------------------------------------
    public static void testSpecialsArrayContentsAreValid() {

        char[] expected = {'!', '@', '#', '$', '%', '&', '*'};
        boolean ok = true;

        for (int i = 0; i < PatriceThreeThreads.SPECIALS.length; i++) {

            if (!charInArray(PatriceThreeThreads.SPECIALS[i], expected)) {
                ok = false;
            }
        }

        assertTrue("SPECIALS array contains only {!,@,#,$,%,&,*}", ok);
        assertTrue("SPECIALS array has 7 characters", PatriceThreeThreads.SPECIALS.length == 7);
    }

    // -----------------------------------------------------------------
    // Test: incrementLetterCount increments correctly
    // -----------------------------------------------------------------
    public static void testIncrementLetterCount() {

        PatriceThreeThreads app = new PatriceThreeThreads();

        app.incrementLetterCount();
        app.incrementLetterCount();
        app.incrementLetterCount();

        assertTrue("incrementLetterCount increments to 3", app.getLetterCount() == 3);
    }

    // -----------------------------------------------------------------
    // Test: incrementDigitCount increments correctly
    // -----------------------------------------------------------------
    public static void testIncrementDigitCount() {

        PatriceThreeThreads app = new PatriceThreeThreads();

        app.incrementDigitCount();
        app.incrementDigitCount();

        assertTrue("incrementDigitCount increments to 2", app.getDigitCount() == 2);
    }

    // -----------------------------------------------------------------
    // Test: incrementSpecialCount increments correctly
    // -----------------------------------------------------------------
    public static void testIncrementSpecialCount() {

        PatriceThreeThreads app = new PatriceThreeThreads();
        app.incrementSpecialCount();

        assertTrue("incrementSpecialCount increments to 1", app.getSpecialCount() == 1);
    }

    // -----------------------------------------------------------------
    // Test: full run meets minimum count of 10000 for all three threads
    // -----------------------------------------------------------------
    public static void testFullRunMeetsMinimumCount() throws InterruptedException {

        PatriceThreeThreads app = new PatriceThreeThreads();

        LetterRunnable  letterRunnable  = new LetterRunnable(app);
        DigitRunnable   digitRunnable   = new DigitRunnable(app);
        SpecialRunnable specialRunnable = new SpecialRunnable(app);

        Thread letterThread  = new Thread(letterRunnable);
        Thread digitThread   = new Thread(digitRunnable);
        Thread specialThread = new Thread(specialRunnable);

        letterThread.setName("LetterThread");
        digitThread.setName("DigitThread");
        specialThread.setName("SpecialThread");

        letterThread.start();
        digitThread.start();
        specialThread.start();

        letterThread.join();
        digitThread.join();
        specialThread.join();

        assertTrue("letterCount  >= 10000", app.getLetterCount()  >= PatriceThreeThreads.MIN_COUNT);
        assertTrue("digitCount   >= 10000", app.getDigitCount()   >= PatriceThreeThreads.MIN_COUNT);
        assertTrue("specialCount >= 10000", app.getSpecialCount() >= PatriceThreeThreads.MIN_COUNT);
    }

    // -----------------------------------------------------------------
    // main
    // -----------------------------------------------------------------
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Running PatriceThreeThreads Tests...\n");

        testInitialCountersAreZero();
        testLettersArrayContentsAreValid();
        testDigitsArrayContentsAreValid();
        testSpecialsArrayContentsAreValid();
        testIncrementLetterCount();
        testIncrementDigitCount();
        testIncrementSpecialCount();
        testFullRunMeetsMinimumCount();

        System.out.println("\n----- " + passed + " passed,  " + failed + " failed -----");

        System.exit(failed == 0 ? 0 : 1);
    }
}
