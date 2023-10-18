package me.piguy.assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.piguy.assignment.database.KVDatabase;
import me.piguy.assignment.models.Role;
import me.piguy.assignment.models.User;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // load my config
        ConfigurationManager config = new ConfigurationManager();
        KVDatabase<String, User> userdb = config.userDB;

        // Initialise DB
        userdb.setValue("joey_nonsensejp", new User("Joey Bizinger", Role.Sales, "nonsense"));
        userdb.setValue("cdawgva", new User("Connor Colquhoun", Role.Sales, "jumpking"));
        userdb.setValue("notgrant", new User("Garnt \"Grant\" Maneetapho", Role.IT, "isekai"));
        userdb.setValue("1", new User("Garnt \"Grant\" Maneetapho", Role.IT, "1"));

        // Load the login window
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-screen.fxml"));
        fxmlLoader.setController(new LoginScreenController(userdb));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Open music dungeon - Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}