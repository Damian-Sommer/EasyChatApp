/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.chatClient.viewModel;

import ch.bbbaden.chatClient.entity.Nachricht;
import ch.bbbaden.chatClient.entity.User;
import ch.bbbaden.chatClient.model.NachrichtModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author da_so
 */
public class NachrichtFormViewModel extends ViewModel {

    private StringProperty message = new SimpleStringProperty();
    private StringProperty username = new SimpleStringProperty();
    private final StringProperty errorMessage = new SimpleStringProperty();
    private BooleanProperty usingUsername = new SimpleBooleanProperty();
    private NachrichtModel model;

    private final User user;

    public NachrichtFormViewModel(NachrichtModel model, User user) {
        this.model = model;
        this.user = user;
    }

    public StringProperty getMessage() {
        return message;
    }

    public void setMessage(StringProperty message) {
        this.message = message;
    }

    public NachrichtModel getModel() {

        return model;
    }

    public void setModel(NachrichtModel model) {
        this.model = model;
    }

    public void saveAction() {
        if (message.getValue() == null) {
            errorMessage.setValue(String.format("Bitte geben sie mindestens %d Buchstaben ein!", 1));
        } else {
            System.out.println(usingUsername.getValue());
            model.addNachricht(new Nachricht(message.getValue()), user, username.getValue(), usingUsername.getValue());
        }
        mainApp.showNachrichten(user);
    }

    public void chancelAction() {
        mainApp.showNachrichten(user);
    }

    public StringProperty getErrorMessage() {
        return errorMessage;
    }

    public StringProperty getUsername() {
        return username;
    }

    public void setUsername(StringProperty username) {
        this.username = username;
    }

    public BooleanProperty getUsingUsername() {
        return usingUsername;
    }

    public void setUsingUsername(BooleanProperty usingUsername) {
        this.usingUsername = usingUsername;
    }

}
