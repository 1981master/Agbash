
package com.agbash.service;


public class Config {
    private String username;
    private String password;
    private String email;
    private String db;
    private String pass;

    
    public String getDb(){
        return db;
    }
    
    public void setDb(String db){
        this.db = db;
    }
    
    public String getPass(){
        return pass;
    }
    
    public void setPass(String pass){
        this.pass = pass;
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
}
