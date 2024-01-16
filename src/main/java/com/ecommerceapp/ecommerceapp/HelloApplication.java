package com.ecommerceapp.ecommerceapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start( Stage stage) throws IOException {
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Signup.fxml" ));
        Scene scene = new Scene(fxmlLoader.load(), 400, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}