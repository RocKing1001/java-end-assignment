package me.piguy.assignment.popup;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import me.piguy.assignment.database.CollectionDatabase;
import me.piguy.assignment.database.DBCollections;
import me.piguy.assignment.database.KVDatabase;
import me.piguy.assignment.models.Item;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.List;

public class AddProductWindow extends PopupWindow {
    private final Popup popup = Popup.AddItem;
    private final ObservableList<Item> ordersList;

    @FXML
    TableView<Item> tableView;
    @FXML
    Button addOrderBtn;
    @FXML
    TextField quantity;

    private ObservableList<Item> items;
    private CollectionDatabase database;


    /**
     * @param ordersList (passed by reference) added items will modify the class directly
     * @param database   database ._.
     */
    public AddProductWindow(ObservableList<Item> ordersList, CollectionDatabase database) {
        List<Item> itemsList = (List<Item>) database.getCollection(DBCollections.Products).getAllValues();
        this.database = database;
        this.items = FXCollections.observableArrayList(itemsList);
        this.ordersList = ordersList;
    }

    private int getQuantityToAdd() {
        return Integer.parseInt(quantity.getText());
    }

    private boolean isQuantityValid() {
        try {
            return getQuantityToAdd() > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Item getSelectedItem() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    private boolean isButtonDisabled() {
        Item selectedItem = getSelectedItem();
        if (selectedItem == null) {
            return true;
        } else if (!isQuantityValid()) {
            return true;
        } else return selectedItem.getQuantity() < getQuantityToAdd();
    }

    public void cancel() {
        this.closePopup();
    }

    public void addToOrder() {
        if (!isQuantityValid()) return;

        Item selectedItem = getSelectedItem();

        for (int i = 0; i < ordersList.size(); i++) {
            Item item = ordersList.get(i);
            if (item.id == selectedItem.id) {
                item.setQuantity(item.getQuantity() + getQuantityToAdd());
                ordersList.set(i, item);
                return;
            }
        }

        selectedItem.setQuantity(selectedItem.getQuantity() - getQuantityToAdd());

        Item itemToAdd = SerializationUtils.clone(selectedItem);
        itemToAdd.setQuantity(getQuantityToAdd());

        ordersList.add(itemToAdd);

        // reduce the quantity in the db
        KVDatabase<String, Item> productDb = (KVDatabase<String, Item>) database.getCollection(DBCollections.Products);
        productDb.setValue(selectedItem.getName(), selectedItem);

        this.closePopup();
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
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        addOrderBtn.disableProperty().bind(Bindings.createBooleanBinding(this::isButtonDisabled,
                quantity.textProperty(), tableView.getSelectionModel().selectedItemProperty()));
    }
}
