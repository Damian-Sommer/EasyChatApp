/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.chatClient.viewModel;

import ch.bbbaden.chatClient.entity.User;
import ch.bbbaden.chatClient.model.UserModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author da_so
 */
public class AnmeldungViewModel extends ViewModel {

    private StringProperty userName = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

    private final StringProperty errorMessage = new SimpleStringProperty();
    private UserModel model;
    private final List<User> users;

    public AnmeldungViewModel(UserModel model) {

        this.model = model;
        users = model.getUser();

    }

    public StringProperty getPassword() {
        return password;
    }

    public void setPassword(StringProperty password) {
        this.password = password;
    }

    public StringProperty getUserName() {
        return userName;
    }

    public void setUserName(StringProperty userName) {
        this.userName = userName;
    }

    public UserModel getModel() {
        return model;
    }

    public void setModel(UserModel model) {
        this.model = model;
    }

    public void saveAction() {

        if (userName.getValue() != null) {

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getBenutzerName().equals(userName.getValue())) {
                    if (users.get(i).getPassword().equals(password.getValue())) {
                        mainApp.showNachrichten();
                    }

                }
            }
            errorMessage.setValue("Benutzername oder Passwort ist Falsch!");
        } else {
            errorMessage.setValue("Bitte geben Sie den Benutzernamen ein!");
        }
    }

    public void chancelAction() {
        System.exit(0);
    }

    public StringProperty getErrorMessage() {
        return errorMessage;
    }

}
