/**
 * Sample Skeleton for 'FXMLAnmeldung.fxml' Controller Class
 */
package ch.bbbaden.chatClient.view;

import ch.bbbaden.chatClient.viewModel.AnmeldungViewModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FXMLAnmeldungView implements Initializable {

    private AnmeldungViewModel viewModel;

    @FXML
    private Label titleLabel;

    @FXML
    private AnchorPane backgroundAnchorpane;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAbbrechen"
    private Button btnAbbrechen; // Value injected by FXMLLoader

    @FXML // fx:id="btnEinloggen"
    private Button btnEinloggen; // Value injected by FXMLLoader

    @FXML // fx:id="txtUsername"
    private TextField txtUsername; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassword"
    private TextField txtPassword; // Value injected by FXMLLoader

    @FXML // fx:id="errorLabel"
    private Label errorLabel; // Value injected by FXMLLoader

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorLabel.wrapTextProperty().set(true);

        assert btnAbbrechen != null : "fx:id=\"btnAbbrechen\" was not injected: check your FXML file 'FXMLAnmeldung.fxml'.";
        assert btnEinloggen != null : "fx:id=\"btnEinloggen\" was not injected: check your FXML file 'FXMLAnmeldung.fxml'.";
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'FXMLAnmeldung.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'FXMLAnmeldung.fxml'.";
    }

    public void bind() {
        txtUsername.textProperty().bindBidirectional(viewModel.getUserName());
        txtPassword.textProperty().bindBidirectional(viewModel.getPassword());
        errorLabel.textProperty().bind(viewModel.getErrorMessage());
    }

    @FXML
    void loginAction(ActionEvent event) {
        viewModel.saveAction();
    }

    @FXML
    void chancelAction(ActionEvent event) {
        viewModel.chancelAction();
    }

    public void setViewModel(AnmeldungViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
