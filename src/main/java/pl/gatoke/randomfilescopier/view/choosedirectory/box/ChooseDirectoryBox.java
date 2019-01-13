package pl.gatoke.randomfilescopier.view.choosedirectory.box;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import pl.gatoke.randomfilescopier.view.choosedirectory.Direction;

import java.io.File;

public class ChooseDirectoryBox {

    private static final String INITIAL_DIRECTORY = "user.home";
    private static final int TEXT_AREA_MINIMUM_HEIGHT = 25;
    private static final int TEXT_AREA_MAXIMUM_HEIGHT = 25;

    private final DirectoryChooser directoryChooser;
    private final TextArea textArea;
    private final Button button;

    public ChooseDirectoryBox(final Stage primaryStage, final Direction direction) {
        final String directionMessage = direction.getMessage();
        this.directoryChooser = createDirectoryChooser(directionMessage);
        this.button = createButton(primaryStage, directionMessage);
        this.textArea = createTextArea();
    }

    public Node[] get() {
        return new Node[]{button, textArea};
    }

    private DirectoryChooser createDirectoryChooser(final String systemDialogTitle) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(systemDialogTitle);
        directoryChooser.setInitialDirectory(new File(System.getProperty(INITIAL_DIRECTORY)));
        return directoryChooser;
    }

    private TextArea createTextArea() {
        final TextArea textArea = new TextArea();
        textArea.setPrefWidth(200);
        textArea.setMinHeight(TEXT_AREA_MINIMUM_HEIGHT);
        textArea.setMaxHeight(TEXT_AREA_MAXIMUM_HEIGHT);
        return textArea;
    }

    private Button createButton(final Stage stage, final String text) {
        final Button button = new Button(text);
        button.getStyleClass().add("choose-directory");
//        button.setPrefWidth(200);
        button.setOnAction(event -> {
            final File dir = directoryChooser.showDialog(stage);
            if (dir != null) {
                textArea.setText(dir.getAbsolutePath());
            } else {
                textArea.setText(null);
            }
        });
        return button;
    }
}
