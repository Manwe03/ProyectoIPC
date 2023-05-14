/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.misReservas;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.Booking;
import model.ClubDAOException;

/**
 * FXML Controller class
 *
 * @author manub
 */
public class FXMLReservasController implements Initializable {

    Booking reserva;
    
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setData(Booking MIreserva){
        this.reserva = MIreserva;
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
        System.out.println("cancelado");
    }
    
}
