/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.misReservas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafxmlapplication.UtilData;
import javafxmlapplication.miniTarjeta.MiniTarjetaController;
import javafxmlapplication.reservas.FXMLReservasController;
import model.Booking;
import model.Club;
import model.ClubDAOException;

/**
 * FXML Controller class
 *
 * @author manub
 */
public class FXMLReservasBoxController implements Initializable {

    public Booking reserva;
    
    FXMLReservasController parentController;
    
    @FXML
    private HBox r1;
    @FXML
    private Label fechaL;
    @FXML
    private Label horaL;
    @FXML
    private Button pagarB;
    @FXML
    private Button cancelarB;
    @FXML
    private Label pista;
    
    private Club club;
    private UtilData utilData;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {club = Club.getInstance();} catch (ClubDAOException | IOException ex) {}
        this.utilData = UtilData.getInstance();
        utilData.setReservasBoxController(this);
    }    
    
    public void setData(Booking MIreserva, FXMLReservasController parentController){
        this.reserva = MIreserva;
        this.parentController = parentController;
        fechaL.setText(reserva.getMadeForDay().toString());
        horaL.setText(reserva.getFromTime().toString() + " - " + reserva.getFromTime().plusHours(1).toString());
        pista.setText(reserva.getCourt().getName());
        if(MIreserva.getPaid()){
            pagarB.setDisable(true);
            pagarB.setText("Pagado");
        }
    }
    
    @FXML
    private void onPagar(ActionEvent event) throws ClubDAOException, IOException {
        utilData.setReservasBoxController(this);//mandamos este controlador a la ventana y demas
        //Si tiene tarjeta de credito pagar la reserva y mandarlo a la ventana de info
        if(Club.getInstance().hasCreditCard(utilData.getLogin())) {
            utilData.ventanaMode = 6;
            utilData.setReservasBoxController(this);
            utilData.getMainController().setVentanaConfirmar(
                    "¿Pagar con tarjeta xxxx-xxxx-xxxx-" + club.getMemberByCredentials(utilData.getLogin(), utilData.getPassword()).getCreditCard().substring(12) + "?",
                    "Pagar","Cancelar");
            utilData.getMainController().showVentana(true);
            
        //Si no tiene tarjeta, mandarlo a la ventana -> registrar tarjeta -> pagar reserva
        } else { //registrar la tarjeta de crédito
            utilData.ventanaMode = 5;
            utilData.getMainController().setVentanaConfirmar("Añadir tarjeta de crédito","Pagar","Cancelar");
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("/javafxmlapplication/miniTarjeta/miniTarjeta.fxml"));
            BorderPane pistaBox = loader.load();
            MiniTarjetaController controler = loader.getController();
            utilData.setMiniTarjetaController(controler);
            utilData.getMainController().ventanaAddNode(pistaBox);
            utilData.getMainController().showVentana(true);
        }
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        try {
            utilData.ventanaMode = 7;
            utilData.getMainController().setVentanaConfirmar("Cancelar Reserva","Aceptar","Cancelar");
            utilData.getMainController().ventanaAddNode(new Label("¿Seguro que quieres cancelar la reserva?"));
            utilData.getMainController().showVentana(true);
            
            System.out.println("cancelado");
            parentController.updateMisReservas();
        } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(FXMLReservasBoxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cambiarPagarAPagado() {
        pagarB.setText("Pagado");
        pagarB.setDisable(true);
        this.reserva.setPaid(true);
        System.out.println("Pagar -> Pagado");
    }
    
}
