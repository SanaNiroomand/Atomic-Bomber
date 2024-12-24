module AtomicBomber {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires gson;
    requires java.sql;

    exports view;
    opens view to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
    exports model;
    exports model.bomb;
    opens model to gson;
}