package ch.aelgict.easychatapp;

import ch.aelgict.easychatapp.entity.User;
import ch.aelgict.easychatapp.model.InternetConnection;
import ch.aelgict.easychatapp.model.NachrichtModel;
import ch.aelgict.easychatapp.model.UserModel;
import ch.aelgict.easychatapp.model.VolatileModel;
import ch.aelgict.easychatapp.view.*;
import ch.aelgict.easychatapp.viewModel.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainApp extends Application {

    Stage stage;
    NachrichtModel nachrichtModel;
    UserModel userModel;
    private String css = "";

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        css = Objects.requireNonNull(MainApp.class.getResource("styles.css")).toExternalForm();
        this.stage = stage;
        VolatileModel model = new VolatileModel();
        nachrichtModel = model;
        userModel = model;
        System.out.println("Is Internet Connected: " + InternetConnection.isConnected());
        showAnmeldungForm();
    }

    public void showNachrichtenverlauf(User user, User me) {
        System.out.println("showNachrichtenverlauf");
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("FXMLChat.fxml"));
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
            stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("logo.png")).toString()));
            stage.setScene(scene);
            stage.setMinWidth(600);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showNachrichtenForm(User user, User me, int chancelOption) {
        try {

            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("NachrichtForm.fxml"));
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
            stage.getIcons().add(new Image(this.getClass().getResource("logo.png").toString()));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showAnmeldungForm() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("FXMLAnmeldung.fxml"));
            Parent root;
            root = loader.load();
            FXMLAnmeldungView formView = loader.getController();
            AnmeldungViewModel viewModel = new AnmeldungViewModel(userModel);
            viewModel.setMainApp(this);
            formView.setViewModel(viewModel);
            formView.bind();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(css);
            stage.getIcons().add(new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream("logo.png"))));
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
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("CreateNewUserForm.fxml"));
            Parent root;

            root = loader.load();

            CreateNewUserFormView formView = loader.getController();
            CreateNewUserViewModel viewModel = new CreateNewUserViewModel(userModel);
            viewModel.setMainApp(this);
            formView.setViewModel(viewModel);
            formView.bind();
            Scene scene = new Scene(root);

            scene.getStylesheets().add(css);
            stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("logo.png")).toString()));
            stage.setMinWidth(600);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showUserList(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("FXMLUserListView.fxml"));
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
            stage.getIcons().add(new Image(Objects.requireNonNull(MainApp.class.getResource("logo.png")).toString()));
            stage.setScene(scene);
            stage.setMinWidth(600);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}