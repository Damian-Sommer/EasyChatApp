/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.chatClient.entity;

import java.util.List;

/**
 *
 * @author da_so
 */
public class User {

    private String password;
    private String benutzerName;

    public User(String password, String benutzerName) {
        this.password = password;
        this.benutzerName = benutzerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBenutzerName() {
        return benutzerName;
    }

    public void setBenutzerName(String benutzerName) {
        this.benutzerName = benutzerName;
    }

}
