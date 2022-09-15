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
import javafx.scene.layout.AnchorPane;

public class FXMLUserList implements Initializable {

    private UserListViewModel viewModel;

    @FXML
    private Label titleLabel;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="chatText"
    private AnchorPane chatText; // Value injected by FXMLLoader

    @FXML // fx:id="userOutput"
    private ListView<User> userOutput; // Value injected by FXMLLoader

    @FXML // fx:id="neueNachricht"
    private Button neueNachricht; // Value injected by FXMLLoader

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

        assert chatText != null : "fx:id=\"chatText\" was not injected: check your FXML file 'FXMLChatClient.fxml'.";
        assert userOutput != null : "fx:id=\"textOutput\" was not injected: check your FXML file 'FXMLChatClient.fxml'.";
        assert neueNachricht != null : "fx:id=\"neueNachricht\" was not injected: check your FXML file 'FXMLChatClient.fxml'.";
    }

    public void setViewModel(UserListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void bind() {
        userOutput.setItems(viewModel.getUserList());

    }

    @FXML
    void selectUser(ActionEvent event) {
        viewModel.selectUser(getSelectedUser());
    }
    
    @FXML
    void chancelAction(ActionEvent event) {
        viewModel.chancelAction();
    }
    
    private User getSelectedUser(){
        return (User) userOutput.getSelectionModel().getSelectedItem();
    }
}
