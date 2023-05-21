/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafxmlapplication.perfil.FXMLPerfilController;
import javafxmlapplication.pistaCalendario.FXMLpistaBoxController;
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

    private Club club;
    private UtilData utilData;
    @FXML
    private StackPane mainStackPane;
    @FXML
    private Pane ventanaPane;
    @FXML
    private Label titleLabel;
    @FXML
    private Button iButton;
    @FXML
    private Button dButton;
    @FXML
    private Text infoLabel;
    
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
        
        mainBorderPane.maxWidthProperty().bind(mainStackPane.widthProperty());
        mainBorderPane.minWidthProperty().bind(mainStackPane.widthProperty());
        
        mainBorderPane.maxHeightProperty().bind(mainStackPane.heightProperty());
        mainBorderPane.minHeightProperty().bind(mainStackPane.heightProperty());
        
    }    

    public void updateButtonText(){
        if(utilData.getRegistrarse()){
            perfilButton.setText("Registrarse");
        }else if(utilData.isLogged()){
            perfilButton.setText("Perfil");
        }else{
            perfilButton.setText("Login");
        }
    }
    
    public void showVentana(boolean show){
        if(show){
            ventanaPane.toFront();
        }else{
            ventanaPane.toBack();
        }
        mainBorderPane.setDisable(show);
        ventanaPane.setDisable(!show);
    }
    public void setVentanaInfo(String titulo, String texto){
        iButton.setVisible(false);
        dButton.setVisible(true);
        titleLabel.setText(titulo);
        infoLabel.setText(texto);
    }
    public void setVentanaConfirmar(String titulo, String texto){
        iButton.setVisible(true);
        iButton.setVisible(true);
        titleLabel.setText(titulo);
        infoLabel.setText(texto);
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
        perfilButton.setId("buttonTabSelected");
        reservasButton.setId("buttonDeault");
        pistasButton.setId("buttonDeault");
        updateButtonText();
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
        perfilButton.setId("buttonDeault");
        reservasButton.setId("buttonTabSelected");
        pistasButton.setId("buttonDeault");
        updateButtonText();
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
        perfilButton.setId("buttonDeault");
        reservasButton.setId("buttonDeault");
        pistasButton.setId("buttonTabSelected");
        updateButtonText();
    }

    @FXML
    private void onIButton(ActionEvent event) {
        showVentana(false);//quita la ventana modal
    }

    @FXML
    private void onDButton(ActionEvent event) {
        
        switch(utilData.ventanaMode){
            case 0:
                FXMLPerfilController controller = utilData.getPerfilController();
                controller.editarDatos();
                controller.removeContraseñaField();
            break;
            case 1:
                utilData.showScene("Login");
            break;
            case 2:
                FXMLpistaBoxController pistaController = utilData.getPistaBoxController();
                pistaController.safeRegisterBooking(LocalDateTime.now(), utilData.getSelectedDate());
            break;
            case 3:    
            break;
            case 4:
                FXMLpistaBoxController pistaController2 = utilData.getPistaBoxController();
                try {
                    club.removeBooking(pistaController2.reservaCancel);
                } catch (ClubDAOException ex) {Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);}
                pistaController2.updateButtonState();
            break;

                
                
        }
        showVentana(false);//quita la ventana modal
    }
}
