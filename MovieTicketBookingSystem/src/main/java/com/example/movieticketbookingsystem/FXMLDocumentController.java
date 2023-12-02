package com.example.movieticketbookingsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FXMLDocumentController implements Initializable {
    @FXML
    private Hyperlink signUp_alreadyHaveAccount;

    @FXML
    private Button signUp_btn;

    @FXML
        private FontAwesomeIconView signUp_close;

    @FXML
    private TextField signUp_email;

    @FXML
    private AnchorPane signUp_form;

    @FXML
    private FontAwesomeIconView signUp_minimize;

    @FXML
    private PasswordField signUp_password;

    @FXML
    private TextField signUp_username;

    @FXML
    private FontAwesomeIconView signin_close;

    @FXML
    private Hyperlink signin_createAccount;

    @FXML
    private AnchorPane signin_form;

    @FXML
    private Button signin_loginBtn;

    @FXML
    private FontAwesomeIconView signin_minimize;

    @FXML
    private PasswordField signin_password;

    @FXML
    private TextField signin_username;

    @Override
    public void initializable(URL url, ResourceBundle rb) {

    }
}
