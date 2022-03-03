/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.easyChatApp.model;

import ch.bbbaden.easyChatApp.entity.Nachricht;
import ch.bbbaden.easyChatApp.entity.User;
import java.util.ArrayList;
import java.util.List;
import javafx.concurrent.Task;

/**
 *
 * @author da_so
 */
public class VolatileModel extends Model implements NachrichtModel, UserModel {

    private final List<User> users = new ArrayList();

    public VolatileModel() {

        //Nachrichten-Liste
        
        

        //User-Liste
        User u1 = new User("1234", "Damian");

        u1.addNachricht(new Nachricht("hallo"));
        
        User u2 = new User(null, "root");
        users.add(u2);
        // u1.setNachrichten(nachrichten);
        //u1.addNachricht(n13);
        users.add(u1);
    }

    /*

    @Override
    public void addNachricht(Nachricht nachricht) {
        List<Nachricht> oldNachrichten = new ArrayList<>(nachrichten);
        nachrichten.add(nachricht);
        changes.firePropertyChange("nachrichten", oldNachrichten, nachrichten);
    }
     */
    @Override
    public List<User> getUser() {
        return users;
    }

    @Override
    public void addUser(User user) {
        List<User> oldUsers = new ArrayList<>(users);
        users.add(user);
        changes.firePropertyChange("users", oldUsers, users);
    }

    @Override
    public int proveUser(User user) {
        if (user.getBenutzerName() != null) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getBenutzerName().equals(user.getBenutzerName())) {
                    if (users.get(i).getPassword() == null && user.getPassword() == null) {
                        return 0;
                    } else if (users.get(i).getPassword().equals(user.getPassword())) {
                        return 0;
                    }
                }
            }
            return -1;
        }
        return -2;
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

    }/*
    public void addNachricht(User user, Nachricht nachricht){
        List<Nachricht> oldNachricht = user.getNachrichten();
        user.addNachricht(nachricht);
        changes.firePropertyChange("nachrichten", oldNachricht, user.getNachrichten());
    }*/

    @Override
    public List<Nachricht> getAllNachrichten(User user) {
        return user.getNachrichten();
    }

    @Override
    public int getRightUser(String username, String password) {
        for (User user : users) {
            if (user.getBenutzerName().equals(username)) {
                if (user.getPassword() == null && password == null) {
                    return users.indexOf(user);
                } else if (user.getPassword().equals(password)) {
                    return users.indexOf(user);
                }
            }
        }
        return -1;
    }

    @Override
    public void addNachricht(Nachricht nachricht, User user, String username, Boolean usingUsername) {
        if(usingUsername)
        {
            for(User userSending : users){
            if(userSending.getBenutzerName().equals(username)){
                userSending.addNachricht(nachricht);
            }}
        }
        List<Nachricht> oldNachricht = user.getNachrichten();
        user.addNachricht(nachricht);
        changes.firePropertyChange("nachrichten", oldNachricht, user.getNachrichten());
    }

}
