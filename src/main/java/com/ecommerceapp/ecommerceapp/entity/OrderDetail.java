package com.ecommerceapp.ecommerceapp.entity;

import com.ecommerceapp.ecommerceapp.NotNull;


public class OrderDetail {


    private  Integer OrderId;
    private String  OrderFullName;
    private  String OrderFullOrder;
    private  String OrderContact;
    private  String OrderAlternateContact;
    private String OrderStatus;
    private Integer OrderAmount;

    public OrderDetail(String orderFullName, String orderFullOrder, String orderContact, String orderAlternateContact, String orderStatus, Integer orderAmount) {
        OrderFullName = orderFullName;
        OrderFullOrder = orderFullOrder;
        OrderContact = orderContact;
        OrderAlternateContact = orderAlternateContact;
        OrderStatus = orderStatus;
        OrderAmount = orderAmount;
    }

    public Integer getOrderId() {
        return OrderId;
    }

    public void setOrderId(Integer orderId) {
        OrderId = orderId;
    }

    public String getOrderFullName() {
        return OrderFullName;
    }

    public void setOrderFullName(String orderFullName) {
        OrderFullName = orderFullName;
    }

    public String getOrderFullOrder() {
        return OrderFullOrder;
    }

    public void setOrderFullOrder(String orderFullOrder) {
        OrderFullOrder = orderFullOrder;
    }

    public String getOrderContact() {
        return OrderContact;
    }

    public void setOrderContact(String orderContact) {
        OrderContact = orderContact;
    }

    public String getOrderAlternateContact() {
        return OrderAlternateContact;
    }

    public void setOrderAlternateContact(String orderAlternateContact) {
        OrderAlternateContact = orderAlternateContact;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public Integer getOrderAmount() {
        return OrderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        OrderAmount = orderAmount;
    }
}
