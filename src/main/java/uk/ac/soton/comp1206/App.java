package uk.ac.soton.comp1206;

import java.util.Scanner;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    private int savedVal = 1234;

    // arrays for storing attempted numbers
    private ObservableList<String> oList0;
    private ObservableList<String> oList1;
    private ObservableList<String> oList2;
    private ObservableList<String> oList3;

    public void start(Stage stage) {
        savedVal = getUserInput();

        //basic struct elements
        SplitPane splitPane = new SplitPane();
        VBox leftPane = new VBox();
        HBox rightPane = new HBox();
        VBox rightB1 = new VBox();
        VBox rightB2 = new VBox();

        //defining v&h box properties
        HBox.setHgrow(rightB1, Priority.ALWAYS);
        HBox.setHgrow(rightB2, Priority.ALWAYS);
        rightB1.setSpacing(5);
        rightB2.setSpacing(5);
        rightPane.setSpacing(5);

        // add els to v box
        rightPane.getChildren().addAll(rightB1,rightB2);

        // create left side elements
        TextField numInp = new TextField();
        Button submit = new Button("Submit");
        submit.setDisable(true);

        // add els to left side
        leftPane.getChildren().addAll(numInp,submit);

        numInp.textProperty().addListener((observable, oldValue, newValue) ->{
            submit.setDisable(true);
            if (!newValue.matches("^(?!.*(.).*\\1)[1-9]{0,4}$")) {
                numInp.setText(oldValue);
                newValue = oldValue;
            }
            if(newValue.length() == 4){
                submit.setDisable(false);
            }
        });

        // create the lists
        ListView<String> listView0 = new ListView<>();
        oList0 = FXCollections.observableArrayList();
        listView0.setItems(oList0);

        ListView<String> listView1 = new ListView<>();
        oList1 = FXCollections.observableArrayList();
        listView1.setItems(oList1);

        ListView<String> listView2 = new ListView<>();
        oList2 = FXCollections.observableArrayList();
        listView2.setItems(oList2);

        ListView<String> listView3 = new ListView<>();
        oList3 = FXCollections.observableArrayList();
        listView3.setItems(oList3);

        // add lists and labels to given h box
        rightB1.getChildren().addAll(new Label(" 0 matching"),listView0,new Label(" 2 matching"),listView2);
        rightB2.getChildren().addAll(new Label(" 1 matching"),listView1,new Label(" 3 matching"),listView3);

        submit.setOnAction(actionEvent -> { // what happens when button is pressed
            StringProperty stringProperty = numInp.textProperty();
            String userStr = stringProperty.get();
            userStr= userStr.trim();
            int userVal = Integer.parseInt(userStr); // formatting the user input
            if(savedVal ==  userVal){
                System.out.println("match!!!");
            }
            else {
                String ansStr = String.valueOf(savedVal); // finding all matching characters
                int count = 0;
                for (int i = 0; i < 4; i++) {
                    if(ansStr.charAt(i) == userStr.charAt(i)){
                        count++;
                    }
                }
                System.out.printf("there are %d matching characters\n", count);
                switch (count) { // add to relevant display list
                    case 0:
                        checkSameAnswer(oList0,userStr);
                        break;
                    case 1:
                        checkSameAnswer(oList1,userStr);
                        break;
                    case 2:
                        checkSameAnswer(oList2,userStr);
                        break;
                    case 3:
                        checkSameAnswer(oList3,userStr);
                        break;
                }
            }
            numInp.clear();
        });

        splitPane.getItems().addAll(leftPane, rightPane);
        splitPane.setDividerPositions(0.334);
        leftPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.330));
        rightPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.660));
        //splitPane.setResizableWithParent(rightPane, Boolean.FALSE);

        var root = new BorderPane();
        root.setStyle("-fx-background-color: grey;");
        root.setCenter(splitPane);

        var scene = new Scene(root, 800, 800);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm()); // style sheet to remove highlighting
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public int getUserInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Custom value?(Y/N)\n");
        if(scanner.next().equalsIgnoreCase("Y")) {
            System.out.print("Please enter choice value:\n");
            System.out.print("Restraints: unique digits in range 1 to 9\n");
            String choiceVal = scanner.next();
            while(!choiceVal.matches("^(?!.*(.).*\\1)[1-9]{0,4}$")){ // check choice input meets rules
                System.out.print("Please enter value matching restraints:\n");
                System.out.print("Restraints: unique digits in range 1 to 9\n");
                choiceVal = scanner.next();
            }
            return Integer.parseInt(choiceVal);
        }
        System.out.print("Default value in use.\n");
        return savedVal; // use the system value
    }

    public void checkSameAnswer(ObservableList<String> oList, String userInp){
        // checks if given oList contains given value avoids multiple guesses of same number
        if(oList.contains(userInp)){
            System.out.println("Already in List");
        }
        else{
            oList.add(userInp);
        }
    }

}