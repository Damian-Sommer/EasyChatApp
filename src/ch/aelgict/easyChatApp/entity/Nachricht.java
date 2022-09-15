/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.aelgict.easyChatApp.entity;

import java.util.Date;

/**
 *
 * @author da_so
 */
public class Nachricht {

    private User absender;
    private User ankommer;
    private String message;
    private Date absendeDatum;

    public Nachricht(String message, User absender, User ankommer) {
        this.absender = absender;
        this.ankommer = ankommer;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getAbsender() {
        return absender;
    }

    public void setAbsender(User absender) {
        this.absender = absender;
    }

    public User getAnkommer() {
        return ankommer;
    }

    public void setAnkommer(User ankommer) {
        this.ankommer = ankommer;
    }

    public Date getAbsendeDatum() {
        return absendeDatum;
    }

    public void setAbsendeDatum(Date absendeDatum) {
        this.absendeDatum = absendeDatum;
    }
    
}
