package me.piguy.assignment.pane;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public enum ContentPane {
    Dashboard("dashboard.fxml", "Dashboard"),
    CreateOrder("create-order.fxml", "Create Order"),
    ProductInventory("product-inventory.fxml", "Product inventory"),
    OrderHistory("order-history.fxml", "Order history");

    public final String displayName;
    Pane pane;

    private void loadPane(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(ContentPane.class.getResource(fxmlFile));
        this.pane = loader.load();
        System.out.println("HELLO");
    }

    ContentPane(String fxmlFile, String displayName) {
        this.displayName = displayName;
        try {
            loadPane(fxmlFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Pane getPane() {
        return pane;
    }
}
