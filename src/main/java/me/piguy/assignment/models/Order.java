package me.piguy.assignment.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order implements Serializable {
    private final Date date;

    private final String name;
    private final String email;
    private final String number;
    private final List<Item> orders;


    private final UUID id;

    public Order(Date date, String name, String email, String number, List<Item> orders) {
        this.date = date;
        this.name = name;
        this.email = email;
        this.number = number;
        this.orders = orders;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public double getTotal() {
        double total = 0;
        for (Item item : orders) {
            total += item.getPrice() * item.getQuantity();
        }

        return Math.round(total * 100.0) / 100.0;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public List<Item> getOrders() {
        return orders;
    }

}
