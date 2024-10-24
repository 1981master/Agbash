package com.agbash.model;

public class ModelLogin {

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserName() {
        return userName;
    }

    public ModelLogin(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public ModelLogin() {
    }

    private String email;
    private String password;
    private String userName;

}
