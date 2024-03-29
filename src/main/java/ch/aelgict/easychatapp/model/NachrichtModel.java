/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.aelgict.easychatapp.model;

import ch.aelgict.easychatapp.entity.Nachricht;
import ch.aelgict.easychatapp.entity.User;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author da_so
 */
public interface NachrichtModel {

    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);

    public abstract List<Nachricht> getAllNachrichten(User user);

    public abstract ArrayList<Nachricht> getNachrichtenBetween(User me, User user);
    
    public abstract User getUser(String username);
    
    public boolean addNachricht(Nachricht nachricht);
    
}
