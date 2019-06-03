package com.william.fullbankingapplicationfinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "CUSTOMER_ID")
    private Long customer_id;

    @Column(name = "CUSTOMER_FIRST_NAME")
    private String first_name;

    @Column(name = "CUSTOMER_LAST_NAME")
    private String last_name;

    private String username;

    private String password;

//    @JoinColumn(name = "CUSTOMER_ADDRESSES")
//    @ManyToOne(cascade = CascadeType.ALL)
//    private Address address;


    public Customer() {

    }

    public Customer(Long customer_id, String first_name, String last_name, String username, String password) {
        this.customer_id = customer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;

    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//
//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password +
                '}';
    }
}


