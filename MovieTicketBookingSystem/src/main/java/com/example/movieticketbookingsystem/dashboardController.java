package com.example.movieticketbookingsystem;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class dashboardController implements Initializable {


    @FXML
    private Button addMovies_btn;

    @FXML
    private Button addMovies_clearBtn;

    @FXML
    private TableColumn<?, ?> addMovies_col_date;

    @FXML
    private TableColumn<?, ?> addMovies_col_duration;

    @FXML
    private TableColumn<?, ?> addMovies_col_genre;

    @FXML
    private TableColumn<?, ?> addMovies_col_movieTitle;

    @FXML
    private TextField addMovies_date;

    @FXML
    private Button addMovies_deleteBtn;

    @FXML
    private TextField addMovies_duration;

    @FXML
    private AnchorPane addMovies_form;

    @FXML
    private TextField addMovies_genre;

    @FXML
    private ImageView addMovies_imageView;

    @FXML
    private Button addMovies_import;

    @FXML
    private Button addMovies_insertBtn;

    @FXML
    private TextField addMovies_movieTitle;

    @FXML
    private TextField addMovies_search;

    @FXML
    private TableView<?> addMovies_tableView;

    @FXML
    private Button addMovies_updateBtn;

    @FXML
    private Button availableMovies_btn;

    @FXML
    private Button availableMovies_buyBtn;

    @FXML
    private TableColumn<?, ?> availableMovies_col_genre;

    @FXML
    private TableColumn<?, ?> availableMovies_col_movieTitle;

    @FXML
    private TableColumn<?, ?> availableMovies_col_showingDate;

    @FXML
    private Label availableMovies_date;

    @FXML
    private AnchorPane availableMovies_form;

    @FXML
    private Label availableMovies_genre;

    @FXML
    private AnchorPane availableMovies_imageView;

    @FXML
    private Label availableMovies_label;

    @FXML
    private Label availableMovies_movieTitle;

    @FXML
    private Label availableMovies_normalClass_price;

    @FXML
    private Spinner<?> availableMovies_normalClass_quantity;

    @FXML
    private Button availableMovies_selectMovie;

    @FXML
    private Label availableMovies_specialClass_price;

    @FXML
    private Spinner<?> availableMovies_specialClass_quantity;

    @FXML
    private Label availableMovies_total;

    @FXML
    private Label availableMovies_ttitle;

    @FXML
    private Button close;

    @FXML
    private Button customers_btn;

    @FXML
    private Button customers_clearBtn;

    @FXML
    private TableColumn<?, ?> customers_col_date;

    @FXML
    private TableColumn<?, ?> customers_col_genre;

    @FXML
    private TableColumn<?, ?> customers_col_movieTitle;

    @FXML
    private TableColumn<?, ?> customers_col_ticketNumber;

    @FXML
    private TableColumn<?, ?> customers_col_time;

    @FXML
    private Label customers_date;

    @FXML
    private Button customers_deleteBtn;

    @FXML
    private AnchorPane customers_form;

    @FXML
    private Label customers_genre;

    @FXML
    private Label customers_movieTitle;

    @FXML
    private TextField customers_search;

    @FXML
    private Label customers_ticketNumber;

    @FXML
    private Label customers_time;

    @FXML
    private Label dashboard_availableMovies;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_totalEarnMoney;

    @FXML
    private Label dashboard_totalSoldTicket;

    @FXML
    private Button editScreening_btn;

    @FXML
    private TableColumn<?, ?> editScreening_col_current;

    @FXML
    private TableColumn<?, ?> editScreening_col_duration;

    @FXML
    private TableColumn<?, ?> editScreening_col_genre;

    @FXML
    private TableColumn<?, ?> editScreening_col_movieTitle;

    @FXML
    private ComboBox<?> editScreening_current;

    @FXML
    private Button editScreening_delete;

    @FXML
    private AnchorPane editScreening_form;

    @FXML
    private AnchorPane editScreening_imageView;

    @FXML
    private Label editScreening_label;

    @FXML
    private TextField editScreening_search;

    @FXML
    private TableView<?> editScreening_tableView;

    @FXML
    private Label editScreening_title;

    @FXML
    private Button editScreening_update;

    @FXML
    private Button minimize;

    @FXML
    private Button signout;

    @FXML
    private Label username;

    @FXML
    void signUp_close(ActionEvent event) {

    }

    @FXML
    void signUp_minimize(ActionEvent event) {

    }

    public void close() {
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
