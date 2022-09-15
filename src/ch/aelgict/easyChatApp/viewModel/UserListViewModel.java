/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.aelgict.easyChatApp.viewModel;

import ch.aelgict.easyChatApp.entity.Nachricht;
import ch.aelgict.easyChatApp.entity.User;
import ch.aelgict.easyChatApp.model.NachrichtModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author da_so
 */
public class UserListViewModel extends ViewModel implements PropertyChangeListener {

    private final ObservableList<Nachricht> nachrichten = FXCollections.observableArrayList();
    private final ObservableList<User> users = FXCollections.observableArrayList();
    private final StringProperty neueNachricht = new SimpleStringProperty();

    
    private final User user;
    private NachrichtModel model;

    public UserListViewModel(NachrichtModel model, User user) {
        this.model = model;/*
        this.nachrichten.addAll(model.getNachricht());*/
        this.user = user;
        //System.out.println(model.getAllNachrichten(user).get(0).getMessage());
        this.nachrichten.setAll(model.getAllNachrichten(user));
    }

    public ObservableList<User> getUserList() {
        getAllUsersFromNachrichten();
        this.users.removeAll();
        this.users.setAll(removeDuplicatesFromUser());
        return users;
    }

    private ArrayList<User> removeDuplicatesFromUser(){
        ArrayList<User> newUserList = new ArrayList<>();
        for(User user : users){
            if(!newUserList.contains(user)){
                newUserList.add(user);
            }
        }
        return newUserList;
    }
    private void getAllUsersFromNachrichten(){
        for(Nachricht nachricht : nachrichten){
            if(nachricht.getAbsender().getUseruid().equals(user.getUseruid())){
                users.add(nachricht.getAnkommer());
            }else if(nachricht.getAnkommer().getUseruid().equals(user.getUseruid())){
                users.add(nachricht.getAbsender());
            }
        }
    }
    
    public NachrichtModel getModel() {
        return model;
    }

    public void setModel(NachrichtModel model) {
        this.model = model;
    }

    public void selectUser(User user) {
        mainApp.showNachrichtenverlauf(user, this.user);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "nachrichten":
                this.nachrichten.addAll(model.getAllNachrichten(user));
            default:
        }
    }

    public void chancelAction() {
        mainApp.showAnmeldungForm();
    }

}
