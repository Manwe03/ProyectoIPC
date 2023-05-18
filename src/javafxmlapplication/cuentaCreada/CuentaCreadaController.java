/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.cuentaCreada;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
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
public class CuentaCreadaController implements Initializable {

    @FXML
    private Label labelFelicidades;
    @FXML
    private Button menuPrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }   

    @FXML
    private void aMenuPrincipal(ActionEvent event) {
        
        UtilData.getInstance().showScene("Main");
        
    }
    
}
