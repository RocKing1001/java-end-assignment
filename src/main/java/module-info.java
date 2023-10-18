module me.piguy.assignment {
    requires javafx.controls;
    requires javafx.fxml;


    opens me.piguy.assignment to javafx.fxml;
    opens me.piguy.assignment.pane to javafx.fxml;
    exports me.piguy.assignment;
    exports me.piguy.assignment.models;
    exports me.piguy.assignment.database;
    exports me.piguy.assignment.encryption;
    exports me.piguy.assignment.pane;
}