/**
 * Sample Skeleton for 'NachrichtForm.fxml' Controller Class
 */
package ch.aelgict.easyChatApp.view;

import ch.aelgict.easyChatApp.viewModel.NachrichtFormViewModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

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
    private Label errorLabel;
    
    @FXML // fx:id="buttonSenden"
    private Button buttonSenden; // Value injected by FXMLLoader

    @FXML // fx:id="buttonAbbrechen"
    private Button buttonAbbrechen; // Value injected by FXMLLoader

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void bind() {
        txtInput.textProperty().bindBidirectional(viewModel.getMessage());
        errorLabel.textProperty().bindBidirectional(viewModel.getErrorMessage());
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
