package com.ecommerceapp.ecommerceapp;
import java.sql.Date;




    public class customersData {

        private Integer Id;
        private Integer customerID;
        private Double total;
        private Date date;
        private String emUsername;

        public customersData(Integer Id, Integer customerID, Double total,
                             Date date, String emUsername) {
            this.Id = Id;
            this.customerID = customerID;
            this.total = total;
            this.date = date;
            this.emUsername = emUsername;
        }

        public Integer getId() {
            return Id;
        }

        public Integer getCustomerID() {
            return customerID;
        }

        public Double getTotal() {
            return total;
        }

        public Date getDate() {
            return date;
        }

        public String getEmUsername() {
            return emUsername;
        }


    }
