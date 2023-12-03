module com.example.movieticketbookingoop {
    requires javafx.controls;
    requires javafx.fxml;
    requires fontawesomefx;
    requires javafx.graphics;
    requires java.sql;



    opens com.example.movieticketbookingsystem to javafx.fxml;
    exports com.example.movieticketbookingsystem;
}
