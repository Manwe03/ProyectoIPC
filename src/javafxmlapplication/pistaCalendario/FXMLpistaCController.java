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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
    
    private static int[] buttonState = {0,0,0,0,0,0,0,0,0,0,0,0,0};
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Pone el tamaño de los botones con respecto a los puntos por pulgada
        UtilData util = UtilData.getInstance();
        util.setSize_DPI(vBoxPista,2.2+0.3,0.35*13+0.4);
        
        util.setSize_DPI(b_09, 2.2, 0.35);
        util.setSize_DPI(b_10, 2.2, 0.35);
        util.setSize_DPI(b_11, 2.2, 0.35);
        util.setSize_DPI(b_12, 2.2, 0.35);
        util.setSize_DPI(b_13, 2.2, 0.35);
        util.setSize_DPI(b_14, 2.2, 0.35);
        util.setSize_DPI(b_15, 2.2, 0.35);
        util.setSize_DPI(b_16, 2.2, 0.35);
        util.setSize_DPI(b_17, 2.2, 0.35);
        util.setSize_DPI(b_18, 2.2, 0.35);
        util.setSize_DPI(b_19, 2.2, 0.35);
        util.setSize_DPI(b_20, 2.2, 0.35);
        util.setSize_DPI(b_21, 2.2, 0.35);
    }    

    public void setData(Court court,LocalDate madeForDay){
        this.court = court;             //Pista
        this.madeForDay = madeForDay;
        updateButtonState();
    }
    
    private void updateButtonState(){ 
        try {
            //0 = no reservado || 1 = reservado || 2 = reservado por mi
            List<Booking> reservasDelDia = Club.getInstance().getCourtBookings(court.getName(), madeForDay); //obtiene las reservas del dia
            for(int i = 0; i < 13; i++){buttonState[i] = 0;}//todas a 0
            for(Booking reserva: reservasDelDia){ //recorre las reservas
                System.out.println(reserva.toString());
                if(reserva.getMember().equals(Club.getInstance().getMemberByCredentials(UtilData.getInstance().getLogin(), UtilData.getInstance().getPassword()))){
                    buttonState[reserva.getFromTime().getHour()-9] = 2;
                }else{
                    buttonState[reserva.getFromTime().getHour()-9] = 1;
                }
            }
            updateDisplay();
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLpistaCController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLpistaCController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updateDisplay(){
        updateButton(b_09,buttonState[0]);
        updateButton(b_10,buttonState[1]);
        updateButton(b_11,buttonState[2]);
        updateButton(b_12,buttonState[3]);
        updateButton(b_13,buttonState[4]);
        updateButton(b_14,buttonState[5]);
        updateButton(b_15,buttonState[6]);
        updateButton(b_16,buttonState[7]);
        updateButton(b_17,buttonState[8]);
        updateButton(b_18,buttonState[9]);
        updateButton(b_19,buttonState[10]);
        updateButton(b_20,buttonState[11]);
        updateButton(b_21,buttonState[12]);
    }
    
    private void updateButton(Button boton, int state){
        switch(state){
            case 0:
                boton.setText("Libre");
                break;
            case 1:
                boton.setText("Reservado");
                break;
            case 2:      
                boton.setText("Reservado por mi");
                break;
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
                updateButtonState();
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
