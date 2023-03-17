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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author da_so
 */
public class ChatViewModel extends ViewModel implements PropertyChangeListener {

    private final ObservableList<Nachricht> nachrichten = FXCollections.observableArrayList();
    private final StringProperty neueNachricht = new SimpleStringProperty();

    private Nachricht nachricht;
    private final User me;
    private final User user;
    private NachrichtModel model;

    public ChatViewModel(NachrichtModel model, User user, User me) {
        this.model = model;
        this.user = user;
        this.me = me;
        this.nachrichten.addAll(model.getNachrichtenBetween(me, user));
    }

    public ObservableList<Nachricht> getNachricht() {
        return nachrichten;
    }

    public NachrichtModel getModel() {
        return model;
    }

    public void setModel(NachrichtModel model) {
        this.model = model;
    }

    public void addNachricht() {
        mainApp.showNachrichtenForm(user, me, 0);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "nachrichten":
                this.nachrichten.setAll(model.getNachrichtenBetween(me, user));
            default:
        }
    }

    public void chancelAction() {
        mainApp.showUserList(me);
    }

}
