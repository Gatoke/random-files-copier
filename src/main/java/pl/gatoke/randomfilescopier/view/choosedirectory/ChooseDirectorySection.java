package pl.gatoke.randomfilescopier.view.choosedirectory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import pl.gatoke.randomfilescopier.view.choosedirectory.box.ChooseDirectoryBox;

import java.util.Optional;

@Getter
public class ChooseDirectorySection {

    private final HBox chooseSourceBox;
    private final HBox chooseDestinationBox;

    public ChooseDirectorySection(final Stage stage) {
        final ChooseDirectoryBox sourceBox = new ChooseDirectoryBox(stage, Direction.SOURCE);
        this.chooseSourceBox = new HBox();
        chooseSourceBox.setPadding(new Insets(1));
        chooseSourceBox.setSpacing(2);
        chooseSourceBox.getChildren().addAll(sourceBox.get());
        chooseSourceBox.setAlignment(Pos.CENTER);

        final ChooseDirectoryBox destinationBox = new ChooseDirectoryBox(stage, Direction.DESTINATION);
        this.chooseDestinationBox = new HBox();
        chooseDestinationBox.setPadding(new Insets(1));
        chooseDestinationBox.setSpacing(2);
        chooseDestinationBox.getChildren().addAll(destinationBox.get());
        chooseDestinationBox.setAlignment(Pos.CENTER);
    }

    public Node[] get() {
        return new Node[]{this.chooseSourceBox, this.chooseDestinationBox};
    }

    public String getPathOf(final Direction direction) {
        if (Direction.SOURCE == direction) {
            return getPathOf(this.chooseSourceBox);
        }
        return getPathOf(this.chooseDestinationBox);

    }

    private String getPathOf(final HBox hBox) {
        final Optional<Node> textArea = hBox.getChildren().stream().filter(node -> node instanceof TextArea).findAny();
        if (textArea.isPresent()) {
            return ((TextArea) textArea.get()).getText();
        }
        return StringUtils.EMPTY;
    }
}
