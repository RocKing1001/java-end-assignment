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
    private Pane currentPane;
    @FXML
    private VBox navMenu;

    protected User user;
    protected ConfigurationManager config;

    public void init(User user, ConfigurationManager config) {
        this.user = user;
        this.config = config;
    }

    @FXML
    protected void navDashboard() {
        navigatePane(ContentPane.Dashboard);
    }

    @FXML
    protected void navCreateOrder() {
        navigatePane(ContentPane.CreateOrder);
    }

    @FXML
    protected void navProductInventory() {
        navigatePane(ContentPane.ProductInventory);
    }

    @FXML
    protected void navOrderHistory() {
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
