// M5Assignment1.java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class M5Assignment1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Load images from the Images folder (same level as this .java file)
        Image img1 = new Image("file:Images/flag1.gif");
        Image img2 = new Image("file:Images/flag2.gif");
        Image img3 = new Image("file:Images/flag6.gif");
        Image img4 = new Image("file:Images/flag7.gif");

        // ImageViews
        ImageView iv1 = new ImageView(img1);
        ImageView iv2 = new ImageView(img2);
        ImageView iv3 = new ImageView(img3);
        ImageView iv4 = new ImageView(img4);

        // Scale to a reasonable size
        iv1.setFitWidth(200);
        iv2.setFitWidth(200);
        iv3.setFitWidth(200);
        iv4.setFitWidth(200);

        iv1.setPreserveRatio(true);
        iv2.setPreserveRatio(true);
        iv3.setPreserveRatio(true);
        iv4.setPreserveRatio(true);

        // Layout in 2x2 grid
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(iv1, 0, 0);
        grid.add(iv2, 1, 0);
        grid.add(iv3, 0, 1);
        grid.add(iv4, 1, 1);

        Scene scene = new Scene(grid);
        primaryStage.setTitle("M5Assignment1 - Four Flags");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
