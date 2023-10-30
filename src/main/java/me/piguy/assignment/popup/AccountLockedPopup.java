package me.piguy.assignment.popup;

import javafx.scene.control.Label;

public class AccountLockedPopup extends CriticalExceptionPopup {
    private final String title = "Account locked";
    private final String content = "The account you were trying to access has been locked";

    @Override
    protected String getExceptionTitle() {
        return title;
    }

    @Override
    protected String getExceptionContent() {
        return content;
    }
}
