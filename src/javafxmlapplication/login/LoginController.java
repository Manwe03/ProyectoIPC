/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import javafxmlapplication.UtilData;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Miquel
 */
public class LoginController  implements Initializable{

    @FXML
    private Button register;
    @FXML
    private Label loginFailed;
    @FXML
    private Button login;
    @FXML
    private TextField fieldUsuario;
    @FXML
    private TextField fieldContraseña;
    @FXML
    private PasswordField pfieldContraseña;
    @FXML
    private ToggleButton toggleContraseña;
    @FXML
    private Button botonIniciandoSesion;
    @FXML
    private Button botonAtras;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pfieldContraseña.textProperty().addListener((observable, oldValue, newValue) -> {
        fieldContraseña.setText(newValue);
        });
        
        fieldContraseña.textProperty().addListener((observable, oldValue, newValue) -> {
        pfieldContraseña.setText(newValue);
        });
        
        pfieldContraseña.textProperty().bindBidirectional(fieldContraseña.textProperty()); 
    }

    @FXML
    private void irARegistro(ActionEvent event) {
        UtilData.getInstance().showScene("Register");
    }

    @FXML
    private void atras(ActionEvent event) {
        UtilData.getInstance().showScene("Main");
    }
    
    @FXML
    private void intentoIniciarSesion(ActionEvent event) throws ClubDAOException, IOException, InterruptedException {
        try {
            //para ver si existe tal miembro y si no da error
            Member m = Club.getInstance().getMemberByCredentials(fieldUsuario.getText(), pfieldContraseña.getText()); 
            loginFailed.setVisible(false);
            login.setVisible(false);
            botonIniciandoSesion.setVisible(true);
            fieldUsuario.setEditable(false);
            fieldContraseña.setEditable(false);
            pfieldContraseña.setEditable(false);
            botonAtras.setDisable(true);
            login.setDisable(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(pauseEvent -> {                
                UtilData.getInstance().setLogin(fieldUsuario.getText());
                UtilData.getInstance().setPassword(pfieldContraseña.getText());
                UtilData.getInstance().showScene("Main");
            });
            pause.play();                         
        } catch (NullPointerException ex) {
            loginFailed.setVisible(true);
        }                
    }

    @FXML
    private void manejaType(KeyEvent event) {
        loginFailed.setVisible(false);
    }

    @FXML
    private void verContraseña(ActionEvent event) {
        if (toggleContraseña.isSelected()) {
            pfieldContraseña.setEditable(false);
            pfieldContraseña.setVisible(false);
            fieldContraseña.setEditable(true);
            fieldContraseña.setVisible(true);
        } else {
            fieldContraseña.setEditable(false);
            fieldContraseña.setVisible(false);
            pfieldContraseña.setEditable(true);
            pfieldContraseña.setVisible(true);
        } 
    }

}
