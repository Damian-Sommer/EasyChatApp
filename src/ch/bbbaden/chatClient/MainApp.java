/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.chatClient;

import ch.bbbaden.chatClient.model.NachrichtModel;
import ch.bbbaden.chatClient.model.UserModel;
import ch.bbbaden.chatClient.model.VolatileModel;
import ch.bbbaden.chatClient.view.CreateNewUserFormView;
import ch.bbbaden.chatClient.view.FXMLAnmeldungView;
import ch.bbbaden.chatClient.view.FXMLChatClientView;
import ch.bbbaden.chatClient.view.NachrichtFormView;
import ch.bbbaden.chatClient.viewModel.AnmeldungViewModel;
import ch.bbbaden.chatClient.viewModel.ChatClientViewModel;
import ch.bbbaden.chatClient.viewModel.CreateNewUserViewModel;
import ch.bbbaden.chatClient.viewModel.NachrichtFormViewModel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author da_so
 */
public class MainApp extends Application {

    Stage stage;
    NachrichtModel nachrichtModel;
    UserModel userModel;
    private String css = "";

    @Override
    public void start(Stage stage) throws Exception {
        css = getClass().getResource("view/styles.css").toExternalForm();
        this.stage = stage;
        VolatileModel model = new VolatileModel();
        nachrichtModel = model;
        userModel = model;
        //showAnmeldungForm();
        showNachrichtenForm();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void showNachrichten() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/FXMLChatClient.fxml"));
            Parent root;
            root = loader.load();
            FXMLChatClientView clientView = loader.getController();
            ChatClientViewModel viewModel = new ChatClientViewModel(nachrichtModel);
            viewModel.setMainApp(this);
            nachrichtModel.addPropertyChangeListener(viewModel);
            clientView.setViewModel(viewModel);
            clientView.bind();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.setMinWidth(600);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showNachrichtenForm() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/NachrichtForm.fxml"));
            Parent root;
            root = loader.load();
            NachrichtFormView formView = loader.getController();
            NachrichtFormViewModel viewModel = new NachrichtFormViewModel(nachrichtModel);
            viewModel.setMainApp(this);
            formView.setViewModel(viewModel);
            formView.bind();
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            scene.getStylesheets().add(css);
            stage.setMinWidth(600);
            stage.setScene(scene);
            
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showAnmeldungForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/FXMLAnmeldung.fxml"));
            Parent root;
            root = loader.load();
            FXMLAnmeldungView formView = loader.getController();
            AnmeldungViewModel viewModel = new AnmeldungViewModel(userModel);
            viewModel.setMainApp(this);
            formView.setViewModel(viewModel);
            formView.bind();
            Scene scene = new Scene(root);

            scene.getStylesheets().add(css);
            stage.setMinWidth(600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showCreateNewUserForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/CreateNewUserForm.fxml"));
            Parent root;

            root = loader.load();

            CreateNewUserFormView formView = loader.getController();
            CreateNewUserViewModel viewModel = new CreateNewUserViewModel(userModel);
            viewModel.setMainApp(this);
            formView.setViewModel(viewModel);
            formView.bind();
            Scene scene = new Scene(root);

            scene.getStylesheets().add(css);
            stage.setMinWidth(600);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
