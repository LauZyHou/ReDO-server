package com.ecnu.refactoring.dao;


public class User  {

    private String username;
    private String password;
    private String email;
    private String company;

    public User(String username, String password, String email, String company) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.company = company;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}

