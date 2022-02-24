/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.chatClient.model;

import ch.bbbaden.chatClient.entity.Nachricht;
import ch.bbbaden.chatClient.entity.User;
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
    public List<Nachricht> getNachricht() {
        return nachrichten;
    }

    @Override
    public void addNachricht(Nachricht nachricht) {
        nachrichten.add(nachricht);
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
        }else{
            return -2;
        }
        
    }

}
