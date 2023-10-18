package me.piguy.assignment.pane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import me.piguy.assignment.models.User;

import java.io.IOException;

public abstract class MainWindowPane {
    @FXML
    Pane currentPane;
    @FXML
    VBox navMenu;

    User user;

    public MainWindowPane(User user) {
        this.user = user;
    }

    public void navDashboard() {
        navigatePane(ContentPane.Dashboard, new DashboardPaneController(user));
    }
    public void navCreateOrder() {
        navigatePane(ContentPane.CreateOrder, new DashboardPaneController(user));
    }

    public void navProductInventory() {
        navigatePane(ContentPane.ProductInventory, new DashboardPaneController(user));
    }

    public void navOrderHistory() {
        navigatePane(ContentPane.OrderHistory, new DashboardPaneController(user));
    }

    void navigatePane(ContentPane pane, MainWindowPane controller) {
        Stage stage = (Stage)navMenu.getScene().getWindow();
        FXMLLoader loader = pane.getLoader();
        loader.setController(controller);
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
