import java.util.ArrayList;

public class LibraryTest {
    public static void main(String[] args) {
        Library myLibrary = new Library("My Documents");

        Document doc1 = new Document("Pride and Prejudice", "Jane Austen", "Content", "pride.txt", "Old Books");
        Document doc2 = new Document("1984", "George Orwell", "Content", "1984.txt", "Dystopian");

        myLibrary.addDocument(doc1);
        myLibrary.addDocument(doc2);

        System.out.println("Testing Library Class:");
        System.out.println(myLibrary);

        ArrayList<Document> results = myLibrary.search("Pride");
        System.out.println("Search results for 'Pride': " + results);

        myLibrary.removeDocument(doc1);
        System.out.println("Library after removing a document: " + myLibrary);
    }
}
