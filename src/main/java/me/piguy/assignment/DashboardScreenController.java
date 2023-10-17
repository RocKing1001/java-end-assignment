package me.piguy.assignment;

import me.piguy.assignment.models.User;

public class DashboardScreenController {
    User currentUser;
    public DashboardScreenController(User currentUser) {
        this.currentUser = currentUser;
    }
}
