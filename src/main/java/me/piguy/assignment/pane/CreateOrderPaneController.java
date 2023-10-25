package me.piguy.assignment.pane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import me.piguy.assignment.ConfigurationManager;
import me.piguy.assignment.database.DBCollections;
import me.piguy.assignment.database.KVDatabase;
import me.piguy.assignment.models.Item;
import me.piguy.assignment.models.User;
import me.piguy.assignment.popup.AddProductWindow;

import java.util.ArrayList;

public class CreateOrderPaneController extends MainWindowPane {
    @FXML
    public TableView<Item> tableOfOrders;

    public ObservableList<Item> orders;

    @Override
    public void init(User user, ConfigurationManager config) {
        super.init(user, config);

        KVDatabase<String, Item> ordersList =
                (KVDatabase<String, Item>) config.database.getCollection(DBCollections.Products);

        this.orders = FXCollections.observableArrayList(new ArrayList<>());
        tableOfOrders.setItems(orders);
    }

    public void addItem() {
        AddProductWindow addItemWindow = new AddProductWindow(orders, config.database);
        addItemWindow.showPopup();
    }

    public void deleteSelected(ActionEvent actionEvent) {
        ObservableList<Item> selectedItem = tableOfOrders.getSelectionModel().getSelectedItems();
        orders.removeAll(selectedItem);
    }
}
