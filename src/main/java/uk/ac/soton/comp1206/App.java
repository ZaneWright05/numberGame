package uk.ac.soton.comp1206;

import javafx.application.Application;
import javafx.stage.Stage;
import uk.ac.soton.comp1206.ui.GameWindow;

public class App extends Application {
    private Stage stage;

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        launchApp();
    }
    public static void main(String[] args) {
        launch();
    }

    public void launchApp(){
        var window = new GameWindow(this);
        stage.setScene(window.getScene());
        stage.show();
        stage.centerOnScreen();
    }
}
