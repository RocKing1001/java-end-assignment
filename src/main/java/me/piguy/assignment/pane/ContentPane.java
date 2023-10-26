package me.piguy.assignment.pane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public enum ContentPane {
    Dashboard("dashboard.fxml", "Dashboard"),
    CreateOrder("create-order.fxml", "Create Order"),
    ProductInventory("product-inventory.fxml", "Product inventory"),
    OrderHistory("order-history.fxml", "Order history");

    private final String displayName;
    private final String fxmlFile;


    public FXMLLoader getLoader() {
        return new FXMLLoader(ContentPane.class.getResource(getFxmlFile()));
    }

    @Deprecated
    public Scene getScene() {
        FXMLLoader loader = getLoader();
        try {
            return new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MainWindowPane getController() {
        FXMLLoader loader = getLoader();
        return loader.getController();
    }

    ContentPane(String fxmlFile, String displayName) {
        this.displayName = displayName;
        this.fxmlFile = fxmlFile;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }
}
