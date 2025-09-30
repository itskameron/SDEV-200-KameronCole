public class DocumentTest {
    public static void main(String[] args) {
        Document doc1 = new Document("Pride and Prejudice", "Jane Austen", 
                                     "Content of the book", "pride.txt", "Old Books");

        System.out.println("Testing Document Class:");
        System.out.println("Title: " + doc1.getTitle());
        System.out.println("Author: " + doc1.getAuthor());
        System.out.println("Category: " + doc1.getCategory());

        doc1.setTitle("Pride & Prejudice");
        System.out.println("Updated Title: " + doc1.getTitle());
        System.out.println(doc1);
    }
}
