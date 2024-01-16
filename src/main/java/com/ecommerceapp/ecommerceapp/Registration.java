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
                try {
                    registerUser();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                ;
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

    private void registerUser() throws ClassNotFoundException {
        String name = tfName.getText();
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String password = String.valueOf(tfPassword.getText());
        String confirmPassword = String.valueOf(tfConfirmPass.getText());


        if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Confirm Password do not macth",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }


        User user = addUserToDatabase(name, username, email, password);
        if(user != null) {
            this.user = user;
            dispose();
        }else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }


    }

    public User user;
    private User addUserToDatabase(String name, String username, String email, String password) throws ClassNotFoundException {
        User user = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        final String DB_URL = "jdbc:mysql://localhost:3306/javafx_ecom?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD  = "";

        try {
            Connection conn;
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO users (name, username, email, password)" + "VALUES(?, ?, ?, ?)";
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
