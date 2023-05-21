/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import model.Club;
import model.ClubDAOException;

/**
 * FXML Controller class
 *
 * @author manub
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Button perfilButton;
    @FXML
    private Button reservasButton;
    @FXML
    private Button pistasButton;
    @FXML
    private Pane mainWindow;

    private Club club;
    private UtilData utilData;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {club = Club.getInstance();} catch (ClubDAOException | IOException ex) {}
        utilData = UtilData.getInstance();
        utilData.setMainController(this);
        
        
                        //INICIALIZACIÓN PARA TESTING//
        ////////////////////////////////////////////////////////////////////////
        try {
            club.setInitialData(); //Resetea la base de datos al iniciar
            //club.addSimpleData();
            
            club.registerMember("Fernando", "Alonso", "99999999", "1", "1", "0000000000000000", 000, null); //registra un miembro de prueba
            //utilData.setLogin("1");
            //utilData.setPassword("1");
            
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ////////////////////////////////////////////////////////////////////////
        
        

    }    

    public void triggerOnPerfilButton() {
        onPerfilButton(new ActionEvent(this, perfilButton));
        perfilButton.requestFocus();
    }
    @FXML
    private void onPerfilButton(ActionEvent event) {
        Scene escena = utilData.escenas.get("Perfil");
        mainBorderPane.setCenter(null);
        mainBorderPane.setCenter(escena.getRoot());
        utilData.getPerfilController().startPerfil();
    }
    
    public void triggerOnReservasButton(){
        onReservasButton(new ActionEvent(this, reservasButton));
        reservasButton.requestFocus();
    }
    @FXML
    private void onReservasButton(ActionEvent event) {
        Scene escena = utilData.escenas.get("Reservas");
        mainBorderPane.setCenter(null);
        mainBorderPane.setCenter(escena.getRoot());
        utilData.getReservasController().startReservas();
    }
    
    public void triggerOnPistasButton(){
        onPistasButton(new ActionEvent(this, pistasButton));
        pistasButton.requestFocus();
    }
    @FXML
    private void onPistasButton(ActionEvent event) {
        Scene escena = utilData.escenas.get("Pistas");
        mainBorderPane.setCenter(null);
        mainBorderPane.setCenter(escena.getRoot());
        utilData.getPistasController().startPistas();
    }
}
