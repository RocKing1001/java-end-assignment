package me.piguy.assignment.models;

public class Order {
    private int quantity;
    private String name;
    private String category;
    private double price;

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

    public Order(int quantity, String name, String category, double price) {
        this.quantity = quantity;
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
