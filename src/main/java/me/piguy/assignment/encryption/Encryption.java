package me.piguy.assignment.encryption;

import java.io.Serializable;

public interface Encryption extends Serializable {
    String encrypt(String value);
}
