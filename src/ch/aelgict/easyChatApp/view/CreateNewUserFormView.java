/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.aelgict.easyChatApp.view;

import ch.aelgict.easyChatApp.viewModel.CreateNewUserViewModel;
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
    private Label errorLabelNachname; // Value injected by FXMLLoader
    
    @FXML // fx:id="errorLabelEmail"
    private Label errorLabelEmail; // Value injected by FXMLLoader
    
    @FXML // fx:id="errorLabelVorname"
    private Label errorLabelVorname; // Value injected by FXMLLoader
    
    @FXML // fx:id="errorLabelPassword"
    private Label errorLabelPassword; // Value injected by FXMLLoader
    
    @FXML // fx:id="errorLabelUsername"
    private Label errorLabelUsername; // Value injected by FXMLLoader

    @FXML // fx:id="txtPasswordRepetition"
    private TextField txtPasswordRepetition; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtVorname"
    private TextField txtVorname; // Value injected by FXMLLoader

    @FXML // fx:id="txtNachname"
    private TextField txtNachname; // Value injected by FXMLLoader

    @FXML // fx:id="txtEmail"
    private TextField txtEmail; // Value injected by FXMLLoader

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
        
        errorLabelUsername.textProperty().bind(viewModel.getErrorMessageUsername());
        errorLabelEmail.textProperty().bind(viewModel.getErrorMessageEmail());
        errorLabelPassword.textProperty().bind(viewModel.getErrorMessagePassword());
        errorLabelNachname.textProperty().bind(viewModel.getErrorMessageNachname());
        errorLabelVorname.textProperty().bind(viewModel.getErrorMessageVorname());
        
        txtVorname.textProperty().bindBidirectional(viewModel.getVorname());
        txtNachname.textProperty().bindBidirectional(viewModel.getNachname());
        txtEmail.textProperty().bindBidirectional(viewModel.getEmail());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        errorLabelUsername.wrapTextProperty().set(true);
        errorLabelPassword.wrapTextProperty().set(true);
        errorLabelEmail.wrapTextProperty().set(true);
        errorLabelVorname.wrapTextProperty().set(true);
        errorLabelNachname.wrapTextProperty().set(true);

        errorLabelEmail.setText("");
        errorLabelNachname.setText("");
        errorLabelPassword.setText("");
        errorLabelUsername.setText("");
        errorLabelVorname.setText("");

        assert btnAbbrechen != null : "fx:id=\"btnAbbrechen\" was not injected: check your FXML file 'CreateNewUserForm.fxml'.";
        assert btnErstellen != null : "fx:id=\"btnErstellen\" was not injected: check your FXML file 'CreateNewUserForm.fxml'.";
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'CreateNewUserForm.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'CreateNewUserForm.fxml'.";
        assert txtPasswordRepetition != null : "fx:id=\"txtPasswordRepetition\" was not injected: check your FXML file 'CreateNewUserForm.fxml'.";

    }

}
