module org.demineur.demineur {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.demineur.demineur to javafx.fxml;
    exports org.demineur.demineur;
}