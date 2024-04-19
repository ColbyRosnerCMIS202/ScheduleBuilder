module com.example.asiancafe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;

    opens com.example.asiancafe to javafx.fxml;
    exports com.example.asiancafe;
}