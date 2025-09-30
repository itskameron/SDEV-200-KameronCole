/**
 * Represents a text document with metadata.
 */
public class Document {
    // Fields
    private String title;
    private String author;
    private String content;
    private String filePath;
    private String category;

    // Constructor
    public Document(String title, String author, String content, String filePath, String category) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.filePath = filePath;
        this.category = category;
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    // toString method
    @Override
    public String toString() {
        return "Document [Title=" + title + ", Author=" + author + ", Category=" + category + "]";
    }
}
