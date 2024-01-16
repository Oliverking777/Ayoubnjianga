package com.ecommerceapp.ecommerceapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login  extends JDialog{
    private JTextField thEmail;
    private JPasswordField thPassword;
    private JButton loginButton;
    private JButton cancelButton;
    private JPanel LoginPanel;


    public  Login(JFrame parent){
        super((parent));
        setTitle("logged into account");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = thEmail.getText();
                String password = String.valueOf(thPassword.getPassword());

                try {
                    user = getAuthenticatedUser(email,password);

                    if (user != null) {
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(Login.this,
                                "Email or Password Invalid",
                                "Try again",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }



            }

        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public User user;
    private User getAuthenticatedUser(String email, String password) throws ClassNotFoundException {
        User user = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        final String DB_URL = "jdbc:mysql://localhost:3306/javafx_ecom?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD  = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.name = resultSet.getString("name");
                user.username = resultSet.getString("username");
                user.email = resultSet.getString("email");
                user.password = resultSet.getString("password");


            }
        }catch (Exception e) {
            e.printStackTrace();
        }


        return user;
    }

    public static void  main(String[] args) {
        Login loginform = new Login(null);
        User user = loginform.user;
        if (user != null) {
            System.out.println("Successful Authentification of:" + user.name);
            System.out.println("           Email:" + user.email);

        }else {
            System.out.println("Authentication cancelled");
        }
    }
    }