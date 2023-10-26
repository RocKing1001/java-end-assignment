package me.piguy.assignment.pane;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import me.piguy.assignment.ConfigurationManager;
import me.piguy.assignment.database.CollectionDatabase;
import me.piguy.assignment.database.DBCollections;
import me.piguy.assignment.database.KVPersistentDB;
import me.piguy.assignment.models.Item;
import me.piguy.assignment.models.Order;
import me.piguy.assignment.models.User;
import me.piguy.assignment.popup.AddProductWindow;
import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


public class CreateOrderPaneController extends MainWindowPane {
    @FXML
    public TableView<Item> tableOfOrders;
    @FXML
    public Button deleteBtn;
    @FXML
    public Button createOrderBtn;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField emailAddress;
    @FXML
    public TextField lastName;
    @FXML
    public TextField firstName;


    public ObservableList<Item> orders;

    // This database is a clone of the original items database
    // the original will not be modified unless an order is created
    // this saves my sanity by not having to repopulate the database
    // if an order was not created, which is the alternative
    // I was going to make some form of a staging system, where the database
    // class holds a copy of itself, but such a feature is only needed here
    // please gods of memory, please don't smite me
    public CollectionDatabase clonedDb;

    @Override
    public void init(User user, ConfigurationManager config) {
        super.init(user, config);

        this.orders = FXCollections.observableArrayList(new ArrayList<>());
        this.tableOfOrders.setItems(orders);
        this.clonedDb = SerializationUtils.clone(config.database);
    }

    public void addItem() {
        AddProductWindow addItemWindow = new AddProductWindow(orders, clonedDb);
        addItemWindow.showPopup();
    }

    public void deleteSelected(ActionEvent actionEvent) {
        Item selectedItem = tableOfOrders.getSelectionModel().getSelectedItem();
        KVPersistentDB<UUID, Item> products = (KVPersistentDB<UUID, Item>) clonedDb.getCollection(DBCollections.Products);


        Item newValue = products.getValue(selectedItem.id);
        newValue.addQuantity(selectedItem.getQuantity());

        products.setValue(newValue.id, newValue);

        orders.remove(selectedItem);
    }

    private void clearAllFields() {
        orders.clear();
        firstName.clear();
        lastName.clear();
        emailAddress.clear();
        phoneNumber.clear();
    }

    public void createOrder() {
        config.database.set(DBCollections.Products, clonedDb.getCollection(DBCollections.Products));

        KVPersistentDB<UUID, Order> orderDB = config.database.getOrders();

        Order newOrder = new Order(
                new Date(),
                firstName.getText() + " " + lastName.getText(),
                emailAddress.getText(),
                phoneNumber.getText(),
                orders.stream().toList()
        );
        orderDB.setValue(newOrder.getId(), newOrder);

        clearAllFields();
    }

    private boolean isFormEmpty() {
        return firstName.getText().isEmpty() ||
                lastName.getText().isEmpty() ||
                emailAddress.getText().isEmpty() ||
                phoneNumber.getText().isEmpty();
    }

    private boolean isNotSelected() {
        return tableOfOrders.getSelectionModel().getSelectedItem() == null;
    }

    public void initialize() {
        tableOfOrders.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        deleteBtn.disableProperty().bind(Bindings.createBooleanBinding(
                        this::isNotSelected,
                        tableOfOrders.getSelectionModel().selectedItemProperty()
                )
        );
        createOrderBtn.disableProperty().bind(Bindings.createBooleanBinding(this::isFormEmpty,
                firstName.textProperty(), lastName.textProperty(), emailAddress.textProperty(), phoneNumber.textProperty()));
    }
}
