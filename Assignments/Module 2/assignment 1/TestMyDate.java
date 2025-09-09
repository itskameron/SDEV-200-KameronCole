public class TestMyDate {
    public static void main(String[] args) {
        // Object with current date
        MyDate date1 = new MyDate();
        System.out.println("Current date:");
        System.out.println(date1.getYear() + "-" + (date1.getMonth() + 1) + "-" + date1.getDay());

        // Object with specified elapsed time
        MyDate date2 = new MyDate(34355555133101L);
        System.out.println("Date from elapsed time:");
        System.out.println(date2.getYear() + "-" + (date2.getMonth() + 1) + "-" + date2.getDay());
    }
}