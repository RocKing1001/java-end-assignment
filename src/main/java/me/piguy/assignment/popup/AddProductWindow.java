package me.piguy.assignment.popup;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import me.piguy.assignment.database.CollectionDatabase;
import me.piguy.assignment.database.DBCollections;
import me.piguy.assignment.models.Item;

import java.util.List;

public class AddProductWindow extends PopupWindow {
    private final Popup popup = Popup.AddItem;
    private final ObservableList<Item> ordersList;

    @FXML
    TableView<Item> tableView;

    public ObservableList<Item> items;

    /**
     * @param ordersList (passed by reference) added items will modify the class directly
     * @param database   database ._.
     */
    public AddProductWindow(ObservableList<Item> ordersList, CollectionDatabase database) {
        List<Item> itemsList = (List<Item>) database.getCollection(DBCollections.Products).getAllValues();
        this.items = FXCollections.observableArrayList(itemsList);
        this.ordersList = ordersList;
    }

    public void addToOrder() {
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        ordersList.add(selectedItem);
    }

    @Override
    protected Popup getPopup() {
        return popup;
    }

    @Override
    protected PopupWindow getController() {
        return this;
    }

    public void initialize() {
        tableView.setItems(items);
    }
}
