 /*
 * Kameron Cole
 * SDEV 200
 * Module 1 Programming Assignment (2)
 */

 /*
  * 
  */

import java.util.Scanner;

public class Assignment2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a credit card number to check validity: ");
        long number = input.nextLong();

        if (isValid(number)) {
            System.out.println(number + " is valid.");
        } else {
            System.out.println(number + " is invalid.");
        }

        input.close();
    }

    /* Returns true if the number is valid */
    public static boolean isValid(long number) {
        int size = getSize(number);

        // Check number size
        if (size < 13 || size > 16) return false;

        // Check first number(s)
        if (!(prefixMatched(number, 4) ||
              prefixMatched(number, 5) ||
              prefixMatched(number, 37) ||
              prefixMatched(number, 6))) {
            return false;
        }

        // Apply Luhn algorithm
        int sum = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
        return sum % 10 == 0;
    }

    // Get the results from step 2
    public static int sumOfDoubleEvenPlace(long number) {
        String numStr = Long.toString(number);
        int sum = 0;

        // Start from second-to-last digit and move left
        for (int i = numStr.length() - 2; i >= 0; i -= 2) {
            int digit = Character.getNumericValue(numStr.charAt(i));
            sum += getDigit(digit * 2);
        }
        return sum;
    }

    // Return this number if it is a single digit or return the sum of digits
    public static int getDigit(int number) {
        if (number < 10) return number;
        return (number / 10) + (number % 10);
    }

    // Return the sum of odd-place digits in number
    public static int sumOfOddPlace(long number) {
        String numStr = Long.toString(number);
        int sum = 0;

        for (int i = numStr.length() - 1; i >= 0; i -= 2) {
            int digit = Character.getNumericValue(numStr.charAt(i));
            sum += digit;
        }
        return sum;
    }

    // Return true if the number d is a prefix for the number
    public static boolean prefixMatched(long number, int d) {
        int prefixSize = getSize(d);
        return getPrefix(number, prefixSize) == d;
    }

    // Return the number of digits in d
    public static int getSize(long d) {
        return Long.toString(d).length();
    }

    // Return the first k digits from number
    public static long getPrefix(long number, int k) {
        String numStr = Long.toString(number);
        if (numStr.length() < k) {
            return number;
        }
        return Long.parseLong(numStr.substring(0, k));
    }
}