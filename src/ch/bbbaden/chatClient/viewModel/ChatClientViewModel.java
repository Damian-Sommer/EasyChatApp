/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.chatClient.viewModel;

import ch.bbbaden.chatClient.entity.Nachricht;
import ch.bbbaden.chatClient.model.NachrichtModel;
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
public class ChatClientViewModel extends ViewModel implements PropertyChangeListener {

    private final ObservableList<Nachricht> nachrichten = FXCollections.observableArrayList();
    private final StringProperty neueNachricht = new SimpleStringProperty();

    private Nachricht nachricht;
    private NachrichtModel model;

    public ChatClientViewModel(NachrichtModel model) {
        this.model = model;
        this.nachrichten.addAll(model.getNachricht());
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
        mainApp.showNachrichtenForm();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "nachrichten":
                this.nachrichten.setAll(model.getNachricht());
            default:
        }
    }

    public void chancelAction() {
        mainApp.showAnmeldungForm();
    }

}
