package me.piguy.assignment.pane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import me.piguy.assignment.ConfigurationManager;
import me.piguy.assignment.models.User;

import java.io.IOException;

public abstract class MainWindowPane {
    @FXML
    Pane currentPane;
    @FXML
    VBox navMenu;

    User user;
    ConfigurationManager config;

    public void init(User user, ConfigurationManager config) {
        this.user = user;
        this.config = config;
    }

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
        Stage stage = (Stage) navMenu.getScene().getWindow();
        FXMLLoader loader = pane.getLoader();

        try {
            stage.setScene(new Scene(loader.load()));
            MainWindowPane controller = loader.getController();
            controller.init(user, config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
