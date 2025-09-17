import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class M4assignment1 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java M4assignment1 <source-file>");
            return;
        }

        String filename = args[0];
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            Stack<Character> stack = new Stack<>();
            boolean isValid = true;

            while (scanner.hasNextLine() && isValid) {
                String line = scanner.nextLine();
                for (char ch : line.toCharArray()) {
                    if (ch == '(' || ch == '{' || ch == '[') {
                        stack.push(ch);
                    } else if (ch == ')' || ch == '}' || ch == ']') {
                        if (stack.isEmpty()) {
                            isValid = false;
                            break;
                        }
                        char last = stack.pop();
                        if (!matches(last, ch)) {
                            isValid = false;
                            break;
                        }
                    }
                }
            }

            if (isValid && stack.isEmpty()) {
                System.out.println("All grouping symbols are correctly matched.");
            } else {
                System.out.println("Grouping symbols are NOT correctly matched.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}
