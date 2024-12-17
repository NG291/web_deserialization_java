package model;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String email;
    private int user_id;


    public User(int user_id,String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.user_id = user_id;
    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
            this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
