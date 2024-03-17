package uk.ac.soton.comp1206;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.ac.soton.comp1206.ui.GameWindow;
import uk.ac.soton.comp1206.ui.LaunchWindow;

public class App extends Application {
    private Stage stage;
    private int savedVal = 1234;

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        launchStart();
        //launchMain();
    }
    public static void main(String[] args) {
        launch();
    }

    public void launchStart() {
        var window = new LaunchWindow(this);
        stage.setScene(window.getScene());
        stage.show();
        stage.centerOnScreen();
    }

    public void launchMain(){
        var window = new GameWindow(this);
        stage.setScene(window.getScene());
        stage.show();
        stage.centerOnScreen();
    }

    public void setSavedVal(int savedVal) {
        this.savedVal = savedVal;
    }

    public int getSavedVal() {
        return savedVal;
    }
}
