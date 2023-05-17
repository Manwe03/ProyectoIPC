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
import javafxmlapplication.UtilData;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
    private void intentoIniciarSesion(ActionEvent event) {
        //falta por implementar
    }

}
