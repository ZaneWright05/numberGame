package uk.ac.soton.comp1206.ui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NumInpField extends TextField {
  private static final Logger logger = LogManager.getLogger(NumInpField.class);
  // unique text field that takes only valid inputs
  public NumInpField(Button submit){
    this.textProperty().addListener((observable, oldValue, newValue) ->{
      submit.setDisable(true);
      if (!newValue.matches("^(?!.*(.).*\\1)[1-9]{0,4}$")) {
        this.setText(oldValue);
        newValue = oldValue;
        logger.info("Invalid Input - unique digits [1-9]");
      }
      if(newValue.length() == 4){
        submit.setDisable(false);
        logger.info("Valid input given");
      }
    });
  }


}
