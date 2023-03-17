/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.aelgict.easyChatApp.viewModel;

import ch.aelgict.easyChatApp.entity.User;
import ch.aelgict.easyChatApp.model.UserModel;
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

    public AnmeldungViewModel(UserModel model) {
        this.model = model;
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

    public void proveUser() {
        System.out.println("Prove User");
        int ret = model.proveUser(new User(password.getValue(), userName.getValue()));
        switch (ret) {
            case 0:
                System.out.println("Got User");
                //User selectedUser = new User(password.getValue(), userName.getValue());
                int index = model.getRightUser(userName.getValue(), password.getValue());
                if (index != -1) {
                    User user = model.getUserList().get(index);
                    //mainApp.showNachrichtenverlauf(user);
                    mainApp.showUserList(user);
                    return;
                }
                errorMessage.setValue("Benutzername oder Passwort ist Falsch!");
                break;
            case -1:
                errorMessage.setValue("Benutzername oder Passwort ist Falsch!");
                break;
            case -2:
                errorMessage.setValue("Bitte geben Sie den Benutzernamen ein!");
                break;
            default:
                errorMessage.setValue("");
                break;
        }
    }

    public void chancelAction() {
        System.exit(0);
    }

    public StringProperty getErrorMessage() {
        return errorMessage;
    }

    public void createNewUser() {
        mainApp.showCreateNewUserForm();
    }

}
