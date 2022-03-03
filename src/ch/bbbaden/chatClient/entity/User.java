/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.chatClient.entity;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import javafx.concurrent.Task;

/**
 *
 * @author da_so
 */
public class User {

    private String password;
    private String benutzerName;

    private List<Nachricht> nachrichten;

    public User(String password, String benutzerName) {
        this.password = password;
        this.benutzerName = benutzerName;
        this.nachrichten = new ArrayList();
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

}
