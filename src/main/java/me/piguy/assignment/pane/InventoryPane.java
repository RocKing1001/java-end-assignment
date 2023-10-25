package me.piguy.assignment.pane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import me.piguy.assignment.ConfigurationManager;
import me.piguy.assignment.database.DBCollections;
import me.piguy.assignment.models.Item;
import me.piguy.assignment.models.User;

import java.util.List;

public class InventoryPane extends MainWindowPane {

    @FXML
    public TableView<Item> tableView;
    @FXML
    public TableColumn<Item, Integer> quantityCol;
    @FXML
    public TableColumn<Item, String> nameCol;
    @FXML
    public TableColumn<Item, String> categoryCol;
    @FXML
    public TableColumn<Item, Double> priceCol;

    ObservableList<Item> items;

    @Override
    public void init(User user, ConfigurationManager config) {
        super.init(user, config);
        this.items = FXCollections.observableArrayList((List<Item>) config.database.getCollection(DBCollections.Products).getAllValues());
        tableView.setItems(items);
    }

    public void initialize() {
        tableView.setEditable(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        quantityCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantityCol.setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setQuantity(e.getNewValue());
            config.database.getProducts().setValue(item.getName(), item);
        });

        nameCol.setCellFactory(col -> new TextFieldTableCell<>());
        nameCol.setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setName(e.getNewValue());
            config.database.getProducts().setValue(item.getName(), item);
        });

        categoryCol.setCellFactory(col -> new TextFieldTableCell<>());
        categoryCol.setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setCategory(e.getNewValue());
            config.database.getProducts().setValue(item.getName(), item);
        });

        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceCol.setOnEditCommit(e -> {
            Item item = e.getRowValue();
            item.setPrice(e.getNewValue());
            config.database.getProducts().setValue(item.getName(), item);
        });
    }

    public void addItem() {
        items.add(new Item(1, "Placeholder", "Placeholder", 0.99, 10));
    }
    public void deleteItem() {
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        items.removeAll(selectedItem);
        config.database.getProducts().dropValue(selectedItem.getName());
    }
}
