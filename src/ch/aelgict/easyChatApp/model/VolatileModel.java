/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.aelgict.easyChatApp.model;

import ch.aelgict.easyChatApp.entity.Nachricht;
import ch.aelgict.easyChatApp.entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author da_so
 */
public class VolatileModel extends Model implements NachrichtModel, UserModel {

    private final List<User> users = new ArrayList();

    public VolatileModel() {

        User u1 = new User("1234", "Damian");

        u1.addNachricht(new Nachricht("hallo", u1, u1));

        User u2 = new User(null, "root");
        users.add(u2);
        users.add(u1);
    }

    @Override
    public List<User> getUserList() {
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

    }

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

    /**
     *
     * @param username
     * @return
     */
    @Override
    public User getUser(String username) {
        for (User user : users) {
            if (user.getBenutzerName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void addNachricht(Nachricht nachricht) {
        if (nachricht.getAbsender() == nachricht.getAnkommer()) {
            nachricht.getAbsender().addNachricht(nachricht);
            List<Nachricht> oldNachrichtAbsender = nachricht.getAbsender().getNachrichten();
            changes.firePropertyChange("nachrichten", oldNachrichtAbsender, nachricht.getAbsender().getNachrichten());
        } else {
            nachricht.getAbsender().addNachricht(nachricht);
            nachricht.getAnkommer().addNachricht(nachricht);

            List<Nachricht> oldNachrichtAbsender = nachricht.getAbsender().getNachrichten();
            List<Nachricht> oldNachrichtAnkommer = nachricht.getAnkommer().getNachrichten();

            changes.firePropertyChange("nachrichten", oldNachrichtAbsender, nachricht.getAbsender().getNachrichten());
            changes.firePropertyChange("nachrichten", oldNachrichtAnkommer, nachricht.getAnkommer().getNachrichten());
        }

    }

    @Override
    public ArrayList<Nachricht> getNachrichtenBetween(User me, User user) {
        ArrayList<Nachricht> alleNachrichten = (ArrayList<Nachricht>) getAllNachrichten(me);
        ArrayList<Nachricht> nachrichtenBetween = new ArrayList<>();

        for (Nachricht nachricht : alleNachrichten) {
            if (((nachricht.getAbsender().getUseruid().equals(me.getUseruid())) && (nachricht.getAnkommer().getUseruid().equals(user.getUseruid())))
                    || ((nachricht.getAbsender().getUseruid().equals(user.getUseruid())) && (nachricht.getAnkommer().getUseruid().equals(me.getUseruid())))) {
                nachrichtenBetween.add(nachricht);
            }
        }
        return nachrichtenBetween;
    }

}
