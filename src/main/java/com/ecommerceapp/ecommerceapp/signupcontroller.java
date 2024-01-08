package com.ecommerceapp.ecommerceapp;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class signupcontroller implements Initializable {

    @FXML
    private Button button_sigup;

    @FXML
    private Button loginbtn;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_password;

    public signupcontroller(Button buttonSigup) {
        button_sigup = buttonSigup;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        button_sigup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!tf_name.getText().trim().isEmpty() && !tf_username.getText().trim().isEmpty() && !tf_email.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()) {
                    DButils.signUpUser(event, tf_name.getText(),tf_username.getText(), tf_email.getText(), tf_password.getText());
                }else {
                    System.out.println("please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up!");
                    alert.show();
                }
            }
        });

        loginbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DButils.changeScene(event, "login.fxml", "Login", "null");
            }
        });

    }
}
