package uk.ac.soton.comp1206.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import uk.ac.soton.comp1206.App;

public class LaunchWindow {
  private final Scene scene;
  public LaunchWindow(App app) {
    var root = new VBox();
    this.scene = new Scene(root, 800, 800);
    Button begin = new Button("Begin");
    TextField input = new TextField();
    root.getChildren().addAll(new Label("Enter number"),input,begin);

//    input.textProperty().addListener((observable, oldValue, newValue) ->{
//      if (!newValue.matches("^(?!.*(.).*\\1)[1-9]{0,4}$")) {
//        input.setText(oldValue);
//        newValue = oldValue;
//      }
//      if(newValue.length() == 4){
//      }
//    });
  }

//  public int getUserInput(){
//    Scanner scanner = new Scanner(System.in);
//    System.out.print("Custom value?(Y/N)\n");
//
//    if(scanner.next().equalsIgnoreCase("Y")) {
//      System.out.print("Please enter choice value:\n");
//      System.out.print("Restraints: unique digits in range 1 to 9\n");
//      String choiceVal = scanner.next();
//
//      while(!choiceVal.matches("^(?!.*(.).*\\1)[1-9]{0,4}$")){ // check choice input meets rules
//        System.out.print("Please enter value matching restraints:\n");
//        System.out.print("Restraints: unique digits in range 1 to 9\n");
//        choiceVal = scanner.next();
//      }
//      return Integer.parseInt(choiceVal);
//    }
//
//    System.out.print("Default value in use.\n");
//    return savedVal; // use the system value
//  }

  public Scene getScene() {
    return scene;
  }
}
