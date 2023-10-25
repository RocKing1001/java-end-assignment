package me.piguy.assignment.popup;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public enum Popup {
    AddItem("add-product.fxml", "Add product to order");

    public final String displayName;
    public final String fxmlFile;

    Popup(String fxmlFile, String displayName) {
        this.displayName = displayName;
        this.fxmlFile = fxmlFile;
    }
}
