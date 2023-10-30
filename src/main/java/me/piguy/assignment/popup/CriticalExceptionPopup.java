package me.piguy.assignment.popup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public abstract class CriticalExceptionPopup extends PopupWindow {
    @FXML
    private Label exceptionTitle;
    @FXML
    private Label exceptionContent;


    protected abstract String getExceptionTitle();
    protected abstract String getExceptionContent();

    @Override
    protected Popup getPopup() {
        return Popup.CriticalException;
    }

    @Override
    protected PopupWindow getController() {
        return this;
    }

    @FXML
    private void close(ActionEvent ignored) {
        System.exit(0);
    }

    @FXML
    private void initialize() {
        this.exceptionTitle.setText(getExceptionTitle());
        this.exceptionContent.setText(getExceptionContent());
    }
}
