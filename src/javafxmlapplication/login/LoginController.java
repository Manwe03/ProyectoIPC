/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.login;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private Label usuarioLabel;
    @FXML
    private Label contraseñaLabel;
    
    private boolean usuarioLabelR;
    private boolean contraseñaLabelR;
    @FXML
    private ImageView ojo;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.utilData = UtilData.getInstance();
        
        usuarioField.focusedProperty().addListener((observable,oldVal,newVal)-> { usuarioLabelR = moveLabelIntoBorder(usuarioLabel,usuarioLabelR);});
        contraseñaField.focusedProperty().addListener((observable,oldVal,newVal)-> { contraseñaLabelR = moveLabelIntoBorder(contraseñaLabel,contraseñaLabelR);});
        
        contraseñaField.textProperty().bindBidirectional(temporaryTextField.textProperty());
        
        usuarioField.textProperty().addListener((observable,oldVal,newVal)-> { 
            contraseñaErrorLabel.setVisible(false);
        });
        contraseñaField.textProperty().addListener((observable,oldVal,newVal)-> { 
            contraseñaErrorLabel.setVisible(false);
        });
        temporaryTextField.textProperty().addListener((observable,oldVal,newVal)-> { 
            contraseñaErrorLabel.setVisible(false);
        });
        contraseñaErrorLabel.setVisible(false);
        
        utilData.setLoginController(this);
    }
    
    public void startLoggin(){
        contraseñaErrorLabel.setVisible(false);
        login.setVisible(true);
        usuarioField.setEditable(true);
        contraseñaField.setEditable(true);
        menuButton.setDisable(false);
        login.setDisable(false);
    }

    @FXML
    private void irARegistro(ActionEvent event) {
        utilData.showScene("Main");
        utilData.setRegistrarse(true);
        utilData.getPerfilController().startPerfil();
        utilData.getMainController().updateButtonText();
        
        utilData.getMainController().triggerOnPerfilButton();//clicar el boton de registrarse
    }

    @FXML
    private void intentoIniciarSesion(ActionEvent event) throws ClubDAOException, IOException, InterruptedException {
        
        if(usuarioField.getText().isBlank()){
            usuarioField.setId("textFieldError");
            contraseñaErrorLabel.setText("Usuario vacío");
            contraseñaErrorLabel.setVisible(true);
        }else if(contraseñaField.getText().isBlank()){
            contraseñaField.setId("textFieldError");
            temporaryTextField.setId("textFieldError");
            contraseñaErrorLabel.setText("Contraseña vacía");
            contraseñaErrorLabel.setVisible(true);    
        }else{
            try {
                //para ver si existe tal miembro y si no da error
                Member m = Club.getInstance().getMemberByCredentials(usuarioField.getText(), contraseñaField.getText()); m.getPassword(); 
                
                login.setVisible(false);
                usuarioField.setEditable(false);
                contraseñaField.setEditable(false);
                menuButton.setDisable(true);
                login.setDisable(true);
                PauseTransition pause = new PauseTransition(Duration.millis(300));
                pause.setOnFinished(pauseEvent -> {                
                    utilData.setLogin(usuarioField.getText());
                    utilData.setPassword(contraseñaField.getText());
                    utilData.showScene("Main");
                    utilData.getMainController().triggerOnPistasButton();
                });
                pause.play();
                ((Button)event.getSource()).getScene().setCursor(Cursor.HAND);
                utilData.getMainController().cerrarSesionButton.setVisible(true);
            } catch (NullPointerException ex) {
                usuarioField.setId("textFieldError");
                contraseñaField.setId("textFieldError");
                temporaryTextField.setId("textFieldError");

                contraseñaErrorLabel.setText("Usuario o contraseña incorrectos");
                contraseñaErrorLabel.setVisible(true);
            } 
        }
    }
    
    @FXML
    private void verContraseña(ActionEvent event) {
        temporaryTextField.setVisible(!temporaryTextField.isVisible());
        contraseñaField.setVisible(!contraseñaField.isVisible());
        Image ver = new Image(new File("src/resources/images/ver.png").toURI().toString());
        Image nover = new Image(new File("src/resources/images/nover.png").toURI().toString());
        if(mostrarButton.isSelected()) {
            ojo.setImage(ver);
        } else {
            ojo.setImage(nover);
        }
    }
    
    @FXML
    private void onMenuButton(ActionEvent event) {
        utilData.showScene("Main");
        utilData.getMainController().triggerOnPistasButton();
    }
    
    private boolean moveLabelIntoBorder(Node nodo, boolean estado){
        TranslateTransition translate = new TranslateTransition(Duration.millis(100));
        translate.setNode(nodo);              
        if(estado){
           translate.setToX(0);
        }else{
           translate.setToX(8);
        }
        translate.play();
        return !estado;
    }
}
