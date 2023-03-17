/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.aelgict.easychatapp.viewModel;

import ch.aelgict.easychatapp.entity.Nachricht;
import ch.aelgict.easychatapp.entity.User;
import ch.aelgict.easychatapp.model.NachrichtModel;
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
    private int chancelOption;
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

    public int getChancelOption() {
        return chancelOption;
    }

    public void setChancelOption(int chancelOption) {
        this.chancelOption = chancelOption;
    }

    public void saveAction() {
        if (message.getValue() == null) {
            errorMessage.setValue(String.format("Bitte geben sie mindestens %d Buchstaben ein!", 1));
            mainApp.showNachrichtenForm(user, me, 0);
        } else {
            //model.addNachricht(new Nachricht(message.getValue(),me, user), user, username.getValue(), usingUsername.getValue());
            model.addNachricht(new Nachricht(message.getValue(),me, user));
            mainApp.showNachrichtenverlauf(user, me);
        }
    }

    public void chancelAction() {
        
        if(chancelOption == 0){
            mainApp.showNachrichtenverlauf(user,me);
        }else{
            mainApp.showUserList(me);
        }
        
    }

    public StringProperty getErrorMessage() {
        return errorMessage;
    }
}
