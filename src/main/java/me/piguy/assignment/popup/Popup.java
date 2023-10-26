package me.piguy.assignment.popup;

public enum Popup {
    AddItem("add-product.fxml", "Add product to order");

    private final String displayName;
    private final String fxmlFile;

    Popup(String fxmlFile, String displayName) {
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
