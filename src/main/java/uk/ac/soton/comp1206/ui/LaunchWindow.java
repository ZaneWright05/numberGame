package uk.ac.soton.comp1206.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ac.soton.comp1206.App;

public class LaunchWindow {
  private static final Logger logger = LogManager.getLogger(LaunchWindow.class);
  private final Scene scene;
  public LaunchWindow(App app) {
    var root = new VBox();
    this.scene = new Scene(root, 800, 800);
    Button begin = new Button("Begin");
    begin.setDisable(true);
    TextField input = new NumInpField(begin);
    root.getChildren().addAll(new Label("Enter number - unique digits [1-9]"),input,begin);

    begin.setOnAction(actionEvent -> {
      app.setSavedVal(Integer.parseInt(input.getText()));
      app.launchMain();
    });
  }

  public Scene getScene() {
    return scene;
  }

}
