package com.example.tutorial6;

public class Student {
    private String ID;
    private String Name;
    private String Address;
    private Integer ContactNo;

    public Student() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getContactNo() {
        return ContactNo;
    }

    public void setContactNo(Integer contactNo) {
        ContactNo = contactNo;
    }
}
