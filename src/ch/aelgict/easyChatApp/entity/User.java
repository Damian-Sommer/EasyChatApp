/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.aelgict.easyChatApp.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author da_so
 */
public class User {

    private int userId;
    private String useruid;
    private String password;
    private String benutzerName;
    private String vorname;
    private String nachname;
    private String email;
    
    private List<Nachricht> nachrichten;

    public User(String password, String benutzerName, String vorname, String nachname, String email) {
        this.password = password;
        this.benutzerName = benutzerName;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.nachrichten = new ArrayList();
        this.useruid = UUID.randomUUID().toString();
    }

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

    public List<Nachricht> getNachrichten() {
        return nachrichten;
    }

    public void addNachricht(Nachricht nachricht) {
        nachrichten.add(nachricht);
    }

    public void setNachrichten(List<Nachricht> nachrichten) {
        this.nachrichten = nachrichten;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUseruid() {
        return useruid;
    }

    public void setUseruid(String useruid) {
        this.useruid = useruid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    
    
}
