package me.piguy.assignment.models;

public enum Role {
    Sales;

    final String displayName;
    Role(String displayName) {
        this.displayName = displayName;
    }
    Role () {
        this.displayName = this.toString();
    }

}
