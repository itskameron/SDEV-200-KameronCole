package daysAndDates
public class DaysOfWeek {
	public static String DayOfWeekStr(int NumberOfDay) {
		String dayStr = ""; // Added Semicolon
		switch (NumberOfDay) {
			case 1:
				dayStr = "Sunday";
				break;
			case 2:
				dayStr = "Monday";
				break;
			case 3:
				dayStr = "Tuesday"; // Added Semicolon
				break;
			case 4:
				dayStr = "Wednesday"; 
				break; // Added break
			case 5:
				dayStr = "Thursday";
				break;
			case 6:
				dayStr = "Thursday";
				break;
			case 7:
				dayStr = "Saturday";
				break;
		}
	Return dayStr; // Return the day string
		
	}
}
