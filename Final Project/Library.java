import java.util.ArrayList;

/**
 * Represents a library of documents and manages them.
 */
public class Library {
    private ArrayList<Document> documents;
    private String libraryName;
    private int totalDocuments;
    private String lastSearch;
    private boolean isModified;

    // Constructor
    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.documents = new ArrayList<>();
        this.totalDocuments = 0;
        this.lastSearch = "";
        this.isModified = false;
    }

    // Add a document
    public void addDocument(Document doc) {
        if (doc != null) {
            documents.add(doc);
            totalDocuments++;
            isModified = true;
        }
    }

    // Remove a document
    public void removeDocument(Document doc) {
        if (documents.remove(doc)) {
            totalDocuments--;
            isModified = true;
        }
    }

    // Search documents by query in title or author
    public ArrayList<Document> search(String query) {
        lastSearch = query;
        ArrayList<Document> results = new ArrayList<>();
        for (Document doc : documents) {
            if (doc.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                doc.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                results.add(doc);
            }
        }
        return results;
    }

    // Getters
    public ArrayList<Document> getDocuments() { return documents; }
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
