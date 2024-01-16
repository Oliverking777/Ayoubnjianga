package com.ecommerceapp.ecommerceapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DButils {

    public static <parent extends Parent, stage> void  changeScene(ActionEvent event, String fxmlfile, String title, String username){
        parent root = null;

        if (username != null){
            try {
                FXMLLoader loader = new FXMLLoader(DButils.class.getResource(fxmlfile));
                root = loader.load();
                Product product = loader.getController();
                product.setUserinformation(username);
                /*Logincontroller logincontroller = loader.getController();
                logincontroller.setUserinformation(username);*/
            }catch (IOException e){
                e.printStackTrace();
            }
        }else {
            try{
                root = FXMLLoader.load(DButils.class.getResource(fxmlfile));
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene((Parent) root, 600, 400));
        stage.show();;
    }

    public  static void signUpUser(ActionEvent event, String name, String username, String email, String password, String txConPass ) throws ClassNotFoundException {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        if (!password.equals(txConPass)) {
            System.out.println("Passwords do no match!");
            Alert alert = new Alert((Alert.AlertType.ERROR) );
            alert.show();
            return;
        }

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_ecom", "root","");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE  name = ?");
            psCheckUserExists.setString(1, name);
             resultSet = psCheckUserExists.executeQuery();

             if(resultSet.isBeforeFirst()) {
                 System.out.println("user already exist!");
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setContentText("you cannot use this name");
                 alert.show();
             }

             else {
                 psInsert = connection.prepareStatement("INSERT INTO users (name, username, email, password) VALUES (?, ?, ?, ?)");
                 psInsert.setString(1, name);
                 psInsert.setString(2, username);
                 psInsert.setString(3, email);
                 psInsert.setString(4, password);
                 psInsert.executeUpdate();

                 changeScene(event, "product.fxml", "product", name);
             }


        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null){
                try {
                    psCheckUserExists.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }
    public static  void logInUser(ActionEvent event, String email, String password) throws ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_ecom", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE  email = ? ");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in the database !");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            }else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");

                    if (password.equals(retrievedPassword)) {
                        changeScene(event, "product.fxml", "product", email);

                    }else {
                        System.out.println("Password did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (resultSet != null) {
                    resultSet.close();

            }
                if (preparedStatement != null) {
                    preparedStatement.close();

                }
                if (connection != null) {
                    connection.close();
                }

            }catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

}

