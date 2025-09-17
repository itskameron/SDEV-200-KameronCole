import java.io.*;
import java.util.*;

public class M4assignment2 {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java M4assignment2 <Java source file>");
            return;
        }

        String filename = args[0];
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            return;
        }

        int count = countKeywords(file);
        System.out.println("The number of keywords in " + filename + " is " + count);
    }

    public static int countKeywords(File file) throws Exception {
        // All Java keywords + true, false, null
        String[] keywordString = {
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", 
            "char", "class", "const", "continue", "default", "do", "double", 
            "else", "enum", "extends", "for", "final", "finally", "float", 
            "goto", "if", "implements", "import", "instanceof", "int", 
            "interface", "long", "native", "new", "package", "private", 
            "protected", "public", "return", "short", "static", "strictfp", 
            "super", "switch", "synchronized", "this", "throw", "throws", 
            "transient", "try", "void", "volatile", "while", "true", "false", "null"
        };

        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));
        int count = 0;

        Scanner input = new Scanner(file);
        boolean inBlockComment = false;

        while (input.hasNextLine()) {
            String line = input.nextLine();

            // Remove block comments
            if (inBlockComment) {
                int endComment = line.indexOf("*/");
                if (endComment != -1) {
                    line = line.substring(endComment + 2);
                    inBlockComment = false;
                } else {
                    continue;
                }
            }

            int blockStart = line.indexOf("/*");
            while (blockStart != -1) {
                int blockEnd = line.indexOf("*/", blockStart + 2);
                if (blockEnd != -1) {
                    line = line.substring(0, blockStart) + line.substring(blockEnd + 2);
                    blockStart = line.indexOf("/*");
                } else {
                    line = line.substring(0, blockStart);
                    inBlockComment = true;
                    break;
                }
            }

            // Remove line comments
            int lineComment = line.indexOf("//");
            if (lineComment != -1) {
                line = line.substring(0, lineComment);
            }

            // Remove string literals
            line = line.replaceAll("\"(\\\\.|[^\"\\\\])*\"", "");

            // Split words and count keywords
            String[] words = line.split("\\W+");
            for (String word : words) {
                if (keywordSet.contains(word)) {
                    count++;
                }
            }
        }

        input.close();
        return count;
    }
}
