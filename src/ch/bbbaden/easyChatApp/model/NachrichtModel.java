/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.easyChatApp.model;

import ch.bbbaden.easyChatApp.entity.Nachricht;
import ch.bbbaden.easyChatApp.entity.User;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 *
 * @author da_so
 */
public interface NachrichtModel {

    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);

    public abstract List<Nachricht> getAllNachrichten(User user);


    public void addNachricht(Nachricht nachricht, User user, String value, Boolean value0);
    
    

}
