package me.piguy.assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;

public class Main extends Application {
    private ScheduledExecutorService scheduler;
    @Override
    public void start(Stage stage) throws IOException {
        // load my config
        ConfigurationManager config = new ConfigurationManager();

        // Initialise DB
        // this happens automatically
        //config.database.initTestData();

        // Set the scheduler field
        // This is so I can gracefully shut it down
        scheduler = config.getScheduler();

        // Load the login window
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-screen.fxml"));
        fxmlLoader.setController(new LoginScreenController(config));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Open music dungeon - Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() {
        scheduler.shutdown();
    }

    public static void main(String[] args) {
        launch();
    }
}