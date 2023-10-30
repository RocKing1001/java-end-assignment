package me.piguy.assignment.pane;

import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import me.piguy.assignment.ConfigurationManager;
import me.piguy.assignment.database.DBCollections;
import me.piguy.assignment.models.Item;
import me.piguy.assignment.models.User;

import java.util.ArrayList;
import java.util.List;

public class InventoryPane extends MainWindowPane {

    @FXML
    private TextField searchField;
    @FXML
    private TableView<Item> tableView;
    @FXML
    private TableColumn<Item, Integer> quantityCol;
    @FXML
    private TableColumn<Item, String> nameCol;
    @FXML
    private TableColumn<Item, String> categoryCol;
    @FXML
    private TableColumn<Item, Double> priceCol;
    @FXML
    private TableColumn<Item, String> descCol;

    private ObservableList<Item> items;

    @Override
    public void init(User user, ConfigurationManager config) {
        super.init(user, config);
        this.items = FXCollections.observableArrayList((List<Item>) config.getDatabase().getCollection(DBCollections.Products).getAllValues());
        tableView.setItems(items);
    }

    public void initialize() {
        tableView.setEditable(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        quantityCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantityCol.setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setQuantity(e.getNewValue());
            config.getDatabase().getProducts().setValue(item.id, item);
        });

        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setName(e.getNewValue());
            config.getDatabase().getProducts().setValue(item.id, item);
        });

        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryCol.setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setCategory(e.getNewValue());
            config.getDatabase().getProducts().setValue(item.id, item);
        });

        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceCol.setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setPrice(e.getNewValue());
            config.getDatabase().getProducts().setValue(item.id, item);
        });

        descCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descCol.setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setDescription(e.getNewValue());
            config.getDatabase().getProducts().setValue(item.id, item);
        });

        // search
        searchField.textProperty().addListener((observer, oldValue, newValue) -> {
            System.out.println(newValue);
            if (newValue.isEmpty()) {
                tableView.setItems(items);
            } else {
                tableView.setItems(
                        items.filtered((e) -> e.getName().toLowerCase().contains(newValue.toLowerCase()))
                );
            }
        });
    }

    public void addItem() {
        items.add(new Item(1, "Placeholder", "Placeholder", 0.99));
    }

    public void deleteItem() {
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        items.removeAll(selectedItem);
        config.getDatabase().getProducts().dropValue(selectedItem.id);
    }
}
