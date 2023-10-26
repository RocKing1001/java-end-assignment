package me.piguy.assignment.util;

// Unused because the default string converter gives me the same results,
// with a lengthy stacktrace in the console. As this is a GUI app
// I shall not address that issue. It is simply not worth my time
// but if there is a need for it, I can pass in a label with red font colour into this
// converter and have the error be displayed as a label and then throw the error
public class IntegerStringConverter extends javafx.util.converter.IntegerStringConverter {
    @Override
    public String toString(Integer value) {
        return super.toString(value);
    }
}
