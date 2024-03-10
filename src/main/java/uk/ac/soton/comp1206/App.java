package uk.ac.soton.comp1206;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
        TextField numInp = new TextField();

        Button submit = new Button("Submit");
        submit.setDisable(true);

        var root = new GridPane();
        root.add(numInp,0,0);
        root.add(submit,1,0);

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
        root.add(listView0,1,1);

        ListView<String> listView1 = new ListView<>();
        oList1 = FXCollections.observableArrayList();
        listView1.setItems(oList1);
        root.add(listView1,2,1);

        ListView<String> listView2 = new ListView<>();
        oList2 = FXCollections.observableArrayList();
        listView2.setItems(oList2);
        root.add(listView2,1,2);

        ListView<String> listView3 = new ListView<>();
        oList3 = FXCollections.observableArrayList();
        listView3.setItems(oList3);
        root.add(listView3,2,2);

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
                switch (count) {
                    case 0:
                        System.out.println("adding to oList0");
                        if(!oList0.contains(userStr)){
                            oList0.add(userStr);
                        }
                        else{
                            System.out.println("Already in List");
                        }
                        break;
                    case 1:
                        System.out.println("adding to oList1");
                        if(!oList1.contains(userStr)){
                            oList1.add(userStr);
                        }
                        else{
                            System.out.println("Already in List");
                        }
                        break;
                    case 2:
                        System.out.println("adding to oList2");
                        if(!oList2.contains(userStr)){
                            oList2.add(userStr);
                        }
                        else{
                            System.out.println("Already in List");
                        }
                        break;
                    case 3:
                        System.out.println("adding to oList3");
                        if(!oList3.contains(userStr)) {
                            oList3.add(userStr);
                        }
                        else{
                            System.out.println("Already in List");
                        }
                        break;
                }
            }
            numInp.clear();
        });

        var scene = new Scene(root, 800, 800);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm()); // style sheet to remove highlighting
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}