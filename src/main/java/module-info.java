module org.example.chatappgui {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.chatappgui to javafx.fxml;
    exports org.example.chatappgui;
}