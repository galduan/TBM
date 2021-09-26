package com.example.tbm;

public class UserInfo {

    String first_name, last_name, password, email, phone_num;


    public UserInfo(String first_name, String last_name, String email, String password, String phone_num) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.phone_num = phone_num;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_num() {
        return phone_num;
    }
}