package com.ecommerceapp.ecommerceapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class  Registration extends JDialog {
    private JTextField tfName;
    private JTextField tfUsername;
    private JTextField tfEmail;
    private JTextField tfPassword;
    private JTextField tfConfirmPass;
    private JButton signupButton;
    private JButton clearButton;
    private JPanel SignupPanel;

    public  Registration(JFrame parent){
        super((parent));
        setTitle("create a new account");
        setContentPane(SignupPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();;
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);


    }

    private void registerUser() {
        String name = tfName.getText();
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String password = tfPassword.getText();
        String confirmPassword = tfConfirmPass.getText();


        if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        User addUserToDatabase = addUserToDatabase(name, username, email, password);
        if(user != null) {
            dispose();
        }else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }


    }

    public User user;
    private User addUserToDatabase(String name, String username, String email, String password) {
        User user = null;
        final String DB_URL = "http://localhost/phpmyadmin/index.php?route=/sql&db=javafx_ecom&table=users&pos=0";
        final String USERNAME = "root";
        final String PASSWORD  = "";

        try {
            Connection conn;
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO users (name, username, eamil, password)" + "VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);


            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.name = name;
                user.username = username;
                user.email = email;
                user.password = password;
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public  static  void  main(String[] args) {
        Registration myForm = new Registration(null);
        User user = myForm.user;
        if (user != null) {
            System.out.println("Successful registration of:" + user.name);

        }else {
            System.out.println("Registration cancelled");
        }
    }
}
