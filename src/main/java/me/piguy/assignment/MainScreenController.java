package me.piguy.assignment;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import me.piguy.assignment.models.User;
import me.piguy.assignment.pane.ContentPane;

public class MainScreenController {
    User currentUser;

    @FXML
    Pane currentPane;

    @FXML
    VBox navMenu;

    @FXML
    Label dashboardUsernameDisplay;

    public MainScreenController() {
    }

    public MainScreenController(User user) {
        this.currentUser = user;
    }

    // navigation pane
    public void navDashboard() {
        navigatePane(ContentPane.Dashboard);
    }

    public void navCreateOrder() {
        navigatePane(ContentPane.CreateOrder);
    }

    public void navProductInventory() {
        navigatePane(ContentPane.ProductInventory);
    }

    public void navOrderHistory() {
        navigatePane(ContentPane.OrderHistory);
    }

    void navigatePane(ContentPane pane) {
        HBox hbox = (HBox) navMenu.getParent();

        ObservableList<Node> children = hbox.getChildren();

        children.remove(currentPane);

        currentPane = pane.getPane();
        children.add(currentPane);
    }

    public void initialize() {
        navDashboard();
    }
}
