package pl.gatoke.randomfilescopier.view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import pl.gatoke.randomfilescopier.utils.FilePicker;
import pl.gatoke.randomfilescopier.view.amount.SetAmountSection;
import pl.gatoke.randomfilescopier.view.choosedirectory.ChooseDirectorySection;
import pl.gatoke.randomfilescopier.view.choosedirectory.Direction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class MainScreen {

    private final Random random = new Random();
    private final ChooseDirectorySection chooseDirectorySection;
    private final SetAmountSection setAmountSection;
    private final Button copyButton;
    private final Label resultLabel;

    public MainScreen(final Stage stage) {
        this.setAmountSection = new SetAmountSection();
        this.chooseDirectorySection = new ChooseDirectorySection(stage);
        this.copyButton = createCopyButton();
        this.resultLabel = createResultLabel();
    }

    public Node[] get() {
        final List<Node> nodes = new ArrayList<>(Arrays.asList(chooseDirectorySection.get()));
        nodes.add(setAmountSection.get());
        nodes.add(copyButton);
        nodes.add(resultLabel);
        return nodes.toArray(new Node[0]);
    }

    private Button createCopyButton() {
        final Button button = new Button("COPY");
        button.getStyleClass().add("copy-button");
        button.setOnAction(event -> {
            final String sourcePath = chooseDirectorySection.getPathOf(Direction.SOURCE);
            final String destinationPath = chooseDirectorySection.getPathOf(Direction.DESTINATION);

            final File[] source = Paths.get(sourcePath).toFile().listFiles();
            if (source != null) {
                copyRandomFiles(source, Paths.get(destinationPath).toFile());
                setLabelOnSuccess();
            }
        });
        return button;
    }

    private void copyRandomFiles(final File[] source, final File destination) {
        IntStream.range(0, this.setAmountSection.getAmount()).forEach(time -> {
            final File fileToCopy = FilePicker.getFile(source[random.nextInt(source.length)]);
            if (fileToCopy != null) {
                try {
                    FileUtils.copyFileToDirectory(fileToCopy, destination);
                } catch (final IOException e) {
                    setLabelOnFailed();
                }
            }
        });
    }

    private Label createResultLabel() {
        final Label label = new Label();
        label.getStyleClass().add("result");
        return label;
    }

    private void setLabelOnSuccess() {
        this.resultLabel.setText("Success");
    }

    private void setLabelOnFailed() {
        this.resultLabel.setText("Failed");
    }

}
