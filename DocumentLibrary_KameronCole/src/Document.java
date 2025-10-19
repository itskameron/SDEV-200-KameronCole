/**
 * Represents a text document with manually entered content.
 * Fulfills the "inheritance" requirement by extending BaseDocument.
 */
public class Document extends BaseDocument {
    // Fields
    private String content;
    private String filePath; // Kept to match original UML [cite: 37]

    // Constructor
    public Document(String title, String author, String content, String filePath, String category) {
        super(title, author, category); // Call to abstract parent constructor
        this.content = content;
        this.filePath = filePath;
    }

    // --- Implementation of Abstract Methods ---

    @Override
    public String getDisplayContent() {
        // For a manual Document, just return the content field
        return this.content;
    }

    @Override
    public String getStorageString() {
        // "MANUAL" type, content is stored directly in the file
        // Replaces newlines to prevent corrupting the save file
        String safeContent = content.replace("\n", "<NEWLINE>");
        return "MANUAL:::" + getTitle() + ":::" + getAuthor() + ":::" + getCategory() + ":::" + getFilePath() + ":::" + safeContent;
    }

    // --- Getters and Setters for local fields ---

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
}