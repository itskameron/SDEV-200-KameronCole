public class BinaryConverter {
    // Converts binary string to decimal, throws exception if invalid
    public static int bin2Dec(String binaryString) throws BinaryFormatException {
        // Check if string contains only 0s and 1s
        for (int i = 0; i < binaryString.length(); i++) {
            char c = binaryString.charAt(i);
            if (c != '0' && c != '1') {
                throw new BinaryFormatException("Invalid binary character: " + c);
            }
        }

        // Convert manually (no parseInt with radix, since assignment wants custom logic)
        int decimal = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            decimal = decimal * 2 + (binaryString.charAt(i) - '0');
        }

        return decimal;
    }
}