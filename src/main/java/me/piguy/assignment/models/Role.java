package me.piguy.assignment.models;

public enum Role {
    IT("IT Technician"),
    Sales;

    public final String displayName;
    Role(String displayName) {
        this.displayName = displayName;
    }
    Role () {
        this.displayName = this.toString();
    }

}
