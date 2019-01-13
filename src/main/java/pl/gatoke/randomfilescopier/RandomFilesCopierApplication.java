package pl.gatoke.randomfilescopier;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.gatoke.randomfilescopier.view.MainScreen;

public class RandomFilesCopierApplication extends Application {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.getIcons().add(new Image("icon.png"));
        final VBox root = setup(primaryStage);

        final Scene scene = new Scene(root, 500, 300);
        scene.getStylesheets().add("styles.css");
        primaryStage.setTitle("Random Files Copier");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox setup(final Stage primaryStage) {
        final MainScreen mainScreen = new MainScreen(primaryStage);
        final VBox vBox = new VBox();
        vBox.getStyleClass().add("main-screen");
        vBox.getChildren().addAll(mainScreen.get());
        return vBox;
    }
}
