import java.util.Scanner;

public class SafeInput {
    /**
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do {
            System.out.print("\n" + prompt + ": ");// show prompt add space
            retString = pipe.nextLine();
        } while (retString.length() == 0);

        return retString;
    }

    //Part B
    public static int getInt(Scanner pipe, String prompt) {
        int retInt = 0;
        boolean isValid = false;
        String trash = "";

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retInt = pipe.nextInt();
                isValid = true;
            } else {
                trash = pipe.next(); // Read the trash input
                System.out.println("Invalid input: \"" + trash + "\". Please enter a valid integer!");
            }
            pipe.nextLine();
        } while (!isValid);

        return retInt;
    }

    //Part C
    public static double getDouble(Scanner pipe, String prompt) {
        double retDouble = 0.0;
        boolean isValid = false;
        String trash = "";

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retDouble = pipe.nextDouble();
                isValid = true;
            } else {
                trash = pipe.next(); // Read the trash input
                System.out.println("Invalid input: \"" + trash + "\". Please enter a valid double value.");
            }
            pipe.nextLine();
        } while (!isValid);

        return retDouble;
    }

    // Part D
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retInt = 0;
        boolean isValid = false;
        String trash = "";

        do {
            // Appends the range to the user-supplied prompt
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                retInt = pipe.nextInt();
                if (retInt >= low && retInt <= high) {
                    isValid = true;
                } else {
                    System.out.println("Input out of range. Must be between " + low + " and " + high + ".");
                }
            } else {
                trash = pipe.next();
                System.out.println("Invalid input: \"" + trash + "\". Please enter an integer.");
            }
            pipe.nextLine();
        } while (!isValid);

        return retInt;
    }

    //Part E
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retDouble = 0.0;
        boolean isValid = false;
        String trash = "";

        do {
            // Appends the range to the user-supplied prompt
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                retDouble = pipe.nextDouble();
                if (retDouble >= low && retDouble <= high) {
                    isValid = true;
                } else {
                    System.out.println("Input out of range. Must be between " + low + " and " + high + ".");
                }
            } else {
                trash = pipe.next();
                System.out.println("Invalid input: \"" + trash + "\". Please enter a double value.");
            }
            pipe.nextLine();
        } while (!isValid);

        return retDouble;
    }

    // Part F
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String response = "";
        boolean isValid = false;
        boolean retValue = false;

        do {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = pipe.nextLine();

            if (response.equalsIgnoreCase("Y")) {
                retValue = true;
                isValid = true;
            } else if (response.equalsIgnoreCase("N")) {
                retValue = false;
                isValid = true;
            } else {
                System.out.println("Invalid input. You must enter 'Y', 'y', 'N', or 'n'.");
            }
        } while (!isValid);

        return retValue;
    }

    // Part G
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String response = "";
        boolean isValid = false;

        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();

            if (response.matches(regEx)) {
                isValid = true;
            } else {
                System.out.println("Input does not match the required pattern: " + regEx);
            }
        } while (!isValid);

        return response;
    }

    //Part H
    public static void prettyHeader(String msg) {
        int totalWidth = 60; // Total output width is always 60 characters
        int borderStars = 3; // 3 stars on either end
        int availableSpace = totalWidth - (borderStars * 2); // 60 - 6 = 54 spaces total

        // To avoid negative numbers if the message is too long!
        if (msg.length() > availableSpace) {
            msg = msg.substring(0, availableSpace);
        }
        // Top row
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();

        // Center row calc
        System.out.print("***");

        int msgLength = msg.length();
        int totalSpacesNeeded = availableSpace - msgLength;

        int leftSpaces = totalSpacesNeeded / 2;
        int rightSpaces = totalSpacesNeeded - leftSpaces; // Handles odd message lengths perfectly

        // Print left spaces
        for (int i = 0; i < leftSpaces; i++) {
            System.out.print(" ");
        }

        // Print the centered message
        System.out.print(msg);

        // Print right spaces
        for (int i = 0; i < rightSpaces; i++) {
            System.out.print(" ");
        }

        System.out.println("***"); // Print ending 3 stars

        // Bottom row of asterisks
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}