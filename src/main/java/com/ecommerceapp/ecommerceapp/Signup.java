package com.ecommerceapp.ecommerceapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.tools.Platform;

import java.net.URL;
import java.util.ResourceBundle;

public class Signup  implements Initializable {

    @FXML
    private Button button_register;

    @FXML
    private Label ftreload;

    @FXML
    private  Button button_login;

    @FXML
    private Button button_close;

    @FXML
    private TextField txName;

    @FXML
    private TextField txUsername;

    @FXML
    private TextField txEmail;

    @FXML
    private TextField txPass;
    @FXML
    private TextField txConPass;

    public Signup() {

    }

    public Signup(Button buttonregister) {
        button_register = buttonregister;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        button_register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!txName.getText().trim().isEmpty() && !txUsername.getText().trim().isEmpty() && !txEmail.getText().trim().isEmpty() && !txPass.getText().trim().isEmpty()) {
                    try {
                        DButils.signUpUser(event, txName.getText(),txUsername.getText(), txEmail.getText(), txPass.getText());
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    System.out.println("please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up!");
                    alert.show();
                }
            }
        });

            button_login.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    DButils.changeScene(event, "Loginform.fxml", "Loginform", null);
                }
            });

            button_close.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage stage = (Stage) button_close.getScene().getWindow();
                    stage.close();

                }
            });

    }
}
