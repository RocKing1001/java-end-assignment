package me.piguy.assignment.encryption;

public class NoEncryption implements Encryption {
    @Override
    public String encrypt(String password) {
        return password;
    }
}
