/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.aelgict.easyChatApp;

import ch.aelgict.easyChatApp.entity.User;
import ch.aelgict.easyChatApp.model.NachrichtModel;
import ch.aelgict.easyChatApp.model.UserModel;
import ch.aelgict.easyChatApp.model.VolatileModel;
import ch.aelgict.easyChatApp.view.CreateNewUserFormView;
import ch.aelgict.easyChatApp.view.FXMLAnmeldungView;
import ch.aelgict.easyChatApp.view.FXMLChatView;
import ch.aelgict.easyChatApp.view.FXMLUserList;
import ch.aelgict.easyChatApp.view.NachrichtFormView;
import ch.aelgict.easyChatApp.viewModel.AnmeldungViewModel;
import ch.aelgict.easyChatApp.viewModel.ChatViewModel;
import ch.aelgict.easyChatApp.viewModel.CreateNewUserViewModel;
import ch.aelgict.easyChatApp.viewModel.NachrichtFormViewModel;
import ch.aelgict.easyChatApp.viewModel.UserListViewModel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        showAnmeldungForm();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void showNachrichtenverlauf(User user, User me) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/FXMLChat.fxml"));
            Parent root;
            root = loader.load();
            FXMLChatView clientView = loader.getController();
            ChatViewModel viewModel = new ChatViewModel(nachrichtModel, user, me);
            viewModel.setMainApp(this);
            nachrichtModel.addPropertyChangeListener(viewModel);
            clientView.setViewModel(viewModel);
            clientView.bind();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(css);
            stage.getIcons().add(new Image(this.getClass().getResource("view/logo.png").toString()));
            stage.setScene(scene);
            stage.setMinWidth(600);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showNachrichtenForm(User user, User me, int chancelOption) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/NachrichtForm.fxml"));
            Parent root;
            root = loader.load();
            NachrichtFormView formView = loader.getController();
            NachrichtFormViewModel viewModel = new NachrichtFormViewModel(nachrichtModel, user, me);
            viewModel.setChancelOption(chancelOption);
            viewModel.setMainApp(this);
            formView.setViewModel(viewModel);
            formView.bind();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(css);
            stage.setMinWidth(600);
            stage.setScene(scene);
            stage.getIcons().add(new Image(this.getClass().getResource("view/logo.png").toString()));
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
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("view/logo.png")));
            stage.setMinWidth(600);
            stage.setMinHeight(400);
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
            stage.getIcons().add(new Image(this.getClass().getResource("view/logo.png").toString()));
            stage.setMinWidth(600);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showUserList(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/FXMLUserListView.fxml"));
            Parent root;
            root = loader.load();
            FXMLUserList userListView = loader.getController();
            UserListViewModel viewModel = new UserListViewModel(nachrichtModel, user);
            viewModel.setMainApp(this);
            nachrichtModel.addPropertyChangeListener(viewModel);
            userListView.setViewModel(viewModel);
            userListView.bind();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(css);
            stage.getIcons().add(new Image(this.getClass().getResource("view/logo.png").toString()));
            stage.setScene(scene);
            stage.setMinWidth(600);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
