package me.piguy.assignment.pane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import me.piguy.assignment.ConfigurationManager;
import me.piguy.assignment.database.KVPersistentDB;
import me.piguy.assignment.models.Item;
import me.piguy.assignment.models.Order;
import me.piguy.assignment.models.User;

import java.util.ArrayList;
import java.util.UUID;

public class OrderHistoryPane extends MainWindowPane {

    // Orders table
    @FXML
    private TableView<Order> ordersTable;

    // Order items table
    @FXML
    private TableView<Item> orderItemsTable;
    private ObservableList<Item> itemsList;

    @Override
    public void init(User user, ConfigurationManager config) {
        super.init(user, config);

        KVPersistentDB<UUID, Order> orderDB = config.getDatabase().getOrders();

        ObservableList<Order> ordersList = FXCollections.observableArrayList(orderDB.getAllValues());
        ordersTable.setItems(ordersList);
        ordersTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        this.itemsList = FXCollections.observableArrayList(new ArrayList<>());
        orderItemsTable.setItems(itemsList);
    }


    public void initialize() {
        ordersTable.getSelectionModel().selectedItemProperty().addListener((observer, oldSelection, newSelection) -> {
            if (newSelection == null) {
                orderItemsTable.getSelectionModel().clearSelection();
            } else {
                itemsList.setAll(newSelection.getOrders());
            }
        });
    }
}
