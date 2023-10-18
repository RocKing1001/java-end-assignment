package me.piguy.assignment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import me.piguy.assignment.models.User;

public class DashboardScreenController {
    User currentUser;

    @FXML
    Label name;

    @FXML
    Label role;

    public DashboardScreenController() {
    }
    public DashboardScreenController(User user) {
        this.currentUser = user;
    }

    public void initialize() {
        name.setText(currentUser.username);
        role.setText(currentUser.role.displayName);
    }
}
