import java.util.Scanner;

public class TestBinaryConverter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a binary string: ");
        String binaryString = input.nextLine();

        try {
            int decimalValue = BinaryConverter.bin2Dec(binaryString);
            System.out.println("The decimal value is: " + decimalValue);
        } catch (BinaryFormatException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        input.close();
    }
}