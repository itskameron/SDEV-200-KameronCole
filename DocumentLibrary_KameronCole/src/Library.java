import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a library of documents and manages them.
 * Now saves/loads all documents to a text file (library.dat)
 * to fulfill the data backend requirement.
 */
public class Library {
    // Use the abstract BaseDocument type
    private ArrayList<BaseDocument> documents;
    private String libraryName;
    private int totalDocuments;
    private String lastSearch;
    private boolean isModified;

    // Constants for the text file data backend
    private static final String SAVE_FILE = "library.dat";
    private static final String DELIMITER = ":::";

    // Constructor
    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.documents = new ArrayList<>();
        this.totalDocuments = 0;
        this.lastSearch = "";
        this.isModified = false;
        loadLibrary(); // Load existing documents on startup
    }

    /**
     * Adds a document to the library and optionally saves.
     * @param doc The document to add
     * @param save Triggers a save to the file
     */
    public void addDocument(BaseDocument doc, boolean save) {
        if (doc != null) {
            documents.add(doc);
            totalDocuments++;
            isModified = true;
            if (save) {
                saveLibrary();
            }
        }
    }

    /**
     * Removes a document and saves the change.
     * @param doc The document to remove
     */
    public void removeDocument(BaseDocument doc) {
        if (documents.remove(doc)) {
            totalDocuments--;
            isModified = true;
            saveLibrary();
        }
    }

    /**
     * Saves the entire library to the "library.dat" text file.
     * Fulfills the "data backend" requirement.
     */
    private void saveLibrary() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE))) {
            for (BaseDocument doc : documents) {
                // Use the polymorphic getStorageString() method
                writer.write(doc.getStorageString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving library: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads the library from the "library.dat" text file on startup.
     */
    private void loadLibrary() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            return; // No file to load
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts.length < 4) continue; // Skip corrupted line

                String type = parts[0];
                String title = parts[1];
                String author = parts[2];
                String category = parts[3];

                if (type.equals("FILE") && parts.length >= 5) {
                    String filePath = parts[4];
                    addDocument(new FileDocument(title, author, category, filePath), false);
                } else if (type.equals("MANUAL") && parts.length >= 6) {
                    String filePath = parts[4];
                    String content = parts[5].replace("<NEWLINE>", "\n"); // Restore newlines
                    addDocument(new Document(title, author, content, filePath, category), false);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading library: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Search documents by query in title or author
    public ArrayList<BaseDocument> search(String query) {
        lastSearch = query;
        ArrayList<BaseDocument> results = new ArrayList<>();
        // Iterate over the abstract BaseDocument type
        for (BaseDocument doc : documents) {
            if (doc.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    doc.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                results.add(doc);
            }
        }
        return results;
    }

    // Getters
    public ArrayList<BaseDocument> getDocuments() { return documents; }
    public int getTotalDocuments() { return totalDocuments; }
    public String getLibraryName() { return libraryName; }
    public String getLastSearch() { return lastSearch; }
    public boolean isModified() { return isModified; }

    // Setter
    public void setLibraryName(String libraryName) { this.libraryName = libraryName; }

    // toString
    @Override
    public String toString() {
        return "Library [Name=" + libraryName + ", Total Documents=" + totalDocuments + "]";
    }
}