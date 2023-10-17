module me.piguy.assignment {
    requires javafx.controls;
    requires javafx.fxml;


    opens me.piguy.assignment to javafx.fxml;
    exports me.piguy.assignment;
    exports me.piguy.assignment.models;
}