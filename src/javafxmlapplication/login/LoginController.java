/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
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
    
    private Club club;
    private String cssError;
    private UtilData utilData;
    @FXML
    private Button register;
    @FXML
    private Button login;
    @FXML
    private PasswordField contraseñaField;
    @FXML
    private TextField usuarioField;
    @FXML
    private Button menuButton;
    @FXML
    private TextField temporaryTextField;
    @FXML
    private ToggleButton mostrarButton;
    @FXML
    private Label contraseñaErrorLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.utilData = UtilData.getInstance();
        try {
            this.club = Club.getInstance();
        } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        contraseñaField.textProperty().bindBidirectional(temporaryTextField.textProperty()); 
        usuarioField.textProperty().addListener((observable,oldVal,newVal)-> { 
            usuarioField.setId("defaultInputBox");
            contraseñaErrorLabel.setVisible(false);
        });
        contraseñaField.textProperty().addListener((observable,oldVal,newVal)-> { 
            contraseñaField.setId("defaultInputBox"); 
            contraseñaErrorLabel.setVisible(false);
        });
        temporaryTextField.textProperty().addListener((observable,oldVal,newVal)-> { 
            temporaryTextField.setId("defaultInputBox"); 
            contraseñaErrorLabel.setVisible(false);
        });
        contraseñaErrorLabel.setVisible(false);
    }

    @FXML
    private void irARegistro(ActionEvent event) {
        utilData.showScene("Main");
        utilData.setRegistrarse(true);
        utilData.getMainController().triggerOnMiPerfil();
        
    }

    @FXML
    private void intentoIniciarSesion(ActionEvent event) throws ClubDAOException, IOException, InterruptedException {
        try {
            //para ver si existe tal miembro y si no da error
            Member m = Club.getInstance().getMemberByCredentials(usuarioField.getText(), contraseñaField.getText()); 
            
            login.setVisible(false);
            usuarioField.setEditable(false);
            contraseñaField.setEditable(false);
            menuButton.setDisable(true);
            login.setDisable(true);
            
            PauseTransition pause = new PauseTransition(Duration.millis(300));
            pause.setOnFinished(pauseEvent -> {                
                UtilData.getInstance().setLogin(usuarioField.getText());
                UtilData.getInstance().setPassword(contraseñaField.getText());
                UtilData.getInstance().showScene("Main");
            });
            pause.play();                         
        } catch (NullPointerException ex) {
            usuarioField.setId("defaultInputBoxError");
            contraseñaField.setId("defaultInputBoxError");
            temporaryTextField.setId("defaultInputBoxError");

            contraseñaErrorLabel.setText("Usuario o contraseña incorrectos");
            contraseñaErrorLabel.setVisible(true);
        }                
    }
    
    @FXML
    private void verContraseña(ActionEvent event) {
        temporaryTextField.setVisible(!temporaryTextField.isVisible());
        contraseñaField.setVisible(!contraseñaField.isVisible());
    }

    @FXML
    private void onMenuButton(ActionEvent event) {
        UtilData.getInstance().showScene("Main");
    }
    
}
