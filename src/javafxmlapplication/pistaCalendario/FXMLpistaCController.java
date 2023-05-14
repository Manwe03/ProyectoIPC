/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.pistaCalendario;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalDateTimeStringConverter;
import javafxmlapplication.FXMLDocumentController;
import javafxmlapplication.UtilData;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Court;
import model.Member;

/**
 * FXML Controller class
 *
 * @author manub
 */
public class FXMLpistaCController implements Initializable {


    @FXML
    private VBox vBoxPista;
    
    private Court court; //pista
    
    private LocalDate madeForDay; //Dia
    
    @FXML
    private Button b_09;
    @FXML
    private Button b_10;
    @FXML
    private Button b_11;
    @FXML
    private Button b_12;
    @FXML
    private Button b_13;
    @FXML
    private Button b_14;
    @FXML
    private Button b_15;
    @FXML
    private Button b_16;
    @FXML
    private Button b_17;
    @FXML
    private Button b_18;
    @FXML
    private Button b_19;
    @FXML
    private Button b_20;
    @FXML
    private Button b_21;
    
    List<Booking> reservasDelDia;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Pone el tamaño de los botones con respecto a los puntos por pulgada
        double dpi = UtilData.getInstance().getDpi();
        vBoxPista.setMaxWidth(dpi * 2.5);
        vBoxPista.setMinWidth(dpi * 2.5);

    }    

    public void setData(Court court,LocalDate madeForDay){
        this.court = court;             //Pista
        this.madeForDay = madeForDay;
        try {
            this.reservasDelDia = Club.getInstance().getCourtBookings(court.getName(),madeForDay);
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLpistaCController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLpistaCController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**Hace la reserva. Devuelve true si se puede hacer la reserva false si no.*/
    private boolean safeRegisterBooking(LocalDateTime bookingDate, LocalDate madeForDay, LocalTime fromHour, boolean paid, Court court, Member member){
        try {
            List<Booking> reservasDelDia = Club.getInstance().getCourtBookings(court.getName(), madeForDay); //obtiene las reservas del dia
            Boolean reservaDuplicada = false;
            for(Booking reserva: reservasDelDia){ //recorre las reservas buscando una con la misma hora que la que queremos reservar
                if(reserva.getFromTime().equals(fromHour)){ //si existe una, no se reserva y devuelve un fallo
                    reservaDuplicada = true;
                }
            } //si lo recorre entero sin encontrar ningun duplicado exito
            
            if(!reservaDuplicada){ //si se puede reservar a esa hora es dicir no hay una reserva a la misma hora, hace la reserva
                Club.getInstance().registerBooking(bookingDate, madeForDay, fromHour, paid, court, member);
                System.out.println("Reserva Exitosa");
                return true;//exito
            }
            else{   //si no se puede hacer la reserva
                //FALTA avisar al usuario
                System.out.println("Reserva Fallida");
                return false;//fallo
            }
        } catch (ClubDAOException ex) { //mitico catch
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;//fallo
    }

    @FXML
    private void onCalendarButton(ActionEvent event) throws ClubDAOException, IOException {
        Button button = (Button) event.getSource();//obtiene el boton sobre el que se a realizado el evento
        
        Member member = Club.getInstance().getMemberByCredentials(UtilData.getInstance().getLogin(), UtilData.getInstance().getPassword());//obtiene el miembro logeado actualmente
        LocalTime fromtime = LocalTime.of(Integer.parseInt( button.getId().substring(2, 4)),0);//obtiene la hora de la reserva
        
        safeRegisterBooking(LocalDateTime.now(), UtilData.getInstance().getSelectedDate(), fromtime , member.checkHasCreditInfo(), court, member);//intenta registrar una reserva
    }
}
