package me.piguy.assignment.pane;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.piguy.assignment.models.Order;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderPaneController extends MainWindowPane {
    @FXML
    public TableView<Order> tableOfOrders;

    public List<Order> ordersList = new ArrayList<>();
    public ObservableList<Order> orders;

    private void initialiseOrders() {
        ordersList.add(new Order(1, "Squire Stratocaster", "Guitar", 299.99));
    }

    public void initialize() {
        initialiseOrders();
        this.orders = FXCollections.observableArrayList(ordersList);
        tableOfOrders.setItems(orders);
    }
}
