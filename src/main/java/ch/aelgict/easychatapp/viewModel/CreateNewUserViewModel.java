/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.aelgict.easychatapp.viewModel;

import ch.aelgict.easychatapp.entity.User;
import ch.aelgict.easychatapp.model.UserModel;
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
    private StringProperty vorname = new SimpleStringProperty();
    private StringProperty nachname = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();

    private StringProperty errorMessageUsername = new SimpleStringProperty();
    private StringProperty errorMessagePassword = new SimpleStringProperty();
    private StringProperty errorMessageVorname = new SimpleStringProperty();
    private StringProperty errorMessageNachname = new SimpleStringProperty();
    private StringProperty errorMessageEmail = new SimpleStringProperty();


    private UserModel model;
    private final List<User> users;

    public CreateNewUserViewModel(UserModel userModel) {
        this.model = userModel;
        users = model.getUserList();
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
        int[] ret = model.createNewUser(userName.getValue(), password.getValue(), passwordRepeat.getValue(), vorname.getValue(), nachname.getValue(), email.getValue());
        if (ret[0] == 0 && ret[1] == 0 && ret[2] == 0 && ret[3] == 0 && ret[4] == 0) {
            mainApp.showAnmeldungForm();
        }else{
            switch (ret[0]){
                case -1:
                    errorMessageUsername.setValue("Bitte geben Sie einen Benutzernamen ein");
                    break;
                case -3:
                    errorMessageUsername.setValue("Benutzername existiert schon");
                    break;
                case -2:
                    errorMessageUsername.setValue("Der Benutzername muss mehr als 4 Buchstaben haben");
                    break;
                case 0:
                    errorMessageUsername.setValue("");
            }
            
            switch (ret[1]){
                case -2:
                    errorMessagePassword.setValue("Passwort stimmt nicht überein");
                    break;
                case 0:
                    errorMessagePassword.setValue("");
            }
            
            switch (ret[2]){
                case -1:
                    errorMessageVorname.setValue("Der Vorname muss mehr als 1 Buchstaben haben");
                    break;
                case 0:
                    errorMessageVorname.setValue("");
            }
            
            switch (ret[3]){
                case -1:
                    errorMessageNachname.setValue("Der Nachname muss mehr als 1 Buchstaben haben");
                    break;
                case 0:
                    errorMessageNachname.setValue("");
            }
            
            switch (ret[4]){
                case -1:
                    errorMessageEmail.setValue("Bitte geben Sie eine valide Email ein");
                    break;
                case -2:
                    errorMessageEmail.setValue("Bitte geben Sie eine andere Email ein");
                    break;
                case -3:
                    errorMessageEmail.setValue("Bitte geben Sie eine Email ein");
                    break;
                case 0:
                    errorMessageEmail.setValue("");
            }
        }
    }

    public StringProperty getVorname() {
        return vorname;
    }

    public void setVorname(StringProperty vorname) {
        this.vorname = vorname;
    }

    public StringProperty getNachname() {
        return nachname;
    }

    public void setNachname(StringProperty nachname) {
        this.nachname = nachname;
    }

    public StringProperty getEmail() {
        return email;
    }

    public void setEmail(StringProperty email) {
        this.email = email;
    }

    public StringProperty getErrorMessageUsername() {
        return errorMessageUsername;
    }

    public void setErrorMessageUsername(StringProperty errorMessageUsername) {
        this.errorMessageUsername = errorMessageUsername;
    }

    public StringProperty getErrorMessagePassword() {
        return errorMessagePassword;
    }

    public void setErrorMessagePassword(StringProperty errorMessagePassword) {
        this.errorMessagePassword = errorMessagePassword;
    }

    public StringProperty getErrorMessageVorname() {
        return errorMessageVorname;
    }

    public void setErrorMessageVorname(StringProperty errorMessageVorname) {
        this.errorMessageVorname = errorMessageVorname;
    }

    public StringProperty getErrorMessageNachname() {
        return errorMessageNachname;
    }

    public void setErrorMessageNachname(StringProperty errorMessageNachname) {
        this.errorMessageNachname = errorMessageNachname;
    }

    public StringProperty getErrorMessageEmail() {
        return errorMessageEmail;
    }

    public void setErrorMessageEmail(StringProperty errorMessageEmail) {
        this.errorMessageEmail = errorMessageEmail;
    }

    
}
