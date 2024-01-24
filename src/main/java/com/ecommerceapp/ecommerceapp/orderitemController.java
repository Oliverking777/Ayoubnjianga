package com.ecommerceapp.ecommerceapp;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import java.sql.Connection;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.Optional;

import static com.ecommerceapp.ecommerceapp.Data.cID;
import static sun.security.pkcs11.wrapper.Functions.getId;

public class orderitemController {



    @FXML
    private Button menu_pay;

    @FXML
    private TableColumn<?, ?> menu_price;

    @FXML
    private TableColumn<?, ?> menu_productname;

    @FXML
    private TableColumn<?, ?> menu_quantity;

    @FXML
    private TextField menu_subtotal;

    @FXML
    private Label menu_total;

    @FXML
    private TableView<productData> menu_tabel;

    @FXML
    private Button menu_remove;

    private Alert alert;
    @FXML
    private Button menu_clear;

    private Connection connect;
    private ResultSet result;
    private PreparedStatement prepare;


    public ObservableList<productData> menuGetOrder() {
        customerID();
        ObservableList<productData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM customer WHERE customer_id = " + cID;

        connect = database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            productData prod;

            while (result.next()) {
                prod = new productData(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("type"),
                        result.getInt("quantity"),
                        result.getDouble("price"),
                        result.getString("image"),
                        result.getDate("date"));
                listData.add(prod);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private void customerID() {

    }

    private ObservableList<productData> menuOrderListData;


    public void menuShowOrderData() {
        menuOrderListData = menuGetOrder();

        menu_productname.setCellValueFactory(new PropertyValueFactory<>("PRODUCT NAME"));
        menu_quantity.setCellValueFactory(new PropertyValueFactory<>("QUANTITY"));
        menu_price.setCellValueFactory(new PropertyValueFactory<>("PRICE (FCFA)"));

        menu_tabel.setItems(menuOrderListData);
    }
    public void menuSelectOrder() {
        productData prod = menu_tabel.getSelectionModel().getSelectedItem();
        int num = menu_tabel.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        // TO GET THE ID PER ORDER
        Object getId = prod.getId();


    }

    private double totalP;

    public void getSubtotal() {
        customerID();
        String total = "SELECT SUM(price) FROM customer WHERE customer_id = " + cID;

        connect = database.connectDB();

        try {

            prepare = connect.prepareStatement(total);
            result = prepare.executeQuery();

            if (result.next()) {
                totalP = result.getDouble("SUM(price)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DisplaySubTotal() {
        getSubtotal();
        menu_total.setText("FCFA" + totalP);
    }


    public void menuPayBtn(){

    }

    public void menuRemoveBtn() {

        int getId = 0;
        if (getId == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the order you want to remove");
            alert.showAndWait();
        } else {
            String deleteData = "DELETE FROM customer WHERE id = " + getId;
            connect = database.connectDB();
            try {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete this order?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.executeUpdate();
                }

                menuShowOrderData();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void clearorder() {
        totalP = 0;
        menu_total.setText("FCFA0.0");


    }
}
