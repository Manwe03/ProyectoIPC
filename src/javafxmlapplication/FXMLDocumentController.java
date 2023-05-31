/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafxmlapplication.miniTarjeta.MiniTarjetaController;
import javafxmlapplication.perfil.FXMLPerfilController;
import javafxmlapplication.pistaCalendario.FXMLpistaBoxController;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author manub
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private ToggleButton perfilButton;
    @FXML
    private ToggleButton reservasButton;
    @FXML
    private ToggleButton pistasButton;

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
    private Label infoLabel;
    @FXML
    private VBox ventanaVbox;
    @FXML
    private ToggleGroup menu;
    @FXML
    public Button cerrarSesionButton;

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
        
        //try {
            //club.setInitialData(); //Resetea la base de datos al iniciar
            //club.addSimpleData();
            //Image imagenDefault = new Image(new FileInputStream("src/resources/images/elNano.png"));
            //club.registerMember("Fernando", "Alonso", "99999999", "33", "33", "", 0, imagenDefault); //registra un miembro de prueba
            //club.registerMember("Fernando", "Alonso", "99999999", "papo", "0", "0000000000000000", 000, null);
            //club.registerMember("Fernando", "Alonso", "99999999", "antonio", "0", "0000000000000000", 000, null);
            //club.registerMember("Fernando", "Alonso", "99999999", "yiyi", "0", "0000000000000000", 000, null);
            //club.registerMember("Fernando", "Alonso", "99999999", "jovani", "0", "0000000000000000", 000, null);
            //club.registerMember("Fernando", "Alonso", "99999999", "skipy", "0", "0000000000000000", 000, null);
            //utilData.setLogin("99");
            //utilData.setPassword("99");
            
        //} catch (ClubDAOException ex) {
        //    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        //} catch (FileNotFoundException ex) {
        //    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        //}
        
        ////////////////////////////////////////////////////////////////////////
        infoLabel.setVisible(false);
        if(LocalTime.now().isAfter(LocalTime.of(21, 0))){
            infoLabel.setText("No se puede reservar ninguna pista para hoy, tienes que iniciar sesión para ver las pistas de mañana");
            infoLabel.setVisible(true);
        }
        
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
    
    ////Como usar la ventana modal esta loca :)/////////////////////////////////
    //1) setVentanaInfo(String titulo, String texto) o setVentanaConfirmar(String titulo, String texto)
    //2) ventanaAddNode() tantos como quieras
    //3) showVentana()
    ////////////////////////////////////////////////////////////////////////////
    public void showVentana(boolean show){
        if(show){
            ventanaPane.toFront();
        }else{
            ventanaPane.toBack();
        }
        mainBorderPane.setDisable(show);
        ventanaPane.setDisable(!show);
    }
    public void setVentanaInfo(String titulo, String botonAceptar){
        iButton.setVisible(false);
        dButton.setVisible(true);
        dButton.setText(botonAceptar);
        titleLabel.setText(titulo);
        ventanaVbox.getChildren().clear();
        ventanaVbox.setSpacing(0);
        ventanaVbox.setAlignment(Pos.TOP_CENTER);
    }
    public void setVentanaConfirmar(String titulo, String botonAceptar, String botonCancelar){
        iButton.setVisible(true);
        iButton.setText(botonCancelar);
        dButton.setVisible(true);
        dButton.setText(botonAceptar);
        titleLabel.setText(titulo);
        ventanaVbox.getChildren().clear();
        ventanaVbox.setSpacing(0);
        ventanaVbox.setAlignment(Pos.TOP_CENTER);
    }
    public void ventanaAddNode(Node nodo){
        ventanaVbox.getChildren().add(nodo);
    }
    
    /**ejecuta onPerfilButton como si se hubiera presionado, usado para llamarlo desde otra clase*/
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
        updateButtonText();
    }
    
    /**ejecuta onReservasButton como si se hubiera presionado, usado para llamarlo desde otra clase*/
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
        updateButtonText();
    }
    
    /**ejecuta onPistasButton como si se hubiera presionado, usado para llamarlo desde otra clase*/
    public void triggerOnPistasButton(){
        onPistasButton(new ActionEvent(this, pistasButton));
        pistasButton.requestFocus();
        pistasButton.setSelected(true);
    }
    @FXML
    private void onPistasButton(ActionEvent event) {
        Scene escena = utilData.escenas.get("Pistas");
        mainBorderPane.setCenter(null);
        mainBorderPane.setCenter(escena.getRoot());
        utilData.getPistasController().startPistas();
        updateButtonText();
    }
    
    /**Boton de cancelar de la ventana modal custom*/
    @FXML
    private void onIButton(ActionEvent event) {
        showVentana(false);//quita la ventana modal
    }

    /**Boton de aceptar de la ventana modal custom
     * filtra lo que tiene que hacer dependiendo de ventanaMode
     */
    @FXML
    private void onDButton(ActionEvent event) {
        
        switch(utilData.ventanaMode){
            case 0: //0:editardatos 
                FXMLPerfilController controller = utilData.getPerfilController();
                controller.editarDatos();
            break;
            case 1: // 1:registrarse
                utilData.showScene("Login");
                utilData.getLoginController().startLoggin();
            break;
            case 2: // 2:hacer reserva 
                FXMLpistaBoxController pistaController = utilData.getPistaBoxController();
                pistaController.safeRegisterBooking(LocalDateTime.now(), utilData.getSelectedDate());               
            break;
            case 3: // 3:nada 
            break;
            case 4: // 4: cancelar reserva
                FXMLpistaBoxController pistaController2 = utilData.getPistaBoxController();
                try {
                    club.removeBooking(pistaController2.reservaCancel);
                } catch (ClubDAOException ex) {Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);}
                pistaController2.updateButtonState();
            break;
            case 5: // 5: registrar y confirmar pagar con la tarjeta
                MiniTarjetaController miniTarjeta = utilData.getMiniTarjetaController();
                if(miniTarjeta.svcErrorLabelIsVisible() || miniTarjeta.numTarjetaErrorLabelIsVisible() || miniTarjeta.getNumTarjetaField().getText().isBlank() || miniTarjeta.getSvcField().getText().isBlank()){return;}//si hay alguna label no se hace nada, no se cierra la ventana
                Member memeber = club.getMemberByCredentials(utilData.getLogin(), utilData.getPassword());
                memeber.setCreditCard(utilData.getMiniTarjetaController().getNumTarjetaField().getText());
                memeber.setSvc(Integer.parseInt(utilData.getMiniTarjetaController().getSvcField().getText()));
                utilData.getReservasBoxController().reserva.setPaid(true);   //poner esta reserva como pagada
                utilData.getReservasController().startReservas();   //actualizar
            break;    
            case 6: // 6: confirmar pagar con tajeta
                utilData.getReservasBoxController().reserva.setPaid(true);
                utilData.getReservasController().startReservas();   //actualizar
            break;
            case 7:
                try {
                    club.removeBooking(utilData.getReservasBoxController().reserva); //elimina la reserva
                    utilData.getReservasController().updateMisReservas();   //actualiza la vista de reservas
                } catch (ClubDAOException | IOException ex) {} 
            break;
        }
        showVentana(false);//quita la ventana modal
    }

    @FXML
    private void onCerrarSesionButton(ActionEvent event) {
        utilData.setLogin("");
        utilData.setPassword("");
        cerrarSesionButton.setVisible(false);
        triggerOnPistasButton();
    }
    
}
