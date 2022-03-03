/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.easyChatApp.view;

import ch.bbbaden.easyChatApp.viewModel.CreateNewUserViewModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author da_so
 */
public class CreateNewUserFormView implements Initializable {

    private CreateNewUserViewModel viewModel;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="backgroundAnchorpane"
    private AnchorPane backgroundAnchorpane; // Value injected by FXMLLoader

    @FXML // fx:id="titleLabel"
    private Label titleLabel; // Value injected by FXMLLoader

    @FXML // fx:id="btnAbbrechen"
    private Button btnAbbrechen; // Value injected by FXMLLoader

    @FXML // fx:id="btnErstellen"
    private Button btnErstellen; // Value injected by FXMLLoader

    @FXML // fx:id="txtUsername"
    private TextField txtUsername; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassword"
    private TextField txtPassword; // Value injected by FXMLLoader

    @FXML // fx:id="errorLabel"
    private Label errorLabel; // Value injected by FXMLLoader

    @FXML // fx:id="txtPasswordRepetition"
    private TextField txtPasswordRepetition; // Value injected by FXMLLoader

    @FXML
    void chancelAction(ActionEvent event) {
        viewModel.chancelAction();
    }

    @FXML
    void creatingUserLogin(ActionEvent event) {
        viewModel.createUser();
    }

    public void setViewModel(CreateNewUserViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void bind() {
        txtUsername.textProperty().bindBidirectional(viewModel.getUserName());
        txtPassword.textProperty().bindBidirectional(viewModel.getPassword());
        txtPasswordRepetition.textProperty().bindBidirectional(viewModel.getPasswordRepeat());
        errorLabel.textProperty().bind(viewModel.getErrorMessage());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        errorLabel.wrapTextProperty().set(true);
        
        errorLabel.setText("");
        
        assert btnAbbrechen != null : "fx:id=\"btnAbbrechen\" was not injected: check your FXML file 'CreateNewUserForm.fxml'.";
        assert btnErstellen != null : "fx:id=\"btnErstellen\" was not injected: check your FXML file 'CreateNewUserForm.fxml'.";
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'CreateNewUserForm.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'CreateNewUserForm.fxml'.";
        assert txtPasswordRepetition != null : "fx:id=\"txtPasswordRepetition\" was not injected: check your FXML file 'CreateNewUserForm.fxml'.";

    }

}
