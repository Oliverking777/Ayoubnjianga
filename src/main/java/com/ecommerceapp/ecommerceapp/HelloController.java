package com.ecommerceapp.ecommerceapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    private void displayTotal(){

        String total=" SELECT COUNT(price) FROM customer WHERE customer_id=" + cID;

        

    }

    private int cID;
}