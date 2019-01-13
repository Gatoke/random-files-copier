package pl.gatoke.randomfilescopier.view.amount;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.apache.commons.lang3.StringUtils;

public class SetAmountSection {

    private static final int DEFAULT_AMOUNT = 10;

    private HBox amountSectionBox;
    private TextField textField;

    public SetAmountSection() {
        this.amountSectionBox = new HBox();
        final Label label = new Label("Select amount of random files to copy:");
        label.getStyleClass().add("files-to-copy-label");
        this.textField = createTextField();
        amountSectionBox.getChildren().addAll(label, this.textField);
        amountSectionBox.getStyleClass().add("amount-section");
    }

    public Node get() {
        return this.amountSectionBox;
    }

    private TextField createTextField() {
        final TextField textField = new TextField();
        textField.setText(String.valueOf(DEFAULT_AMOUNT));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", StringUtils.EMPTY));
            }
        });
        return textField;
    }

    public int getAmount() {
        if (StringUtils.isBlank(this.textField.getText())) {
            return DEFAULT_AMOUNT;
        }
        return Integer.parseInt(this.textField.getText());
    }
}
