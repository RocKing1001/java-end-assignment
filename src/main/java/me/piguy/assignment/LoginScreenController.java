package me.piguy.assignment;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.piguy.assignment.database.KVDatabase;
import me.piguy.assignment.models.User;
import me.piguy.assignment.pane.ContentPane;
import me.piguy.assignment.pane.DashboardPaneController;
import me.piguy.assignment.pane.MainWindowPane;

public class LoginScreenController {
    KVDatabase<String, User> db;

    @FXML
    TextField username;
    @FXML
    TextField password;

    @FXML
    Label errorText;

    @FXML
    Button loginButton = new Button();

    ConfigurationManager config;

    public LoginScreenController(ConfigurationManager config) {
        this.config = config;
        this.db = config.userDB;
    }

    boolean areInputsEmpty() {
        // master and slave
        return username.getText().isEmpty() || password.getText().isEmpty();
    }

    private void showError(String error) {
        errorText.setText(error);
    }

    public void login() {
        // get user
        User user = db.getValue(username.getText());

        if (user == null || !user.checkPassword(password.getText())) {
            showError("Invalid username/password combination");
            return;
        }

        // Load the xml file
        FXMLLoader loader = ContentPane.Dashboard.getLoader();



        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();

            stage.setTitle("Open music dungeon");
            stage.setResizable(true);
            stage.setScene(new Scene(loader.load()));

            MainWindowPane controller = loader.getController();
            controller.init(user, config);

            // Close the window after scene is loaded
            // So if an error occurs, the login window stays open
            stage.close();

            stage.show();

        } catch (Exception e) {
            showError("An internal error has occured, try relaunching the app");
            System.err.printf("Error when loading the window: %s%n", e.getMessage());

            e.printStackTrace();
        }
    }

    public void initialize() {
        loginButton.disableProperty().bind(Bindings.createBooleanBinding(
                this::areInputsEmpty,
                username.textProperty(),
                password.textProperty())
        );
    }
}
