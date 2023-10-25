package me.piguy.assignment.models;

import me.piguy.assignment.ConfigurationManager;
import me.piguy.assignment.encryption.Encryption;

import java.util.Objects;

public class User {
    public String username;
    public Role role;
    /**
     * This will be encrypted usually
     */
    private String password;

    Encryption encryption;

    public boolean checkPassword(String password) {
        return Objects.equals(this.password, password);
    }

    public void setPassword(String currentPassword, String newPassword) {
        if (this.password.equals(encryption.encrypt(currentPassword))) {
            this.password = encryption.encrypt(newPassword);
        } else {
            throw new IllegalArgumentException("Current password is incorrect");
        }
    }

    public User(String name, Role role, String password) {
        // let config
        ConfigurationManager config = new ConfigurationManager();

        this.username = name;
        this.role = role;
        // I really wished I could use static classes for my config manager
        // feels so wrong to have to init a class just to get a type from it
        this.encryption = config.encryption;
        this.password = encryption.encrypt(password);
    }
}
