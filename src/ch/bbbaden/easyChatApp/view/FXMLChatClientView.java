/**
 * Sample Skeleton for 'FXMLChatClient.fxml' Controller Class
 */
package ch.bbbaden.easyChatApp.view;

import ch.bbbaden.easyChatApp.entity.Nachricht;
import ch.bbbaden.easyChatApp.viewModel.ChatClientViewModel;
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
import javafx.util.Callback;

public class FXMLChatClientView implements Initializable {

    private ChatClientViewModel viewModel;

    @FXML
    private Label titleLabel;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="chatText"
    private AnchorPane chatText; // Value injected by FXMLLoader

    @FXML // fx:id="textOutput"
    private ListView<Nachricht> textOutput; // Value injected by FXMLLoader

    @FXML // fx:id="neueNachricht"
    private Button neueNachricht; // Value injected by FXMLLoader

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        textOutput.setCellFactory(new Callback<ListView<Nachricht>, ListCell<Nachricht>>() {
            @Override
            public ListCell<Nachricht> call(ListView<Nachricht> p) {
                ListCell<Nachricht> cell = new ListCell<Nachricht>() {
                    @Override
                    protected void updateItem(Nachricht nachricht, boolean bln) {
                        super.updateItem(nachricht, bln);
                        if (nachricht != null) {
                            setText(nachricht.getMessage());
                        } else {
                            setText("");
                        }
                    }
                };
                return cell;
            }
        });

        assert chatText != null : "fx:id=\"chatText\" was not injected: check your FXML file 'FXMLChatClient.fxml'.";
        assert textOutput != null : "fx:id=\"textOutput\" was not injected: check your FXML file 'FXMLChatClient.fxml'.";
        assert neueNachricht != null : "fx:id=\"neueNachricht\" was not injected: check your FXML file 'FXMLChatClient.fxml'.";
    }

    public void setViewModel(ChatClientViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void bind() {
        textOutput.setItems(viewModel.getNachricht());

    }

    @FXML
    void neueNachricht(ActionEvent event) {
        viewModel.addNachricht();
    }
    
    @FXML
    void chancelAction(ActionEvent event) {
        viewModel.chancelAction();
    }

}
