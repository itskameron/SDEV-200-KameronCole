// M5Assignment2
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class M5Assignment2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle(100, Color.WHITE);

        // Mouse press = black
        circle.setOnMousePressed((MouseEvent e) -> {
            circle.setFill(Color.BLACK);
        });

        // Mouse release = white
        circle.setOnMouseReleased((MouseEvent e) -> {
            circle.setFill(Color.WHITE);
        });

        StackPane pane = new StackPane(circle);
        Scene scene = new Scene(pane, 300, 300);
        primaryStage.setTitle("Assignment2 - Circle Color Toggle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Test main
    public static void main(String[] args) {
        launch(args);
    }
}
