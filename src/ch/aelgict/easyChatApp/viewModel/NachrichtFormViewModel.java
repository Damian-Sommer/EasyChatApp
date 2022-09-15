/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.aelgict.easyChatApp.viewModel;

import ch.aelgict.easyChatApp.entity.Nachricht;
import ch.aelgict.easyChatApp.entity.User;
import ch.aelgict.easyChatApp.model.NachrichtModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author da_so
 */
public class NachrichtFormViewModel extends ViewModel {
    private StringProperty message = new SimpleStringProperty();
    private final StringProperty errorMessage = new SimpleStringProperty();
    private NachrichtModel model;

    private final User user;
    private final User me;
    public NachrichtFormViewModel(NachrichtModel model, User user, User me) {
        this.model = model;
        this.user = user;
        this.me = me;
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
            mainApp.showNachrichtenForm(user, me);
        } else {
            //model.addNachricht(new Nachricht(message.getValue(),me, user), user, username.getValue(), usingUsername.getValue());
            model.addNachricht(new Nachricht(message.getValue(),me, user));
            mainApp.showNachrichtenverlauf(user, me);
        }
    }

    public void chancelAction() {
        mainApp.showNachrichtenverlauf(user,me);
    }

    public StringProperty getErrorMessage() {
        return errorMessage;
    }
}
