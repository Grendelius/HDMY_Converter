import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

abstract class Element {
    /**
     * Create a button
     *
     * @param name
     * @return
     */
    protected static Button addButton(String name, int x, int y) {
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
    protected static Label addLabel(String name, int x, int y) {
        Label label = new Label(name);
        label.setPrefSize(180, 50);
        label.setTranslateX(x);
        label.setTranslateY(y);
        return label;
    }

    static Pane createPane(int width, int height) {
        Pane pane = new Pane();
        pane.setPrefSize(width, height);
        return pane;
    }

    static Stage createStage(double width, double height) {
        Stage stage = new Stage();
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setResizable(false);
        return stage;
    }

    /**
     * Limited text field class
     */
    static class LimitedTextField extends TextField {
        private final int limit;

        LimitedTextField(int limit, int x, int y, int width) {
            setPrefWidth(width);
            setTranslateX(x);
            setTranslateY(y);
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
