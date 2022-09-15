/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.aelgict.easyChatApp.viewModel;

import ch.aelgict.easyChatApp.entity.User;
import ch.aelgict.easyChatApp.model.UserModel;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author da_so
 */
public class CreateNewUserViewModel extends ViewModel {

    private StringProperty userName = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty passwordRepeat = new SimpleStringProperty();

    private final StringProperty errorMessage = new SimpleStringProperty();

    private UserModel model;
    private final List<User> users;

    public CreateNewUserViewModel(UserModel userModel) {
        this.model = userModel;
        users = model.getUserList();
    }

    public StringProperty getErrorMessage() {
        return errorMessage;
    }

    public StringProperty getUserName() {
        return userName;
    }

    public void setUserName(StringProperty userName) {
        this.userName = userName;
    }

    public StringProperty getPassword() {
        return password;
    }

    public void setPassword(StringProperty password) {
        this.password = password;
    }

    public StringProperty getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(StringProperty passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public UserModel getModel() {
        return model;
    }

    public void setModel(UserModel model) {
        this.model = model;
    }

    public void chancelAction() {
        mainApp.showAnmeldungForm();
    }

    public void createUser() {
        int ret = model.createNewUser(userName.getValue(), password.getValue(), passwordRepeat.getValue());
        if (ret == 0) {
            mainApp.showAnmeldungForm();
        } else if (ret == -1) {
            errorMessage.setValue("Benutzername existiert schon!");
        } else if (ret == -2) {
            errorMessage.setValue("Passwort stimmt nicht überein!");
        } else if (ret == -3) {
            errorMessage.setValue("Bitte geben sie einen Benutzernamen ein");
        }
    }

}
