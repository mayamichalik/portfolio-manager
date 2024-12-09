//// This entity class uses annotations instead of the mapping XML file
//
//package com.conygre.spring.boot.entities;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//
//// add an annotations specifying the table that this will map to
//@Entity @Table(name="contact_info")
//public class ContactInfo implements Serializable {
//
//
//    // add an attribute specifying a column for the id property
//    // add attributes to ensure that the id column is automatically generated
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name="id")
//    private int id;
//
//    // add attributes for all the remaining properties
//    @Column(name="email") private String email;
//    @Column(name="phone_number") private String phone_number;
//
//    public ContactInfo() {}
//
//    public ContactInfo(String e, String p){
//        email=e;
//        phone_number=p;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhone_number() {
//        return phone_number;
//    }
//
//    public void setPhone_number(String phone_number) {
//        this.phone_number = phone_number;
//    }
//}
