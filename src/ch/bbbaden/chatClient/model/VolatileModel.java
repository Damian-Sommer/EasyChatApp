/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.chatClient.model;

import ch.bbbaden.chatClient.entity.Nachricht;
import ch.bbbaden.chatClient.entity.User;
import java.util.ArrayList;
import java.util.List;
import javafx.concurrent.Task;

/**
 *
 * @author da_so
 */
public class VolatileModel extends Model implements NachrichtModel, UserModel {

    private final List<Nachricht> nachrichten;
    private final List<User> users = new ArrayList();

    public VolatileModel() {

        //Nachrichten-Liste
        Nachricht n1 = new Nachricht("hallo zusammen");
        Nachricht n2 = new Nachricht("hallo du");
        Nachricht n3 = new Nachricht("hallo");
        Nachricht n4 = new Nachricht("ha");
        Nachricht n5 = new Nachricht("ha");
        Nachricht n6 = new Nachricht("h");
        Nachricht n7 = new Nachricht("a");
        Nachricht n8 = new Nachricht("l");
        Nachricht n9 = new Nachricht("l");
        Nachricht n11 = new Nachricht("0");
        Nachricht n12 = new Nachricht("h");
        Nachricht n13 = new Nachricht("a");
        nachrichten = new ArrayList<>();
        nachrichten.add(n1);
        nachrichten.add(n2);
        nachrichten.add(n3);
        nachrichten.add(n4);
        nachrichten.add(n5);
        nachrichten.add(n6);
        nachrichten.add(n7);
        nachrichten.add(n8);
        nachrichten.add(n9);
        nachrichten.add(n12);
        nachrichten.add(n11);
        nachrichten.add(n13);

        //User-Liste
        User u1 = new User("1234", "Damian");

        u1.addNachricht(new Nachricht("hallo"));
        System.out.println(u1.getNachrichten().size());
        System.out.println(u1.getNachrichten().get(0).getMessage());
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
    public void addNachricht(Nachricht nachricht, User user) {
        List<Nachricht> oldNachricht = user.getNachrichten();
        user.addNachricht(nachricht);
        changes.firePropertyChange("nachrichten", oldNachricht, user.getNachrichten());
    }

}
