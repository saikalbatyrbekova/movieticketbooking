package com.example.movieticketbookingsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FilterReader;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;



public class dashboardController implements Initializable {

    @FXML
    private AnchorPane topForm;

    @FXML
    private Button addMovies_btn;

    @FXML
    private Button addMovies_clearBtn;

    @FXML
    private TableColumn<moviesData, Date> addMovies_col_date;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_duration;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_genre;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_movieTitle;

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
    private TableView<moviesData> addMovies_tableView;

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
    private TableColumn<moviesData, String> editScreening_col_current;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_duration;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_genre;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_movieTitle;

    @FXML
    private ComboBox<?> editScreening_current;

    @FXML
    private Button editScreening_delete;

    @FXML
    private AnchorPane editScreening_form;

    @FXML
    private ImageView editScreening_imageView;


    @FXML
    private Label editScreening_label;

    @FXML
    private TextField editScreening_search;

    @FXML
    private TableView<moviesData> addScreening_tableView;

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
    private DatePicker addMovies_date;

    @FXML
    void signUp_close(ActionEvent event) {

    }

    @FXML
    void signUp_minimize(ActionEvent event) {

    }

    private Image image;
    private double x = 0;
    private double y = 0;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private String[] currentList = {"Showing", "End Showing"};

    public void comboBox(){
        List<String> listCurrent = new ArrayList<>();

        for(String data: currentList){
            listCurrent.add(data);
        }

        ObservableList listC = FXCollections.observableArrayList(listCurrent);
        editScreening_current.setItems(listC);
    }

    public void updateEditScreening() throws SQLException {
        String sql = "UPDATE movie SET current = ' "
                + editScreening_current.getSelectionModel().getSelectedItem()
                + "' WHERE movie_title = '" + editScreening_title.getText() + "'";

        connect = database.connectDb();

        try{
            statement = connect.createStatement();

            Alert alert;

            if(editScreening_title.getText().isEmpty()
            || editScreening_imageView.getImage() == null
            || editScreening_current.getSelectionModel().isEmpty()){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();
            }else {
                statement.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully updated!");
                alert.showAndWait();

                showEditScreening();
                clearEditScreening();
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void clearEditScreening(){
        editScreening_title.setText("");
        editScreening_imageView.setImage(null);
        //editScreening_current.setSelectionModel(null);


    }

    public void selectEditScreening(){
        moviesData movD = addScreening_tableView.getSelectionModel().getSelectedItem();
        int num = addScreening_tableView.getSelectionModel().getFocusedIndex();

        if((num-1) < -1){
            return;
        }

        String uri = "file:" + movD.getImage();
        image = new Image(uri,141, 177, false, true);
        editScreening_imageView.setImage(image);

        editScreening_title.setText(movD.getTitle());


    }

    public ObservableList<moviesData> editScreeningList() throws SQLException {
        ObservableList<moviesData> editSList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM movie";

        connect = database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            moviesData movD;
            while(result.next()) {
                    movD = new moviesData(result.getInt("id"),
                            result.getString("movie_title"),
                            result.getString("genre"),
                            result.getString("duration"),
                            result.getString("image"),
                            result.getDate("date"),
                            result.getString("current"));

                    editSList.add(movD);

            }

        }catch (Exception e){e.printStackTrace();}
        return editSList;
    }

    private ObservableList<moviesData> editScreeningL;
    public void showEditScreening() throws SQLException {
        editScreeningL =  editScreeningList();

        editScreening_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        editScreening_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        editScreening_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        editScreening_col_current.setCellValueFactory(new PropertyValueFactory<>("current"));

        addScreening_tableView.setItems(editScreeningL);
    }
    public void importImage(){
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.png", "*.jpg", "*.jpeg"));

        Stage stage = (Stage) addMovies_form.getScene().getWindow();
        File file = open.showOpenDialog(stage);

        if (file != null) {
            try {
                image = new Image(file.toURI().toString(), 141, 177, false, true);
                addMovies_imageView.setImage(image);
                getData.path = file.getAbsolutePath();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }

    public void movieId() throws SQLException {
        String sql = "SELECT count(id) FROM movie";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                getData.movieId = result.getInt(1); // Use the index 1 for the first column
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void insertAddMovies() {
        String sql1 = "SELECT movie_title FROM movie WHERE movie_title = ?";
        Alert alert;

        try (Connection connect = Database.connectDb();  // Open the connection in a try-with-resources block
             PreparedStatement checkStatement = connect.prepareStatement(sql1)) {

            checkStatement.setString(1, addMovies_movieTitle.getText());
            ResultSet result = checkStatement.executeQuery();

            if (result.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(addMovies_movieTitle.getText() + " is already exist!");
                alert.showAndWait();
            } else {
                if (addMovies_movieTitle.getText().isEmpty()
                        || addMovies_genre.getText().isEmpty()
                        || addMovies_duration.getText().isEmpty()
                        || addMovies_imageView.getImage() == null
                        || addMovies_date.getValue() == null) {

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields!");
                    alert.showAndWait();
                } else {
                    String sql = "INSERT INTO movie (id, movie_title, genre, duration, image, date) VALUES (?, ?, ?, ?, ?, ?)";
                    String uri = getData.path.replace("\\", "\\\\");

                    movieId();

                    try (PreparedStatement prepare = connect.prepareStatement(sql)) {
                        prepare.setInt(1, getData.movieId + 1);
                        prepare.setString(2, addMovies_movieTitle.getText());
                        prepare.setString(3, addMovies_genre.getText());
                        prepare.setString(4, addMovies_duration.getText());
                        prepare.setString(5, uri);
                        java.sql.Date sqlDate = java.sql.Date.valueOf(addMovies_date.getValue());
                        prepare.setDate(6, sqlDate);

                        prepare.execute();
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully added a new movie!");
                        alert.showAndWait();

                        showAddMoviesList();
                        clearAddMovieList();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAddMovieList() {
        String sql = "UPDATE movie SET movie_title = ?, genre = ?, duration = ?, image = ?, date = ? WHERE id = ?";
        String uri = getData.path.replace("\\", "\\\\");

        try (Connection connect = Database.connectDb();  // Open the connection in a try-with-resources block
             PreparedStatement statement = connect.prepareStatement(sql)) {

            statement.setString(1, addMovies_movieTitle.getText());
            statement.setString(2, addMovies_genre.getText());
            statement.setString(3, addMovies_duration.getText());
            statement.setString(4, uri);
            java.sql.Date sqlDate = java.sql.Date.valueOf(addMovies_date.getValue());
            statement.setDate(5, sqlDate);
            statement.setInt(6, getData.movieId);

            statement.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully updated " + addMovies_movieTitle.getText());
            alert.showAndWait();

            showAddMoviesList();
            clearAddMovieList();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAddMovies() throws SQLException {
        String sql = "DELETE FROM movie WHERE movie_title = '"
                + addMovies_movieTitle.getText() + "' ";

        connect = database.connectDb();

        try{
            statement = connect.createStatement();

            Alert alert;


            if(addMovies_movieTitle.getText().isEmpty()
                    || addMovies_genre.getText().isEmpty()
                    || addMovies_duration.getText().isEmpty()
                    || addMovies_date.getValue() == null
                    || addMovies_imageView.getImage() == null) {

                alert = new Alert (Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();

            }else{

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure to delete " + addMovies_movieTitle.getText()  + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if(ButtonType.OK.equals(option.get())) {

                    statement.executeUpdate(sql);
                    showAddMoviesList();
                    clearAddMovieList();

                    alert = new Alert((Alert.AlertType.INFORMATION));
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully deleted!");
                    alert.showAndWait();

                }else{
                    return;
                }
            }


        }catch(Exception e) {e.printStackTrace();}
    }
    public void clearAddMovieList(){
        addMovies_movieTitle.setText("");
        addMovies_genre.setText("");
        addMovies_duration.setText("");
        addMovies_imageView.setImage(null);
        addMovies_date.setValue(null);
    }

    private Database database = new Database();

    public ObservableList<moviesData> listAddMovies;

    public ObservableList<moviesData> addMoviesList() {
        ObservableList<moviesData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM movie";

        try {
            connect = Database.connectDb();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            moviesData movD;
            while(result.next()) {
                movD = new moviesData(result.getInt("id"),
                        result.getString("movie_title"),
                        result.getString("genre"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current"));

                listAddMovies.add(movD);  // This line is causing the NullPointerException
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // ... (your existing cleanup code)
        }
        return listData;
    }


    public void showAddMoviesList() {
        if (listAddMovies == null) {
            listAddMovies = addMoviesList();
        }

        addMovies_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        addMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        addMovies_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        addMovies_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        addMovies_tableView.setItems(listAddMovies);
    }


    public void selectAddMoviesList() {
        moviesData movD = addMovies_tableView.getSelectionModel().getSelectedItem();
        int num = addMovies_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        getData.path = movD.getImage();

        getData.movieId = movD.getId();

        addMovies_movieTitle.setText(movD.getTitle());
        addMovies_genre.setText(movD.getGenre());
        addMovies_duration.setText(movD.getDuration());


        String uri = "file:" + movD.getImage();

        image = new Image(uri, 141, 177, false, true);
        addMovies_imageView.setImage(image);

        LocalDate localDate = Instant.ofEpochMilli(movD.getDate().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        addMovies_date.setValue(localDate);
    }

    public void logOut() {
        signout.getScene().getWindow().hide();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("MovieTicketFXML.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent mouseEvent) -> {
                x = mouseEvent.getSceneX();
                y = mouseEvent.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent mouseEvent) -> {
                stage.setX(mouseEvent.getScreenX() - x);
                stage.setY(mouseEvent.getScreenY() - y);
            });

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);
        }

        if (event.getSource() == addMovies_btn) {
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(true);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);
        }
        if (event.getSource() == availableMovies_btn) {
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(true);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);
        }
        if (event.getSource() == editScreening_btn) {
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(true);
            customers_form.setVisible(false);
        } else if (event.getSource() == customers_btn) {
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(true);
        }
    }

    public void displayUsername() {
        username.setText(getData.username);
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) topForm.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            displayUsername();

            // Ensure listAddMovies is initialized before calling showAddMoviesList
            if (listAddMovies == null) {
                listAddMovies = FXCollections.observableArrayList(); // Initialize the list
                listAddMovies.addAll(addMoviesList()); // Add data from addMoviesList
            }

            showAddMoviesList();
            showEditScreening();
            comboBox();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
