/**
 * ABSTRACT CLASS
 * Represents the base for all document types in the library.
 * Fulfills the "abstract class" requirement.
 */
public abstract class BaseDocument {
    // Fields
    private String title;
    private String author;
    private String category;

    // Constructor
    public BaseDocument(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }

    /**
     * Abstract method to be implemented by subclasses.
     * This will either return stored text or read content from a file.
     * @return The displayable content of the document.
     */
    public abstract String getDisplayContent();

    /**
     * Abstract method to get the string representation for file storage.
     * @return A delimited string for saving to a file.
     */
    public abstract String getStorageString();

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    /**
     * Overridden toString() method used by the ListView.
     * This is shared by all subclasses.
     */
    @Override
    public String toString() {
        return "Document [Title=" + title + ", Author=" + author + ", Category=" + category + "]";
    }
}