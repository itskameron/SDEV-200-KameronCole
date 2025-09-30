public class InterfaceAppTest {
    public static void main(String[] args) {
        InterfaceApp app = new InterfaceApp();

        Document doc = new Document("Moby Dick", "Herman Melville", 
                                    "Call me Ishmael...", "moby.txt", "Old Books");

        System.out.println("Testing InterfaceApp Class:");
        app.setBackground("Old Books");
        app.displayDocument(doc);
        System.out.println(app);
    }
}
