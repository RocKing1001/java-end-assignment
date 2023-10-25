package me.piguy.assignment.popup;

public enum Popup {
    AddItem("add-product.fxml", "Add product to order");

    public final String displayName;
    public final String fxmlFile;

    Popup(String fxmlFile, String displayName) {
        this.displayName = displayName;
        this.fxmlFile = fxmlFile;
    }
}
