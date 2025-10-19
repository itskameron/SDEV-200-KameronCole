import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class DocumentLibraryApp extends Application {

    private Library library;

    // --- Controls for Section 1: Add Manual Document ---
    private TextField titleField;
    private TextField authorField;
    private TextField categoryField;
    private TextArea contentArea;

    // --- Controls for Section 2: Import File Document ---
    private TextField importTitleField;
    private TextField importAuthorField;
    private TextField importCategoryField;
    private Label chosenFileLabel;
    private String selectedFilePath; // Store the chosen file path

    // --- Controls for Section 3: Search ---
    private TextField searchField;
    private ListView<BaseDocument> resultListView; // Use the abstract BaseDocument

    @Override
    public void start(Stage primaryStage) {
        library = new Library("My JavaFX Library");

        // --- Section 1: Add Manual Document UI ---
        Label addLabel = new Label("Add a New Document (Manual)");
        addLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        titleField = new TextField();
        titleField.setPromptText("Enter title");

        authorField = new TextField();
        authorField.setPromptText("Enter author");

        categoryField = new TextField();
        categoryField.setPromptText("Enter category");

        contentArea = new TextArea();
        contentArea.setPromptText("Enter document content...");
        contentArea.setPrefRowCount(4);

        Button addButton = new Button("Add Document");
        addButton.setOnAction(e -> addManualDocumentEvent()); // EVENT #1

        VBox addBox = new VBox(8, addLabel, titleField, authorField, categoryField, contentArea, addButton);
        addBox.setPadding(new Insets(10));
        addBox.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5; -fx-background-color: #f9f9f9;");

        // --- NEW Section 2: Import File UI ---
        Label importLabel = new Label("Import Document from File");
        importLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        importTitleField = new TextField();
        importTitleField.setPromptText("Enter title");

        importAuthorField = new TextField();
        importAuthorField.setPromptText("Enter author");

        importCategoryField = new TextField();
        importCategoryField.setPromptText("Enter category");

        chosenFileLabel = new Label("No file selected.");
        Button chooseFileButton = new Button("Choose File...");
        chooseFileButton.setOnAction(e -> chooseFileEvent(primaryStage)); // EVENT #2

        Button importButton = new Button("Import File Document");
        importButton.setOnAction(e -> addFileDocumentEvent()); // EVENT #3

        VBox importBox = new VBox(8, importLabel, importTitleField, importAuthorField, importCategoryField,
                chooseFileButton, chosenFileLabel, importButton);
        importBox.setPadding(new Insets(10));
        importBox.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5; -fx-background-color: #f9f9f9;");

        // --- Combine left panels ---
        VBox leftPanel = new VBox(15, addBox, importBox);

        // --- Section 3: Search UI ---
        Label searchLabel = new Label("Search Documents");
        searchLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        searchField = new TextField();
        searchField.setPromptText("Enter title or author...");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchDocumentEvent()); // EVENT #4

        resultListView = new ListView<>();
        resultListView.setPrefHeight(380);
        resultListView.setPlaceholder(new Label("Search results will appear here."));
        resultListView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                BaseDocument selectedDoc = resultListView.getSelectionModel().getSelectedItem();
                if (selectedDoc != null) {
                    openReadingView(selectedDoc); // EVENT #5
                }
            }
        });

        // Populate search results on startup
        searchDocumentEvent();

        VBox searchBox = new VBox(8, searchLabel, searchField, searchButton, resultListView);
        searchBox.setPadding(new Insets(10));
        searchBox.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5; -fx-background-color: #f4f8ff;");

        // --- Layout ---
        HBox mainLayout = new HBox(15, leftPanel, searchBox);
        mainLayout.setPadding(new Insets(15));

        Scene scene = new Scene(mainLayout, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Document Library - JavaFX");
        primaryStage.show();
    }

    /**
     * Event #1 - Add Manual Document Button
     */
    private void addManualDocumentEvent() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String category = categoryField.getText().trim();
        String content = contentArea.getText().trim();

        if (title.isEmpty() || author.isEmpty() || content.isEmpty()) {
            showAlert("Missing Information", "Please fill in title, author, and content.");
            return;
        }

        Document newDoc = new Document(title, author, content, "manual-entry.txt", category);
        library.addDocument(newDoc, true); // Pass true to save

        showAlert("Document Added", "The document \"" + title + "\" was added.");

        titleField.clear();
        authorField.clear();
        categoryField.clear();
        contentArea.clear();
        searchDocumentEvent(); // Refresh search
    }

    /**
     * Event #2 - Choose File Button
     */
    private void chooseFileEvent(Stage parentStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Document File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        File file = fileChooser.showOpenDialog(parentStage);
        if (file != null) {
            selectedFilePath = file.getAbsolutePath();
            chosenFileLabel.setText(file.getName());
        }
    }

    /**
     * Event #3 - Add File Document Button
     */
    private void addFileDocumentEvent() {
        String title = importTitleField.getText().trim();
        String author = importAuthorField.getText().trim();
        String category = importCategoryField.getText().trim();

        if (title.isEmpty() || author.isEmpty() || selectedFilePath == null || selectedFilePath.isEmpty()) {
            showAlert("Missing Information", "Please fill in title, author, and select a file.");
            return;
        }

        FileDocument newDoc = new FileDocument(title, author, category, selectedFilePath);
        library.addDocument(newDoc, true); // Pass true to save

        showAlert("Document Imported", "The file \"" + title + "\" was imported.");

        importTitleField.clear();
        importAuthorField.clear();
        importCategoryField.clear();
        chosenFileLabel.setText("No file selected.");
        selectedFilePath = null;
        searchDocumentEvent(); // Refresh search
    }

    /**
     * Event #4 - Search Button
     */
    private void searchDocumentEvent() {
        String query = searchField.getText().trim();
        resultListView.getItems().clear();

        ArrayList<BaseDocument> results; // Use BaseDocument
        if (query.isEmpty()) {
            results = library.getDocuments();
        } else {
            results = library.search(query);
        }

        if (results.isEmpty()) {
            resultListView.setPlaceholder(new Label("No results found for: " + query));
        } else {
            resultListView.getItems().addAll(results);
        }
    }

    /**
     * Event #5 - Open Reading View (Now uses Polymorphism)
     * @param doc The BaseDocument to display (could be Document or FileDocument)
     */
    private void openReadingView(BaseDocument doc) {
        Stage readingStage = new Stage();
        readingStage.initModality(Modality.APPLICATION_MODAL);
        readingStage.setTitle(doc.getTitle());

        Label titleLabel = new Label(doc.getTitle());
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        Label authorLabel = new Label("By: " + doc.getAuthor());
        authorLabel.setFont(Font.font("Arial", FontWeight.ITALIC, 14));

        // --- POLYMORPHISM IN ACTION ---
        // We don't care if it's a Document or FileDocument.
        // We just call getDisplayContent() and the correct version runs.
        String content = doc.getDisplayContent();

        TextArea contentDisplay = new TextArea(content);
        contentDisplay.setEditable(false);
        contentDisplay.setWrapText(true);
        contentDisplay.setPrefHeight(400);

        String backgroundStyle = "", textStyle = "", textAreaStyle = "";
        switch (doc.getCategory().toLowerCase()) {
            case "old books":
                backgroundStyle = "-fx-background-color: #f5f5dc;";
                textStyle = "-fx-text-fill: #3a2a0a;";
                textAreaStyle = "-fx-control-inner-background: #f5f5dc; -fx-text-fill: #3a2a0a; -fx-font-family: 'Times New Roman';";
                break;
            case "dystopian":
                backgroundStyle = "-fx-background-color: #333333;";
                textStyle = "-fx-text-fill: #e0e0e0;";
                textAreaStyle = "-fx-control-inner-background: #444444; -fx-text-fill: #e0e0e0; -fx-font-family: 'Courier New';";
                break;
            default:
                backgroundStyle = "-fx-background-color: #ffffff;";
                textStyle = "-fx-text-fill: #000000;";
                textAreaStyle = "-fx-control-inner-background: #ffffff; -fx-text-fill: #000000;";
                break;
        }

        titleLabel.setStyle(textStyle);
        authorLabel.setStyle(textStyle);
        contentDisplay.setStyle(textAreaStyle);

        VBox layout = new VBox(10, titleLabel, authorLabel, new Separator(), contentDisplay);
        layout.setPadding(new Insets(15));
        layout.setStyle(backgroundStyle);
        VBox.setVgrow(contentDisplay, Priority.ALWAYS);

        Scene scene = new Scene(layout, 600, 500);
        readingStage.setScene(scene);
        readingStage.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}