 /*
 * Kameron Cole
 * SDEV 200
 * Module 1 Programming Assignment (1)
 */

 /*
  * Write a class that contains the following two methods:
  */

  // Convert from feet to meters
  //public static double footToMeter (double foot)

  // Convert from meters to feet
  //public static double meterToFoot (double meter)

  /* The formula for the conversion is:
  meter = 0.305 * foot
  foot = 3.279 * meter
  */

public class Assignment1 {
    // Convert from feet to meters
    public static double footToMeter(double foot) {
        return 0.305 * foot;
    }
    // Convert from meters to feet
    public static double meterToFoot(double meter) {
        return 3.279 * meter;
    }

    public static void main(String[] args) {
        // Header
        System.out.printf("%-10s%-10s  %-12s%-10s%n", "Feet", "Meters", "Meters", "Feet");
        System.out.println("-------------------------------------------------------------");


        // Starting values
        double foot = 1.0; // will count up by 1.0
        double meter = 20.0; // will count up by 5.0

        // Print 10 rows
        for (int i = 0; i < 10; i++) {
            double fFromM =  meterToFoot(meter);
            double mFromF = footToMeter(foot);

            // Prints with 3 decimals
            System.out.printf("%-10.1f%-12.3f  %-12.1f%-10.3f%n", foot, mFromF, meter, fFromM);
            foot += 1.0;
            meter += 5.0;
        }
    }
  }