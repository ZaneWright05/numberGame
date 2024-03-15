package uk.ac.soton.comp1206.ui;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import uk.ac.soton.comp1206.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class GameWindow {


  private static final Logger logger = LogManager.getLogger(GameWindow.class);
  private final Scene scene;
  private final int savedVal = 1234;

  private final ObservableList<String> oList0;
  private final ObservableList<String> oList1;
  private final ObservableList<String> oList2;
  private final ObservableList<String> oList3;
  private final String[][] arrOfArr;

  public GameWindow(App app){
    var root = new BorderPane();
    this.scene = new Scene(root, 800, 800);

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
    Button submit = new Button("Submit");
    TextField numInp = new NumInpField(submit); // create personal made input
    //-> has event listener for my input and if correct input given will enable the parsed in button
    submit.setDisable(true);

    // instantiate 2d arr for unused values
    arrOfArr = new String[9][4];
    for (int row = 0; row < 9; row++){
      for (int col = 0; col < 4; col++) {
        arrOfArr[row][col] = String.valueOf(row+1);
      }
    }

    // create text for chosen val display
    String arrayAsStr = arr2dAsString(9, arrOfArr);
    Text text = new Text(arrayAsStr);
    TextFlow textFlow = new TextFlow(text);

    // add els to left side
    leftPane.getChildren().addAll(numInp,submit,textFlow);

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
        System.out.println("Match!!!");
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
            updateArr(userStr);
            text.setText(arr2dAsString(9,arrOfArr));
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

    // fix split plane
    splitPane.setDividerPositions(0.334);
    leftPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.330));
    rightPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.660));


    root.setStyle("-fx-background-color: grey;");
    root.setCenter(splitPane);
    scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm()); // style sheet to remove highlighting

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

  // convert a 2d array into values
  public String arr2dAsString(int rows, String[][] array){
    String out = "";
    for (int row = 0; row < rows; row++){
      String[] tempArr = array[row];
      out = out + String.format(String.join(" ", tempArr) + "\n");
    }
    return out;
  }

  // version of updateArr for when no matching els are found, finds location and replaces with /
  public void updateArr(String usrStr){
    // called when no matching values given only
    for (int col = 0; col < 4; col++) {
      int curVal = Character.getNumericValue(usrStr.charAt(col));
      //System.out.println(curVal);
      arrOfArr[curVal-1][col] = "X";
    }
  }

  public Scene getScene() {
    return scene;
  }
}

