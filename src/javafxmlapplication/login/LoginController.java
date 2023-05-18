/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafxmlapplication.UtilData;
import model.Club;

/**
 * FXML Controller class
 *
 * @author Miquel
 */
public class LoginController  implements Initializable{

    private UtilData utilData;
    @FXML
    private PasswordField contraseñaLabel;
    @FXML
    private TextField usuarioLabel;
    @FXML
    private Button register;
    @FXML
    private Button login;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.utilData = UtilData.getInstance();
    }

    @FXML
    private void irARegistro(ActionEvent event) {
        UtilData.getInstance().showScene("Register");
    }

    private void atras(ActionEvent event) {
        UtilData.getInstance().showScene("Main");
    }

    @FXML
    private void intentoIniciarSesion(ActionEvent event) {
        utilData.setLogin(usuarioLabel.getText());
        utilData.setPassword(contraseñaLabel.getText());
        if(utilData.isLogged()){//Epico, info correcta
            utilData.showScene("Main");
        }else{
            //Notificar al usuario
            usuarioLabel.getText();
        }
    }

}
