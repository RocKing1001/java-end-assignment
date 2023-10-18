package me.piguy.assignment;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.piguy.assignment.database.KVDatabase;
import me.piguy.assignment.models.User;

import java.io.IOException;

public class LoginScreenController {
    KVDatabase<String, User> db;

    @FXML
    TextField username;
    @FXML
    TextField password;

    @FXML
    Button loginButton = new Button();

    public LoginScreenController() {
    }

    public LoginScreenController(KVDatabase<String, User> db) {
        this.db = db;
    }

    boolean areInputsEmpty() {
        return username.getText().isEmpty() || password.getText().isEmpty();
    }

    public void login() {
        // get user
        User user = db.getValue(username.getText());

        if (user == null) {
            username.setText("");
            return;
        }

        // reset the password field if password is incorrect
        if (!user.checkPassword(password.getText())) {
            password.setText("");
            return;
        }

        // Close the window
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();

        // Load the dashboard
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dashboard-screen.fxml"));
        fxmlLoader.setController(new DashboardScreenController(user));

        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Open music dungeon");
            stage.setResizable(true);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            // I don't really do anything
            // Like, what should I do? ask the user to relaunch
            // They will do that anyway so not my problem
            // I will log to the console though, so terminal users
            // will know what's wrong
            System.err.printf("Error when loading the window: %s%n", e.getMessage());
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
