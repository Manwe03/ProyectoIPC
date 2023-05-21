/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.util.Duration;
import javafxmlapplication.UtilData;
import model.Club;
import model.ClubDAOException;

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
        
        try {club = Club.getInstance();} catch (ClubDAOException | IOException ex) {}
        this.utilData = UtilData.getInstance();
        
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
        utilData.getPerfilController().startPerfil();
        utilData.getMainController().updateButtonText();
    }

    @FXML
    private void intentoIniciarSesion(ActionEvent event) throws ClubDAOException, IOException, InterruptedException {
        
        if(usuarioField.getText().isBlank()){
            usuarioField.setId("defaultInputBoxError");
            contraseñaErrorLabel.setText("Usuario vacio");
            contraseñaErrorLabel.setVisible(true);
        }else if(contraseñaField.getText().isBlank()){
            contraseñaField.setId("defaultInputBoxError");
            temporaryTextField.setId("defaultInputBoxError");
            contraseñaErrorLabel.setText("Contraseña vacia");
            contraseñaErrorLabel.setVisible(true);    
        }else{
            try {
                //para ver si existe tal miembro y si no da error
                Club.getInstance().getMemberByCredentials(usuarioField.getText(), contraseñaField.getText()); 

                login.setVisible(false);
                usuarioField.setEditable(false);
                contraseñaField.setEditable(false);
                menuButton.setDisable(true);
                login.setDisable(true);
                //System.out.println("He pasado por login");
                PauseTransition pause = new PauseTransition(Duration.millis(300));
                pause.setOnFinished(pauseEvent -> {                
                    utilData.setLogin(usuarioField.getText());
                    utilData.setPassword(contraseñaField.getText());
                    utilData.showScene("Main");
                    utilData.getMainController().triggerOnPistasButton();
                });
                pause.play();
                ((Button)event.getSource()).getScene().setCursor(Cursor.HAND);
            } catch (NullPointerException ex) {
                usuarioField.setId("defaultInputBoxError");
                contraseñaField.setId("defaultInputBoxError");
                temporaryTextField.setId("defaultInputBoxError");

                contraseñaErrorLabel.setText("Usuario o contraseña incorrectos");
                contraseñaErrorLabel.setVisible(true);
            } 
        }
    }
    
    @FXML
    private void verContraseña(ActionEvent event) {
        temporaryTextField.setVisible(!temporaryTextField.isVisible());
        contraseñaField.setVisible(!contraseñaField.isVisible());
    }

    @FXML
    private void onMenuButton(ActionEvent event) {
        utilData.showScene("Main");
        utilData.getMainController().triggerOnPistasButton();
    }
    
}
