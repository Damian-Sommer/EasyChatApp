/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.aelgict.easychatapp.viewModel;

import ch.aelgict.easychatapp.entity.Nachricht;
import ch.aelgict.easychatapp.entity.User;
import ch.aelgict.easychatapp.model.NachrichtModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author da_so
 */
public class UserListViewModel extends ViewModel implements PropertyChangeListener {

    private final ObservableList<Nachricht> nachrichten = FXCollections.observableArrayList();
    private final ObservableList<User> users = FXCollections.observableArrayList();
    private StringProperty benutzername = new SimpleStringProperty();
    private StringProperty errorMessageSearchUser = new SimpleStringProperty();

    private final User userMe;
    private NachrichtModel model;

    public UserListViewModel(NachrichtModel model, User user) {
        this.model = model;
        this.userMe = user;
        var ret = model.getAllNachrichten(user);
        if (ret != null) {
            this.nachrichten.setAll(ret);
            System.out.println(this.nachrichten.size());
        }
    }

    public ObservableList<User> getUserList() {
        getAllUsersFromNachrichten();
        this.users.removeAll();
        removeDuplicatesFromUser();
        return users;
    }

    private void removeDuplicatesFromUser() {
        System.out.println("removeDuplicatesFromUser");
        ArrayList<User> newUserList = new ArrayList<>();
        for (User user : users) {
            if (newUserList.isEmpty()) {
                newUserList.add(user);
            } else {
                boolean add = true;
                for (User value : newUserList) {
                    if (user.getUserId().equals(value.getUserId())) {
                        add = false;
                        break;
                    }
                }
                if (add) {
                    newUserList.add(user);
                }
            }
        }
        this.users.clear();
        this.users.setAll(newUserList);
    }

    private void getAllUsersFromNachrichten() {
        System.out.println("getAllUsersFromNachrichten");
        for (Nachricht nachricht : this.nachrichten) {
            System.out.println(nachricht.getMessage());
            System.out.println(nachricht.getAbsender().getUserId());
            System.out.println(nachricht.getAnkommer().getUserId());
            if (nachricht.getAbsender().getUserId().equals(userMe.getUserId())) {
                System.out.println("0");
                System.out.println(nachricht.getAnkommer().getBenutzerName());
                this.users.add(nachricht.getAnkommer());
            } else if (nachricht.getAnkommer().getUserId().equals(userMe.getUserId())) {
                System.out.println("1");
                System.out.println(nachricht.getAbsender().getBenutzerName());
                this.users.add(nachricht.getAbsender());
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
        mainApp.showNachrichtenverlauf(user, this.userMe);
    }

    public StringProperty getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(StringProperty benutzername) {
        this.benutzername = benutzername;
    }

    public StringProperty getErrorMessageSearchUser() {
        return errorMessageSearchUser;
    }

    public void setErrorMessageSearchUser(StringProperty errorMessageSearchUser) {
        this.errorMessageSearchUser = errorMessageSearchUser;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "nachrichten":
                this.nachrichten.addAll(model.getAllNachrichten(userMe));
            default:
        }
    }

    public void chancelAction() {
        mainApp.showAnmeldungForm();
    }

    public void searchUser() {
        if (model.getUser(benutzername.getValue()) != null) {
            mainApp.showNachrichtenForm(model.getUser(benutzername.getValue()), userMe, 1);
        } else {
            mainApp.showUserList(userMe);
        }
    }
}
