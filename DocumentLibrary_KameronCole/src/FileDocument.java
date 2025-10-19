import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Represents a document where the content is read from an external file.
 * Fulfills the "inheritance" requirement by extending BaseDocument.
 */
public class FileDocument extends BaseDocument {

    private String filePath; // Path to the .txt file

    // Constructor
    public FileDocument(String title, String author, String category, String filePath) {
        super(title, author, category);
        this.filePath = filePath;
    }

    // --- Implementation of Abstract Methods ---

    @Override
    public String getDisplayContent() {
        // For a FileDocument, read the content from the file at filePath
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Could not read file from path: " + filePath;
        }
        return content.toString();
    }

    @Override
    public String getStorageString() {
        // "FILE" type, only the path is stored
        return "FILE:::" + getTitle() + ":::" + getAuthor() + ":::" + getCategory() + ":::" + getFilePath();
    }

    // --- Getters and Setters for local fields ---

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
}