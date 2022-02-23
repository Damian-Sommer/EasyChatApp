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

/**
 *
 * @author da_so
 */
public class VolatileModel extends Model implements NachrichtModel, UserModel {

    private final List<Nachricht> nachrichten;
    private final List<User> users;

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

        users = new ArrayList<>();

        users.add(u1);

    }

    @Override
    public List<Nachricht> getNachricht() {
        return nachrichten;
    }

    @Override
    public void addNachricht(Nachricht nachricht) {
        List<Nachricht> oldNachrichten = new ArrayList<>(nachrichten);
        nachrichten.add(nachricht);
        changes.firePropertyChange("nachrichten", oldNachrichten, nachrichten);
    }

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

}
