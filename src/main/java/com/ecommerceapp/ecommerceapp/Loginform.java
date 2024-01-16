package com.ecommerceapp.ecommerceapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Loginform implements Initializable {

    @FXML
    private Button button_Login;

    @FXML
    private TextField thEmail;

    @FXML
    private TextField thPassword;

    @FXML
    private Button button_Register;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        button_Login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    DButils.logInUser(event, thEmail.getText(), thPassword.getText());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        button_Register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DButils.changeScene(event, "Signup.fxml", "Signup", null);
            }
        });

    }
}
