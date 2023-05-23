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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafxmlapplication.misReservas.FXMLReservasBoxController;
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
    @FXML
    private VBox ventanaHbox;
    
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
            club.addSimpleData();
            
            club.registerMember("Fernando", "Alonso", "99999999", "0", "0", "", 0, null); //registra un miembro de prueba
            //club.registerMember("Fernando", "Alonso", "99999999", "papo", "0", "0000000000000000", 000, null); //registra un miembro de prueba
            //club.registerMember("Fernando", "Alonso", "99999999", "antonio", "0", "0000000000000000", 000, null); //registra un miembro de prueba
            //club.registerMember("Fernando", "Alonso", "99999999", "yiyi", "0", "0000000000000000", 000, null); //registra un miembro de prueba
            //club.registerMember("Fernando", "Alonso", "99999999", "jovani", "0", "0000000000000000", 000, null); //registra un miembro de prueba
            //club.registerMember("Fernando", "Alonso", "99999999", "skipy", "0", "0000000000000000", 000, null); //registra un miembro de prueba
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
    public void setVentanaInfo(String titulo){
        iButton.setVisible(false);
        dButton.setVisible(true);
        titleLabel.setText(titulo);
        ventanaHbox.getChildren().clear();
    }
    public void setVentanaConfirmar(String titulo){
        iButton.setVisible(true);
        iButton.setVisible(true);
        titleLabel.setText(titulo);
        ventanaHbox.getChildren().clear();
    }
    public void ventanaAddNode(Node nodo){
        ventanaHbox.getChildren().add(nodo);
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
        perfilButton.setId("buttonTabSelected");
        reservasButton.setId("buttonDeault");
        pistasButton.setId("buttonDeault");
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
        perfilButton.setId("buttonDeault");
        reservasButton.setId("buttonTabSelected");
        pistasButton.setId("buttonDeault");
        updateButtonText();
    }
    
    /**ejecuta onPistasButton como si se hubiera presionado, usado para llamarlo desde otra clase*/
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
                controller.removeContraseñaField();
            break;
            case 1: // 1:registrarse
                utilData.showScene("Login");
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
            case 5: // 5: confirmar pagar con la tarjeta
                System.out.println("aSfgdsfljkahgkdafh");
                
                //utilData.getReservasBoxController().cambiarPagarAPagado();
            break;    

                
                
        }
        showVentana(false);//quita la ventana modal
    }
    
    public void createTarjetaFormulario(){
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setMaxHeight(20);
        hbox.setMinHeight(20);
        hbox.setMaxWidth(100);
        hbox.setMinWidth(100);
        hbox.getChildren().add(new Label("Tarjeta"));
        hbox.getChildren().add(new TextField());
        ventanaHbox.getChildren().add(hbox);
        showVentana(true);
    }
}
