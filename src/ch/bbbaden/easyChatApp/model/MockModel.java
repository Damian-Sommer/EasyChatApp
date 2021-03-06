/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.easyChatApp.model;

import ch.bbbaden.easyChatApp.entity.Nachricht;
import ch.bbbaden.easyChatApp.entity.User;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author da_so
 */
public class MockModel implements NachrichtModel, UserModel {

    private final List<Nachricht> nachrichten = new ArrayList();
    private final List<User> users = new ArrayList();

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
    }

    @Override
    public List<User> getUser() {
        return users;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public int proveUser(User user) {
        if (user.getBenutzerName() != null) {

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getBenutzerName().equals(user.getBenutzerName())) {
                    if (users.get(i).getPassword().equals(user.getPassword())) {
                        return 0;
                    }
                }
            }
            return -1;
            //errorMessage.setValue("Benutzername oder Passwort ist Falsch!");
        } else {
            return -2;
        }
    }

    @Override
    public int createNewUser(String username, String password, String passwordRepeat) {
        if (username != null) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getBenutzerName().equals(username)) {
                    return -1;
                }
            }
            if ((password == null && passwordRepeat == null)) {
                addUser(new User(password, username));
                return 0;
            } else if (!password.equals(passwordRepeat)) {
                return -2;
            } else {
                addUser(new User(password, username));
                return 0;
            }
        }
        return -3;
    }

    @Override
    public List<Nachricht> getAllNachrichten(User user) {
        return null;
    }

    @Override
    public int getRightUser(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addNachricht(Nachricht nachricht, User user, String value, Boolean value0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
