/**
 * Sample Skeleton for 'NachrichtForm.fxml' Controller Class
 */
package ch.bbbaden.chatClient.view;

import ch.bbbaden.chatClient.viewModel.NachrichtFormViewModel;
import ch.bbbaden.chatClient.viewModel.ViewModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NachrichtFormView implements Initializable {

    private NachrichtFormViewModel viewModel;

    @FXML
    private Label titleLabel;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtInput"
    private TextArea txtInput; // Value injected by FXMLLoader

    @FXML
    private TextField txtUsername;
    
    @FXML
    private CheckBox checkBox;
    
    @FXML // fx:id="labelError"
    private Label labelError; // Value injected by FXMLLoader

    @FXML // fx:id="buttonSenden"
    private Button buttonSenden; // Value injected by FXMLLoader

    @FXML // fx:id="buttonAbbrechen"
    private Button buttonAbbrechen; // Value injected by FXMLLoader

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelError.wrapTextProperty().set(true);
    }

    public void bind() {
        txtInput.textProperty().bindBidirectional(viewModel.getMessage());
        labelError.textProperty().bind(viewModel.getErrorMessage());
        txtUsername.textProperty().bindBidirectional(viewModel.getUsername());
        checkBox.selectedProperty().bindBidirectional(viewModel.getUsingUsername());
    }

    @FXML
    void chancelAction(ActionEvent event) {
        viewModel.chancelAction();
    }

    @FXML
    void saveAction(ActionEvent event) {
        viewModel.saveAction();
    }

    public void setViewModel(NachrichtFormViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
