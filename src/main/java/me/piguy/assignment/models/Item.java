package me.piguy.assignment.models;

import java.io.Serializable;

public class Item implements Serializable {
    private int quantity;
    private String name;
    private String category;
    private double price;
    public final int id;

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

    public Item(int quantity, String name, String category, double price, int id) {
        this.quantity = quantity;
        this.name = name;
        this.category = category;
        this.price = price;
        this.id = id;
    }

    public Item withMoreQuantity(int quantity) {
        return new Item(this.quantity + quantity, this.name, this.category, this.price, this.id);
    }

}
