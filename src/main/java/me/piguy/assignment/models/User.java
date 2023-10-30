package me.piguy.assignment.models;

import me.piguy.assignment.ConfigurationManager;
import me.piguy.assignment.encryption.Encryption;
import me.piguy.assignment.util.exception.AccountLockedException;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private final String username;
    private final Role role;
    private String password;

    private int failedLogins = 0;

    private final Encryption encryption;

    public boolean checkPassword(String password) throws AccountLockedException {
        if (Objects.equals(this.getPassword(), getEncryption().encrypt(password))) {
            failedLogins = 0;
            return true;
        } else {
            failedLogins++;
            if (failedLogins > 3) {
                throw new AccountLockedException();
            }
            return false;
        }
    }

    public void setPassword(String currentPassword, String newPassword) {
        if (this.getPassword().equals(getEncryption().encrypt(currentPassword))) {
            this.setPassword(getEncryption().encrypt(newPassword));
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
        this.encryption = config.getEncryption();
        this.setPassword(getEncryption().encrypt(password));
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    /**
     * This will be encrypted usually
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Encryption getEncryption() {
        return encryption;
    }
}
