package com.example.movieticketbookingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieTicketController implements Initializable {

    @FXML
    private Hyperlink signUp_alreadyHaveAccount;

    @FXML
    private Button signUp_btn;

    @FXML
    private Button signUp_close;

    @FXML
    private TextField signUp_email;

    @FXML
    private AnchorPane signUp_form;

    @FXML
    private Button signUp_minimize;

    @FXML
    private PasswordField signUp_password;

    @FXML
    private TextField signUp_username;

    @FXML
    private Button signIn_close;

    @FXML
    private Hyperlink signIn_createAccount;

    @FXML
    private AnchorPane signIn_form;

    @FXML
    private Button signIn_loginBtn;

    @FXML
    private Button signIn_minimize;

    @FXML
    private PasswordField signIn_password;

    @FXML
    private TextField signIn_username;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private static final Logger logger = Logger.getLogger(MovieTicketController.class.getName());

    public boolean validEmail() {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9._]+@[a-zA-Z]+\\.[a-zA-Z]+");

        Matcher match = pattern.matcher(signUp_email.getText());
        Alert alert;

        if (match.matches()) {
            return true;
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid email.");
            alert.showAndWait();
            return false;
        }
    }

    public void signup() {
        String sql = "INSERT INTO admin (email, username, password) VALUES (?,?,?)";

        try {
            connect = Database.connectDb();
            prepare = connect.prepareStatement(sql);

            Alert alert;

            if (signUp_email.getText().isEmpty() || signUp_username.getText().isEmpty()
                    || signUp_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else if (!validEmail()) {
                return;
            } else if (signUp_password.getText().length() < 8) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Password. The password should have more than 8 characters.");
                alert.showAndWait();
            } else {
                prepare.setString(1, signUp_email.getText());
                prepare.setString(2, signUp_username.getText());
                prepare.setString(3, signUp_password.getText());

                prepare.execute();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully created a new account!");
                alert.showAndWait();

                signUp_email.setText("");
                signUp_username.setText("");
                signUp_password.setText("");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during signup:", e);
        } finally {
            closeDatabaseResources();
        }
    }

    public void signin(ActionEvent event) {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try {
            connect = Database.connectDb();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, signIn_username.getText());
            prepare.setString(2, signIn_password.getText());

            result = prepare.executeQuery();

            Alert alert;

            if (signIn_username.getText().isEmpty() || signIn_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully login!");
                    alert.showAndWait();

                    signIn_loginBtn.getScene().getWindow().hide();

                    Parent root = FXMLLoader.load(getClass().getResource("MovieDashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();

                    ((Stage) signIn_form.getScene().getWindow()).close();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username or Password");
                    alert.showAndWait();
                }
            }
        } catch (IOException | SQLException e) {
            logger.log(Level.SEVERE, "Error during signin:", e);
        } finally {
            closeDatabaseResources();
        }
    }

    private void closeDatabaseResources() {
        try {
            if (result != null) result.close();
            if (prepare != null) prepare.close();
            if (connect != null) connect.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error closing database resources:", e);
        }
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == signIn_createAccount) {
            signIn_form.setVisible(false);
            signUp_form.setVisible(true);
        } else if (event.getSource() == signUp_alreadyHaveAccount) {
            signIn_form.setVisible(true);
            signUp_form.setVisible(false);
        }
    }

    public void signIn_close() {
        System.exit(0);
    }

    public void signIn_minimize() {
        Stage stage = (Stage) signIn_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void signUp_close() {
        System.exit(0);
    }

    public void signUp_minimize() {
        Stage stage = (Stage) signUp_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Perfect!");
    }
}
