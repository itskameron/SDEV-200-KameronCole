import java.util.ArrayList;

public class LibraryTest {
    public static void main(String[] args) {
        // Note: This test will create/overwrite "library.dat" in the project root

        System.out.println("Testing Library Class:");
        Library myLibrary = new Library("My Test Library");
        System.out.println("Library on startup: " + myLibrary);
        System.out.println("Total docs on startup: " + myLibrary.getTotalDocuments());


        // Create one of each document type
        Document doc1 = new Document("Pride and Prejudice", "Jane Austen", "Content", "pride.txt", "Old Books");
        FileDocument doc2 = new FileDocument("1984", "George Orwell", "Dystopian", "path/to/1984.txt"); // Path is just a string

        myLibrary.addDocument(doc1, true);
        myLibrary.addDocument(doc2, true);

        System.out.println("\nLibrary after adding 2 docs: " + myLibrary);
        System.out.println("Total docs: " + myLibrary.getTotalDocuments());

        ArrayList<BaseDocument> results = myLibrary.search("Pride");
        System.out.println("Search results for 'Pride': " + results);

        results = myLibrary.search("Orwell");
        System.out.println("Search results for 'Orwell': " + results);

        myLibrary.removeDocument(doc1);
        System.out.println("\nLibrary after removing a document: " + myLibrary);

        // Test loading from file
        System.out.println("\n--- Testing File Load ---");
        Library newLibrary = new Library("Reloaded Library");
        System.out.println("Reloaded Library: " + newLibrary);
        System.out.println("Total docs (should be 1): " + newLibrary.getTotalDocuments());
        System.out.println("Docs list: " + newLibrary.getDocuments());
    }
}