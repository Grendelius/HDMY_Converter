import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Gui extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Создание формы / панели, создание кнопки
        primaryStage.setTitle("HDMY Converter(R) by Andrei Tolkachev"); //Заголовок окна
        primaryStage.setWidth(500); // Ширина окна
        primaryStage.setHeight(400); // Высота окна

        Pane root = new Pane(); // Создаем новый объект панели
        root.setPrefSize(400, 500);
        root.setPadding(new Insets(10, 10, 10, 10));

        Label inHours = addLabel(" :hours");
        inHours.setTranslateX(170);
        inHours.setTranslateY(140);

        Label hoursLabel = addLabel("--"); // Числовые значения часа
        hoursLabel.setTranslateX(90);
        hoursLabel.setTranslateY(140);
        hoursLabel.setId("hoursLabel");

        Label inDays = addLabel(" :days");
        inDays.setTranslateX(170);
        inDays.setTranslateY(160);

        Label daysLabel = addLabel("--"); // Числовые значения дней
        daysLabel.setTranslateX(90);
        daysLabel.setTranslateY(160);
        daysLabel.setId("daysLabel");

        Label inMonths = addLabel(" :months");
        inMonths.setTranslateX(170);
        inMonths.setTranslateY(180);

        Label monthsLabel = addLabel("--"); // Числовые значения месяцев
        monthsLabel.setTranslateX(90);
        monthsLabel.setTranslateY(180);
        monthsLabel.setId("monthsLabel");

        Label inYears = addLabel(" :years");
        inYears.setTranslateX(170);
        inYears.setTranslateY(200);

        Label yearsLabel = addLabel("--"); // Числовые значения лет
        yearsLabel.setTranslateX(90);
        yearsLabel.setTranslateY(200);
        yearsLabel.setId("yearsLabel");

        Tooltip errorText = new Tooltip("Please! Enter a numeric value"); // Error text message
        errorText.setId("errorText");

        Label vers = addLabel("version 0.2.6");
        vers.setTranslateX(400);
        vers.setTranslateY(330);
        vers.setPrefSize(60, 15);
        vers.setId("versLabel");

        TextField inputed = new LimitedTextField(18); // Поле для ввода числовых значений
        inputed.setPromptText("enter a number of minutes");
        inputed.setPrefWidth(250);
        inputed.setTranslateX(90);
        inputed.setTranslateY(100);
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

        Button btn = addButton("CLEAR IT!"); // Создаем новый объект кнопки
        btn.setTranslateX(350); // Задаем координаты по X
        btn.setTranslateY(101); // Задаем координаты по Y
        btn.setOnAction(event -> {
            inputed.clear();
            hoursLabel.setText("--");
            daysLabel.setText("--");
            monthsLabel.setText("--");
            yearsLabel.setText("--");
        });

        root.getChildren()
                .addAll(btn, inputed, inHours, hoursLabel, inDays, daysLabel, inMonths, monthsLabel, inYears, yearsLabel, vers);
        root.setOnMouseClicked(event -> inputed.setPromptText("click on this text field"));

        Scene scene = new Scene(root); // Создаем объект сцены(действий на граф. объекте)
        primaryStage.setScene(scene); // устанавливаем в головоной контейнер интерфейса scene
        scene.getStylesheets().add(Gui.class.getResource("Login.css").toExternalForm());

        primaryStage.show(); // Показать всё
    }

    // Create a button
    private Button addButton(String name) {
        Button btn = new Button(name);
        btn.setPrefSize(100, 20);
        return btn;
    }

    private Label addLabel(String name) {
        Label label = new Label(name);
        label.setPrefSize(180, 50);
        return label;
    }

    class LimitedTextField extends TextField {
        private final int limit;

        LimitedTextField(int limit) {
            this.limit = limit;
        }

        @Override
        public void replaceText(int start, int end, String text) {
            super.replaceText(start, end, text);
            verify();
        }

        @Override
        public void replaceSelection(String text) {
            super.replaceSelection(text);
            verify();
        }

        private void verify() {
            if (getText().length() > limit) {
                setText(getText().substring(0, limit));
            }

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

