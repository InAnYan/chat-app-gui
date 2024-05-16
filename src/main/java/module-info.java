module org.example.chatappgui {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.chatappgui to javafx.fxml;
    exports org.example.chatappgui;
    exports org.example.chatappgui.langchain4j;
    opens org.example.chatappgui.langchain4j to javafx.fxml;
}