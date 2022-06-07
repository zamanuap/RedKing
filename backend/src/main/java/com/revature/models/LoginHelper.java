package com.revature.models;

public class LoginHelper {
    private String email;
    private String password;

    public LoginHelper( String email, String password ) {
        this.email = email;
        this.password = password;
    }

    public LoginHelper() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginHelper{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
