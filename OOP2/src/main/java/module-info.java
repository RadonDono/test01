module com.example.oop2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oop2 to javafx.fxml;
    exports com.example.oop2;
}