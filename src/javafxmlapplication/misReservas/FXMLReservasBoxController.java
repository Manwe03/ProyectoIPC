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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafxmlapplication.FXMLDocumentController;
import javafxmlapplication.UtilData;
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

    Booking reserva;
    
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
    }    
    
    public void setData(Booking MIreserva, FXMLReservasController parentController){
        this.reserva = MIreserva;
        this.parentController = parentController;
        fechaL.setText(reserva.getMadeForDay().toString());
        horaL.setText(reserva.getFromTime().toString());
        pista.setText(reserva.getCourt().getName());
    }
    
    @FXML
    private void onPagar(ActionEvent event) throws ClubDAOException, IOException {
        System.out.println("pagado");
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        try {
            System.out.println("cancelado");
            Club.getInstance().removeBooking(this.reserva);
            parentController.updateMisReservas();
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLReservasBoxController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLReservasBoxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
