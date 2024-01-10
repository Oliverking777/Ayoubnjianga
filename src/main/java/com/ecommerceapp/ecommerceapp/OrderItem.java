
package com.ecommerceapp.ecommerceapp;
public class OrderItem {

    private int orderID;
    private Product product;
    private int quantity;
    private double subtotal;

    public OrderItem(Product product,int quantity,int orderID){

        this.quantity=quantity;
        this.orderID=orderID;
        this.product=product;
        updateSubtotal();

    }

    public int getOrderID() {
        return orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void updateSubtotal(){
        subtotal=product.getPrice() * quantity;
    }
}
