package me.piguy.assignment.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Item implements Serializable {
    private int quantity;
    private String name;
    private String category;
    private double price;
    private String description;

    public final UUID id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Item(int quantity, String name, String category, double price) {
        this.quantity = quantity;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = "";
        this.id = UUID.randomUUID();
    }

    public Item(int quantity, String name, String category, double price, String description) {
        this.quantity = quantity;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.id = UUID.randomUUID();
    }

    public void addQuantity(int quantity) {
        this.setQuantity(this.quantity + quantity);
    }
}
