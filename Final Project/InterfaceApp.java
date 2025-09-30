import java.util.ArrayList;

/**
 * Simulates a simple text-based interface for the Document Library.
 */
public class InterfaceApp {
    private String currentBackground;
    private Document currentDocument;
    private String searchQuery;
    private boolean isReading;
    private ArrayList<String> availableBackgrounds;

    // Constructor
    public InterfaceApp() {
        this.currentBackground = "Default";
        this.currentDocument = null;
        this.searchQuery = "";
        this.isReading = false;
        this.availableBackgrounds = new ArrayList<>();
        availableBackgrounds.add("Default");
        availableBackgrounds.add("Old Books");
        availableBackgrounds.add("Modern");
    }

    // Set the background
    public void setBackground(String background) {
        if (availableBackgrounds.contains(background)) {
            this.currentBackground = background;
        }
    }

    // Display a document
    public void displayDocument(Document doc) {
        if (doc != null) {
            this.currentDocument = doc;
            this.isReading = true;
            System.out.println("=== Displaying Document ===");
            System.out.println("Background: " + currentBackground);
            System.out.println("Title: " + doc.getTitle());
            System.out.println("Author: " + doc.getAuthor());
            System.out.println("Content: " + doc.getContent());
            System.out.println("===========================");
        }
    }

    // toString
    @Override
    public String toString() {
        return "InterfaceApp [Current Background=" + currentBackground + 
               ", Is Reading=" + isReading + "]";
    }
}
