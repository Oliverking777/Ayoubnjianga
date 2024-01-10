package com.ecommerceapp.ecommerceapp.Controller;


import com.ecommerceapp.ecommerceapp.OrderItem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class OrderDetailController {
   @FXML
    private Label orderID;
   @FXML
    private  Label Productname;
   @FXML
    private TextField  Quantity;
   @FXML
    private Label subtotal;

   private OrderItem orderItem;

    public void initialize() {
        // Initialize the UI components and set values based on the OrderItem
        orderID.setText("Order ID: " + orderItem.getOrderID());
        Productname.setText("Product: " + orderItem.getProduct().getName());
        Quantity.setText(String.valueOf(orderItem.getQuantity()));
        subtotal.setText("Subtotal: $" + orderItem.getSubtotal());
    }

    @FXML
    private void updateOrderItem() {
        // Handle updating the OrderItem based on user input
        int newQuantity = Integer.parseInt(Quantity.getText());
        orderItem.setQuantity(newQuantity);
        updateSubtotal();
    }

    private void updateSubtotalLabel() {
        subtotal.setText("Subtotal: $" + orderItem.getSubtotal());
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
        initialize(); // Initialize UI components when setting the OrderItem
    }



}
