package me.piguy.assignment;

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

    public LoginScreenController(KVDatabase<String, User> db) {
        this.db = db;
    }

    boolean areInputsEmpty() {
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


        // Load the dashboard
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-screen.fxml"));
        fxmlLoader.setController(new MainScreenController(user));

        try {
            // I put this before stage.close()
            // because I dont want login window to close
            // if this does not load
            Scene scene = new Scene(fxmlLoader.load());

            // Close the window
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();

            stage.setTitle("Open music dungeon");
            stage.setResizable(true);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            showError("An internal error has occured, try relaunching the app");
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
