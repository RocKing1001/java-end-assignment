package me.piguy.assignment.pane;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import me.piguy.assignment.models.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardPaneController extends MainWindowPane {

    @FXML
    Label username;
    @FXML
    Label role;
    @FXML
    Label time;

    public DashboardPaneController(User user) {
        super(user);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void initialize() {
        username.setText(user.username);
        role.setText(user.role.displayName);
        time.setText("It is now: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }
}
