import java.util.Scanner;

public class SafeInput {

    /**
     * Gets a non-zero-length String from the user
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt the prompt message for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);
        return retString;
    }

    /**
     * Gets an integer value from the user with input validation
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt the prompt message for the user
     * @return a valid int value entered by the user
     */
    public static int getInt(Scanner pipe, String prompt) {
        int retValue = 0;
        boolean done = false;
        String trash = "";

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retValue = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid integer. You entered: " + trash);
            }
        } while (!done);

        return retValue;
    }
    /**
     * Get a valid double value from user input.
     * @param pipe Scanner instance to read input
     * @param prompt The prompt message to display
     * @return a valid double entered by the user
     */
    public static double getDouble(Scanner pipe, String prompt) {
        double retValue = 0;
        boolean done = false;
        String trash = "";

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retValue = pipe.nextDouble();
                pipe.nextLine(); // clear the input buffer
                done = true;
            } else {
                trash = pipe.nextLine(); // discard invalid input
                System.out.println("You must enter a valid decimal number. You entered: " + trash);
            }
        } while (!done);

        return retValue;
    }
    /**
     * Gets an int from the user within a specified inclusive range
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt the prompt message
     * @param low    the low end of the inclusive range
     * @param high   the high end of the inclusive range
     * @return a valid int within the specified range
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retValue = 0;
        boolean done = false;
        String trash = "";

        do {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                retValue = pipe.nextInt();
                pipe.nextLine(); // clear the input buffer
                if (retValue >= low && retValue <= high) {
                    done = true;
                } else {
                    System.out.println("Input out of range [" + low + " - " + high + "]: " + retValue);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid integer. You entered: " + trash);
            }
        } while (!done);

        return retValue;
    }
    /**
     * Gets a double from the user within a specified inclusive range
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt the prompt message
     * @param low    the low end of the inclusive range
     * @param high   the high end of the inclusive range
     * @return a valid double within the specified range
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retValue = 0;
        boolean done = false;
        String trash = "";

        do {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                retValue = pipe.nextDouble();
                pipe.nextLine(); // clear the input buffer
                if (retValue >= low && retValue <= high) {
                    done = true;
                } else {
                    System.out.println("Input out of range [" + low + " - " + high + "]: " + retValue);
                }
            } else {
                trash = pipe.nextLine(); // read and discard bad input
                System.out.println("You must enter a valid number. You entered: " + trash);
            }
        } while (!done);

        return retValue;
    }
    /**
     * Prompts the user to enter Y or N and returns true/false accordingly.
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt the prompt message
     * @return true for yes, false for no
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean isYes = false;
        boolean validInput = false;
        String response = "";

        do {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = pipe.nextLine().trim();
            if (response.equalsIgnoreCase("Y")) {
                isYes = true;
                validInput = true;
            } else if (response.equalsIgnoreCase("N")) {
                isYes = false;
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter Y or N. You entered: " + response);
            }
        } while (!validInput);

        return isYes;
    }
    /**
     * Gets a string from the user that matches a RegEx pattern
     *
     * @param pipe   Scanner instance to read input
     * @param prompt prompt message to display
     * @param regEx  the regular expression the input must match
     * @return the string input that matches the pattern
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String response = "";
        boolean valid = false;

        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();
            if (response.matches(regEx)) {
                valid = true;
            } else {
                System.out.println("Input does not match the pattern. Try again.");
            }
        } while (!valid);

        return response;
    }
    /**
     * Displays a formatted header with a message centered in a decorative frame
     *
     * @param msg the message to display
     *
     * Pseudocode:
     * -- Define total width of the header line
     * -- Calculate how much space is needed to center the message
     * -- Print top line of asterisks
     * -- Print line with message centered between "***" and " ***"
     * -- Print bottom line of asterisks
     */

    public static void prettyHeader(String msg) {
        final int WIDTH = 60;
        int padding = (WIDTH - msg.length() - 6) / 2; // Adjusting for "*** " and " ***"

        // Print top border
        for (int i = 0; i < WIDTH; i++) {
            System.out.print("*");
        }
        System.out.println();

        // Print middle line with message
        System.out.print("***");
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < WIDTH - msg.length() - padding - 6; i++) {
            System.out.print(" ");
        }
        System.out.println("***");

        // Print bottom border
        for (int i = 0; i < WIDTH; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
    /**
     * Displays a Celsius to Fahrenheit conversion table
     * from 0.0 to 150.0 in 0.1 increments
     */
    public static void ctofTable() {
        double fVal;

        System.out.println("Celsius      Fahrenheit");

        for (double cTemp = 0.0; cTemp <= 150.0; cTemp += 0.1) {
            fVal = cTemp * 9.0 / 5 + 32;
            System.out.printf("%6.2f\t%6.2f\n", cTemp, fVal);
        }
    }
}

