package me.piguy.assignment.pane;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import me.piguy.assignment.ConfigurationManager;
import me.piguy.assignment.models.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class DashboardPaneController extends MainWindowPane {
    @FXML
    Label username;
    @FXML
    Label role;
    @FXML
    Label time;

    public DashboardPaneController() {
        super();
    }

    @Override
    public void init(User user, ConfigurationManager config) {
        super.init(user, config);

        username.setText(user.username);
        role.setText(user.role.displayName);

        config.scheduler.scheduleAtFixedRate(() -> Platform.runLater(() ->
                        time.setText("It is now: " + LocalDateTime.now()
                                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))),
                0, 1, TimeUnit.SECONDS);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
