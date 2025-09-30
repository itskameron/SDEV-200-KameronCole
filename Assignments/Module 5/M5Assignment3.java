// M5Assignment3
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class M5Assignment3 extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label text = new Label("Color Me!");
        text.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

        // Sliders for RGBA
        Slider redSlider = createSlider();
        Slider greenSlider = createSlider();
        Slider blueSlider = createSlider();
        Slider opacitySlider = createSlider();
        opacitySlider.setValue(100); // default 100%

        Label rLabel = new Label("Red");
        Label gLabel = new Label("Green");
        Label bLabel = new Label("Blue");
        Label oLabel = new Label("Opacity");

        // Update color method
        Runnable updateColor = () -> {
            double r = redSlider.getValue() / 100.0;
            double g = greenSlider.getValue() / 100.0;
            double b = blueSlider.getValue() / 100.0;
            double o = opacitySlider.getValue() / 100.0;
            text.setTextFill(new Color(r, g, b, o));
        };

        // Add listeners
        redSlider.valueProperty().addListener(e -> updateColor.run());
        greenSlider.valueProperty().addListener(e -> updateColor.run());
        blueSlider.valueProperty().addListener(e -> updateColor.run());
        opacitySlider.valueProperty().addListener(e -> updateColor.run());

        VBox vbox = new VBox(10, text,
                rLabel, redSlider,
                gLabel, greenSlider,
                bLabel, blueSlider,
                oLabel, opacitySlider);
        vbox.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setTitle("Assignment3 - Text Color Control");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Slider createSlider() {
        Slider slider = new Slider(0, 100, 0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        return slider;
    }

    // Test main
    public static void main(String[] args) {
        launch(args);
    }
}
