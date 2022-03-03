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
public interface UserModel {

    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);

    public abstract List<User> getUser();
    
    public abstract int getRightUser(String username, String password);

    public abstract void addUser(User user);
    
    public abstract int proveUser(User user);

    public abstract int createNewUser(String username, String password, String passwordRepeat);
        
}
