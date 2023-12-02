module com.example.movieticketbookingsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.movieticketbookingsystem to javafx.fxml;
    exports com.example.movieticketbookingsystem;
}