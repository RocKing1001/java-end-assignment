package me.piguy.assignment;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.piguy.assignment.models.User;

import java.io.IOException;

public class LoginScreenController {
    @FXML
    TextField username;
    @FXML
    TextField password;

    @FXML
    Button loginButton = new Button();

    boolean areInputsEmpty() {
        return username.getText().isEmpty() || password.getText().isEmpty();
    }

    public void login() throws IOException {
        // Close the window
        Stage stage = (Stage)loginButton.getScene().getWindow();
        stage.close();

        // Load the dashboard
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dashboard-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        fxmlLoader.setController(new DashboardScreenController(new User()));
        stage.setTitle("Open music dungeon");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        loginButton.disableProperty().bind(Bindings.createBooleanBinding(
                this::areInputsEmpty,
                username.textProperty(),
                password.textProperty())
        );
    }
}
