Import daysAndDates.DaysOfWeek; // Fixed spelling of import
public class TestDaysOfWeek {

	public static void main(String[] args) {
		System.out.println("Days Of week: ");

		for (int i = 1;i < 7;i++) {	// Start from 1 and end at 7 to match day numbers
			System.out.println("Number: " + i + "\tDay Of Week: " + DaysOfWeek.DayOfWeekStr(i)); // Added a semicolon
		}

	}

}
