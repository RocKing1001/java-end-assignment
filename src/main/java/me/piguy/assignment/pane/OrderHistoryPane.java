package me.piguy.assignment.pane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import me.piguy.assignment.ConfigurationManager;
import me.piguy.assignment.database.KVDatabase;
import me.piguy.assignment.models.Item;
import me.piguy.assignment.models.Order;
import me.piguy.assignment.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderHistoryPane extends MainWindowPane {

    // Orders table
    @FXML
    public TableView<Order> ordersTable;
    @FXML
    public TableColumn<Date, Order> dateCol;
    @FXML
    public TableColumn<String, Order> customerNameCol;
    @FXML
    public TableColumn<Double, Order> totalPriceCol;
    ObservableList<Order> ordersList;

    // Order items table
    @FXML
    public TableView<Item> orderItemsTable;
    @FXML
    public TableColumn quantityCol;
    @FXML
    public TableColumn nameCol;
    @FXML
    public TableColumn categoryCol;
    @FXML
    public TableColumn priceCol;
    @FXML
    public TableColumn descCol;
    ObservableList<Item> itemsList;

    @Override
    public void init(User user, ConfigurationManager config) {
        super.init(user, config);

        KVDatabase<UUID, Order> orderDB = config.database.getOrders();

        this.ordersList = FXCollections.observableArrayList(orderDB.getAllValues());
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
