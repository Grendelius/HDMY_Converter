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
        // Создание формы / панели, создание кнопки
        primaryStage.setTitle("HDMY Converter(R) by Andrei Tolkachev"); //Заголовок окна
        primaryStage.setWidth(500); // Ширина окна
        primaryStage.setHeight(400); // Высота окна

        Pane root = new Pane(); // Создаем новый объект панели
        root.setPrefSize(400, 500);
        root.setPadding(new Insets(10, 10, 10, 10));

        Label inHours = addLabel(" :hours", 170, 140);

        Label hoursLabel = addLabel("--", 90, 140); // Числовые значения часа
        hoursLabel.setId("hoursLabel");

        Label inDays = addLabel(" :days", 170, 160);

        Label daysLabel = addLabel("--", 90, 160); // Числовые значения дней
        daysLabel.setId("daysLabel");

        Label inMonths = addLabel(" :months", 170, 180);

        Label monthsLabel = addLabel("--", 90, 180); // Числовые значения месяцев
        monthsLabel.setId("monthsLabel");

        Label inYears = addLabel(" :years", 170, 200);

        Label yearsLabel = addLabel("--", 90, 200); // Числовые значения лет
        yearsLabel.setId("yearsLabel");

        Tooltip errorText = new Tooltip("Please! Enter a numeric value"); // Error text message
        errorText.setId("errorText");

        Label vers = addLabel("version 0.2.6", 400, 330);
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

        Button btn = addButton("CLEAR!", 350, 101); // Создаем новый объект кнопки
        btn.setOnAction((ActionEvent event) -> {
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

    /**
     * Create a button
     *
     * @param name
     * @return
     */
    private Button addButton(String name, int x, int y) {
        Button btn = new Button(name);
        btn.setPrefSize(100, 20);
        btn.setTranslateX(x);
        btn.setTranslateY(y);
        return btn;
    }

    /**
     * Create a label
     *
     * @param name
     * @return
     */
    private Label addLabel(String name, int x, int y) {
        Label label = new Label(name);
        label.setPrefSize(180, 50);
        label.setTranslateX(x);
        label.setTranslateY(y);
        return label;
    }

    /**
     * Limited text field class
     */
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
}

