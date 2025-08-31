 /*
 * Kameron Cole
 * SDEV 200
 * Module 1 Programming Assignment (3)
 */

 /* Remember you MUST use two multi-dimensional arrays */

import java.util.Scanner;

public class Assignment3 {

    // Method to check if two 2D arrays are identical
    public static boolean equals(int[][] m1, int[][] m2) {
        if (m1.length != m2.length) {
            return false;
        }
        
        for (int i = 0; i < m1.length; i++) {
            if (m1[i].length != m2[i].length) {
                return false;
            }
            for (int j = 0; j < m1[i].length; j++) {
                if (m1[i][j] != m2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Main method
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[][] m1 = new int[3][3];
        int[][] m2 = new int[3][3];

        // Prompt user to enter first 3x3 array
        System.out.print("Enter m1 (a 3 by 3 matrix) row by row: ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m1[i][j] = input.nextInt();
            }
        }

        // Prompt user to enter second 3x3 array
        System.out.print("Enter m2 (a 3 by 3 matrix) row by row: ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m2[i][j] = input.nextInt();
            }
        }

        // Display result
        if (equals(m1, m2)) {
            System.out.println("The two arrays are identical.");
        } else {
            System.out.println("The two arrays are not identical.");
        }

        input.close();
    }
}