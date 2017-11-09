import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Gui extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = Element.createStage(500, 400);
        primaryStage.setTitle("HDMY Converter(R) by Andrei Tolkachev");
        Pane root = Element.createPane(400,500);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Gui.class.getResource("Login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        Label inHours = Element.addLabel(" :hours", 170, 140);

        Label hoursLabel = Element.addLabel("--", 90, 140);
        hoursLabel.setId("hoursLabel");

        Label inDays = Element.addLabel(" :days", 170, 160);

        Label daysLabel = Element.addLabel("--", 90, 160);
        daysLabel.setId("daysLabel");

        Label inMonths = Element.addLabel(" :months", 170, 180);

        Label monthsLabel = Element.addLabel("--", 90, 180);
        monthsLabel.setId("monthsLabel");

        Label inYears = Element.addLabel(" :years", 170, 200);

        Label yearsLabel = Element.addLabel("--", 90, 200);
        yearsLabel.setId("yearsLabel");

        Tooltip errorText = new Tooltip("Please! Enter a numeric value");
        errorText.setId("errorText");

        Label vers = Element.addLabel("version 0.2.6", 400, 330);
        vers.setId("versLabel");

        TextField inputed = new Element.LimitedTextField(18, 90, 100, 250);
        inputed.setPromptText("enter a number of minutes");
        inputed.setOnKeyReleased(event -> {
            try {
                Converter.inputString(inputed);
                hoursLabel.setText(Converter.toHours());
                daysLabel.setText(Converter.toDays());
                monthsLabel.setText(Converter.toMonths());
                yearsLabel.setText(Converter.toYears());
            } catch (NumberFormatException e) {
                if (!inputed.getText().isEmpty()) {
                    inputed.setTooltip(errorText);
                    inputed.clear();
                    hoursLabel.setText("--");
                    daysLabel.setText("--");
                    monthsLabel.setText("--");
                    yearsLabel.setText("--");
                }
            }
        });

        Button btn = Element.addButton("CLEAR!", 350, 101);
        btn.setOnAction((ActionEvent event) -> {
            inputed.clear();
            hoursLabel.setText("--");
            daysLabel.setText("--");
            monthsLabel.setText("--");
            yearsLabel.setText("--");
        });

        root.getChildren().addAll(btn, inputed, inHours, hoursLabel, inDays, daysLabel, inMonths,
                monthsLabel, inYears, yearsLabel, vers);
        root.setOnMouseClicked(event -> inputed.setPromptText("click on this text field"));
    }
}

