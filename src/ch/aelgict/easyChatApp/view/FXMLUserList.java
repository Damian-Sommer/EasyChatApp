/**
 * Sample Skeleton for 'FXMLChatClient.fxml' Controller Class
 */
package ch.aelgict.easyChatApp.view;

import ch.aelgict.easyChatApp.entity.User;
import ch.aelgict.easyChatApp.viewModel.UserListViewModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FXMLUserList implements Initializable {

    private UserListViewModel viewModel;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="titleLabel"
    private Label titleLabel; // Value injected by FXMLLoader

    @FXML // fx:id="chancelButton"
    private Button chancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="selectUser"
    private Button selectUser; // Value injected by FXMLLoader

    @FXML // fx:id="txtBenutzerSuchen"
    private TextField txtBenutzerSuchen; // Value injected by FXMLLoader

    @FXML // fx:id="btnBenutzerSuchen"
    private Button btnBenutzerSuchen; // Value injected by FXMLLoader

    @FXML // fx:id="errorLabelBenutzer"
    private Label errorLabelBenutzer; // Value injected by FXMLLoader
    
    @FXML // fx:id="userOutput"
    private ListView<User> userOutput; // Value injected by FXMLLoader

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        assert titleLabel != null : "fx:id=\"titleLabel\" was not injected: check your FXML file 'FXMLUserListView.fxml'.";
        assert chancelButton != null : "fx:id=\"chancelButton\" was not injected: check your FXML file 'FXMLUserListView.fxml'.";
        assert selectUser != null : "fx:id=\"selectUser\" was not injected: check your FXML file 'FXMLUserListView.fxml'.";
        assert txtBenutzerSuchen != null : "fx:id=\"txtBenutzerSuchen\" was not injected: check your FXML file 'FXMLUserListView.fxml'.";
        assert btnBenutzerSuchen != null : "fx:id=\"btnBenutzerSuchen\" was not injected: check your FXML file 'FXMLUserListView.fxml'.";
        assert errorLabelBenutzer != null : "fx:id=\"errorLabelBenutzer\" was not injected: check your FXML file 'FXMLUserListView.fxml'.";
        assert userOutput != null : "fx:id=\"userOutput\" was not injected: check your FXML file 'FXMLUserListView.fxml'.";
        
        userOutput.setCellFactory((ListView<User> p) -> {
            ListCell<User> cell = new ListCell<User>() {
                @Override
                protected void updateItem(User user, boolean bln) {
                    super.updateItem(user, bln);
                    if (user != null) {
                        setText(user.getBenutzerName());
                    } else {
                        setText("");
                    }
                }
            };
            return cell;
        });
    }

    public void setViewModel(UserListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void bind() {
        userOutput.setItems(viewModel.getUserList());
        txtBenutzerSuchen.textProperty().bindBidirectional(viewModel.getBenutzername());
        errorLabelBenutzer.textProperty().bindBidirectional(viewModel.getErrorMessageSearchUser());
    }

    @FXML
    void selectUser(ActionEvent event) {
        viewModel.selectUser(getSelectedUser());
    }
    
    @FXML
    void searchBenutzer(ActionEvent event) {
        viewModel.searchUser();
    }
    
    @FXML
    void chancelAction(ActionEvent event) {
        viewModel.chancelAction();
    }
    
    private User getSelectedUser(){
        return (User) userOutput.getSelectionModel().getSelectedItem();
    }
}
