package me.piguy.assignment.popup;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class PopupWindow {
    Stage stage;
    public void showPopup() {
        FXMLLoader loader = new FXMLLoader(Popup.class.getResource(getPopup().fxmlFile));
        Stage stage = new Stage();
        this.stage = stage;
        loader.setController(getController());
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void closePopup() {
        stage.close();
    }

    protected abstract Popup getPopup();

    protected abstract PopupWindow getController();

}
