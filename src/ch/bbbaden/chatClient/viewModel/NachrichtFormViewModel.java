/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.chatClient.viewModel;

import ch.bbbaden.chatClient.entity.Nachricht;
import ch.bbbaden.chatClient.model.NachrichtModel;
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

    public NachrichtFormViewModel(NachrichtModel model) {
        this.model = model;
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
            model.addNachricht(new Nachricht(message.getValue()));
        }
        mainApp.showNachrichten();
    }

    public void chancelAction() {
        mainApp.showNachrichten();
    }

    public StringProperty getErrorMessage() {
        return errorMessage;
    }
}
