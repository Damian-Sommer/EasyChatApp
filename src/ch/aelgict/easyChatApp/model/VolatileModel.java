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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author da_so
 */
public class VolatileModel extends Model implements NachrichtModel, UserModel {

    private Pattern pattern = Pattern.compile("[a-zA-Z0-9._-]{3,}@[a-zA-Z0-9.-]{3,}\\.[a-zA-Z]{2,4}");
    private final List<User> users = new ArrayList();

    public VolatileModel() {
        /*
        User u1 = new User("1234", "damian", "Damian", "Sommer", "info@aelgict.ch");
        User u2 = new User(null, "root", "jeawioj", "jieoawo", "info@aelgict.ch");
        users.add(u2);
        users.add(u1);*/
    }

    @Override
    public List<User> getUserList() {
        return users;
    }

    @Override
    public boolean addUser(User user) {
        List<User> oldUsers = new ArrayList<>(users);
        for (User userElem : this.users) {
            if (userElem.getBenutzerName().equals(user.getBenutzerName())) {
                return false;
            }
        }
        users.add(user);
        changes.firePropertyChange("users", oldUsers, users);
        return true;
    }

    @Override
    public int proveUser(User user) {
        if (user == null) {
            return -3;
        }
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
    public int[] createNewUser(String username, String password, String passwordRepeat, String vorname, String nachname, String email) {
        int[] ret = new int[5];
        Matcher matcher = null;

        if (email == null) {
            ret[4] = -3;
        } else {
            matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                ret[4] = -1;
            } else {
                ret[4] = 0;
            }
        }

        ret[0] = 0;

        if (username == null) {
            ret[0] = -1;
        } else if (username.length() <= 4) {
            ret[0] = -2;
        } else {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getBenutzerName().equals(username)) {
                    ret[0] = -3;
                } else if (users.get(i).getEmail().equals(email)) {
                    ret[4] = -2;
                }
            }
        }

        if ((password == null && passwordRepeat == null)) {
            ret[1] = 0;
        } else if (!password.equals(passwordRepeat)) {
            ret[1] = -2;
        } else {
            ret[1] = 0;
        }

        if (vorname.length() <= 1) {
            ret[2] = -1;
        } else {
            ret[2] = 0;
        }

        if (nachname.length() <= 1) {
            ret[3] = -1;
        } else {
            ret[3] = 0;
        }
        int returnOrCreateNewUser = 0;
        for (int i = 0; i < ret.length; i++) {
            if (ret[i] != 0) {
                returnOrCreateNewUser = 1;
            }
        }

        if (returnOrCreateNewUser != 0) {
            return ret;
        }
        addUser(new User(password, username, vorname, nachname, email));
        return ret;

    }

    @Override
    public List<Nachricht> getAllNachrichten(User user) {
        if (user.getNachrichten() == null) {
            return null;
        }
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
    public User getUser(String username) {
        for (User user : users) {
            if (user.getBenutzerName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean addNachricht(Nachricht nachricht) {
        if (nachricht == null) {
            return false;
        } else {
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
        return true;
    }

    @Override
    public ArrayList<Nachricht> getNachrichtenBetween(User me, User user) {
        ArrayList<Nachricht> alleNachrichten = (ArrayList<Nachricht>) getAllNachrichten(me);
        ArrayList<Nachricht> nachrichtenBetween = new ArrayList<>();
        if (alleNachrichten == null) {
            return null;
        }
        for (Nachricht nachricht : alleNachrichten) {
            if (((nachricht.getAbsender().getUseruid().equals(me.getUseruid())) && (nachricht.getAnkommer().getUseruid().equals(user.getUseruid())))
                    || ((nachricht.getAbsender().getUseruid().equals(user.getUseruid())) && (nachricht.getAnkommer().getUseruid().equals(me.getUseruid())))) {
                nachrichtenBetween.add(nachricht);
            }
        }
        return nachrichtenBetween;
    }
}
