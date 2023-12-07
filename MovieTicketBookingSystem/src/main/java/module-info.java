module com.example.movieticketbooking {
    requires javafx.controls;
    requires javafx.fxml;
    requires fontawesomefx;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;



    opens com.example.movieticketbookingsystem to javafx.fxml;
    exports com.example.movieticketbookingsystem;
}
