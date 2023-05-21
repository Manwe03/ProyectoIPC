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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
public class FXMLpistaBoxController implements Initializable {

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
    @FXML
    private Label pistaLabel;
    
    private Club club;
    
    private static byte[] pressedState;
    private static byte[] reservaState;
    private static boolean[] hoveredState;
    
    UtilData utilData;
    
    @FXML
    private VBox hourVBox;
    @FXML
    private GridPane gridPane;
    @FXML
    private Pane offsetPane;
   
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Pone el tamaño de los botones con respecto a los puntos por pulgada
        try {club = Club.getInstance();} catch (ClubDAOException | IOException ex) {}
        this.utilData = UtilData.getInstance();
        
        utilData.setSize_DPI(offsetPane,0.2,0.15);
        utilData.setSize_DPI(gridPane,3.7,7);
        hourVBox.setSpacing(utilData.getDpi()*0.264);
        
        try {
            club = Club.getInstance();
        } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(FXMLpistaBoxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
        pressedState = new byte[13]; // 0 posicion normal // 1 animacion activa
        reservaState = new byte[13]; // 0 libre // 1 reservado // 2 reservado por mi
        hoveredState = new boolean[13];
        for(int i = 0; i<13; i++){
            pressedState[i] = 0;
            reservaState[i] = 0;
            hoveredState[i] = false;
        }
        //inicia el tamaño del label con respecto a la escala
        //pistaLabel.setFont(Font.font("system", FontWeight.NORMAL, FontPosture.REGULAR, utilData.getDpi()*0.2));
        
        utilData.setSize_DPI(pistaLabel, 2.2, 0.35);
        
        utilData.setSize_DPI(b_09, 2.5, 0.4);
        utilData.setSize_DPI(b_10, 2.5, 0.4);
        utilData.setSize_DPI(b_11, 2.5, 0.4);
        utilData.setSize_DPI(b_12, 2.5, 0.4);
        utilData.setSize_DPI(b_13, 2.5, 0.4);
        utilData.setSize_DPI(b_14, 2.5, 0.4);
        utilData.setSize_DPI(b_15, 2.5, 0.4);
        utilData.setSize_DPI(b_16, 2.5, 0.4);
        utilData.setSize_DPI(b_17, 2.5, 0.4);
        utilData.setSize_DPI(b_18, 2.5, 0.4);
        utilData.setSize_DPI(b_19, 2.5, 0.4);
        utilData.setSize_DPI(b_20, 2.5, 0.4);
        utilData.setSize_DPI(b_21, 2.5, 0.4);
        

        
        b_09.pressedProperty().addListener((observable,oldVal,newVal)-> { pressedState[0] = UtilData.translatePressedButton(b_09, pressedState[0]); });
        b_10.pressedProperty().addListener((observable,oldVal,newVal)-> { pressedState[1] = UtilData.translatePressedButton(b_10, pressedState[1]); });
        b_11.pressedProperty().addListener((observable,oldVal,newVal)-> { pressedState[2] = UtilData.translatePressedButton(b_11, pressedState[2]); });
        b_12.pressedProperty().addListener((observable,oldVal,newVal)-> { pressedState[3] = UtilData.translatePressedButton(b_12, pressedState[3]); });
        b_13.pressedProperty().addListener((observable,oldVal,newVal)-> { pressedState[4] = UtilData.translatePressedButton(b_13, pressedState[4]); });
        b_14.pressedProperty().addListener((observable,oldVal,newVal)-> { pressedState[5] = UtilData.translatePressedButton(b_14, pressedState[5]); });
        b_15.pressedProperty().addListener((observable,oldVal,newVal)-> { pressedState[6] = UtilData.translatePressedButton(b_15, pressedState[6]); });
        b_16.pressedProperty().addListener((observable,oldVal,newVal)-> { pressedState[7] = UtilData.translatePressedButton(b_16, pressedState[7]); });
        b_17.pressedProperty().addListener((observable,oldVal,newVal)-> { pressedState[8] = UtilData.translatePressedButton(b_17, pressedState[8]); });
        b_18.pressedProperty().addListener((observable,oldVal,newVal)-> { pressedState[9] = UtilData.translatePressedButton(b_18, pressedState[9]); });
        b_19.pressedProperty().addListener((observable,oldVal,newVal)-> { pressedState[10] = UtilData.translatePressedButton(b_19, pressedState[10]); });
        b_20.pressedProperty().addListener((observable,oldVal,newVal)-> { pressedState[11] = UtilData.translatePressedButton(b_20, pressedState[11]); });
        b_21.pressedProperty().addListener((observable,oldVal,newVal)-> { pressedState[12] = UtilData.translatePressedButton(b_21, pressedState[12]); });
        
        
        b_09.hoverProperty().addListener((observable,oldVal,newVal)-> { setTextOnContext(b_09,hoveredState,0); });
        b_10.hoverProperty().addListener((observable,oldVal,newVal)-> { setTextOnContext(b_10,hoveredState,1); });
        b_11.hoverProperty().addListener((observable,oldVal,newVal)-> { setTextOnContext(b_11,hoveredState,2); });
        b_12.hoverProperty().addListener((observable,oldVal,newVal)-> { setTextOnContext(b_12,hoveredState,3); });
        b_13.hoverProperty().addListener((observable,oldVal,newVal)-> { setTextOnContext(b_13,hoveredState,4); });
        b_14.hoverProperty().addListener((observable,oldVal,newVal)-> { setTextOnContext(b_14,hoveredState,5); });
        b_15.hoverProperty().addListener((observable,oldVal,newVal)-> { setTextOnContext(b_15,hoveredState,6); });
        b_16.hoverProperty().addListener((observable,oldVal,newVal)-> { setTextOnContext(b_16,hoveredState,7); });
        b_17.hoverProperty().addListener((observable,oldVal,newVal)-> { setTextOnContext(b_17,hoveredState,8); });
        b_18.hoverProperty().addListener((observable,oldVal,newVal)-> { setTextOnContext(b_18,hoveredState,9); });
        b_19.hoverProperty().addListener((observable,oldVal,newVal)-> { setTextOnContext(b_19,hoveredState,10); });
        b_20.hoverProperty().addListener((observable,oldVal,newVal)-> { setTextOnContext(b_20,hoveredState,11); });
        b_21.hoverProperty().addListener((observable,oldVal,newVal)-> { setTextOnContext(b_21,hoveredState,12); });
        
    }    

    public void setData(Court court,LocalDate madeForDay){
        this.court = court;             //Pista
        this.madeForDay = madeForDay;
        updateButtonState();
        pistaLabel.setText(court.getName());
        
        updateButtonsText();
    }
    
    private void updateButtonState(){ 
        //0 = no reservado || 1 = reservado || 2 = reservado por mi
        List<Booking> reservasDelDia = club.getCourtBookings(court.getName(), madeForDay); //obtiene las reservas del dia
        for(int i = 0; i < 13; i++){
            reservaState[i] = 0;//ponemos los datos de la columna 0 a 0
        }
        for(Booking reserva: reservasDelDia){ //recorre las reservas
            Member miembro = reserva.getMember();
            Member yo;
            try{
                yo = club.getMemberByCredentials(utilData.getLogin(), utilData.getPassword());
            }catch(NullPointerException e){
                yo = null;
            }
            if(miembro == null){
                
            }else if(miembro.equals(yo)){//si la reserva es tuya
                reservaState[reserva.getFromTime().getHour()-9] = 2;

            }
            else{ //si la reserva no es tuya
                reservaState[reserva.getFromTime().getHour()-9] = 1;
                
            }
        }
        updateButtonsStyle();
    }
    private void updateButtonsStyle(){
        setStyleOnContext(b_09,reservaState[0]);
        setStyleOnContext(b_10,reservaState[1]);
        setStyleOnContext(b_11,reservaState[2]);
        setStyleOnContext(b_12,reservaState[3]);
        setStyleOnContext(b_13,reservaState[4]);
        setStyleOnContext(b_14,reservaState[5]);
        setStyleOnContext(b_15,reservaState[6]);
        setStyleOnContext(b_16,reservaState[7]);
        setStyleOnContext(b_17,reservaState[8]);
        setStyleOnContext(b_18,reservaState[9]);
        setStyleOnContext(b_19,reservaState[10]);
        setStyleOnContext(b_20,reservaState[11]);
        setStyleOnContext(b_21,reservaState[12]);
    }
    private void updateButtonsText(){
        setTextOnContext(b_09,hoveredState,0);
        setTextOnContext(b_10,hoveredState,1);
        setTextOnContext(b_11,hoveredState,2);
        setTextOnContext(b_12,hoveredState,3);
        setTextOnContext(b_13,hoveredState,4);
        setTextOnContext(b_14,hoveredState,5);
        setTextOnContext(b_15,hoveredState,6);
        setTextOnContext(b_16,hoveredState,7);
        setTextOnContext(b_17,hoveredState,8);
        setTextOnContext(b_18,hoveredState,9);
        setTextOnContext(b_19,hoveredState,10);
        setTextOnContext(b_20,hoveredState,11);
        setTextOnContext(b_21,hoveredState,12);
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
                //System.out.println("Reserva Fallida");
                return false;//fallo
            }
        } catch (ClubDAOException | IOException ex) { //mitico catch
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;//fallo
    }
    
    private void onCalendarButtons(int numButton){
        //Button button = (Button) event.getSource();//obtiene el boton sobre el que se a realizado el evento
        //int numButton = Integer.parseInt( button.getId().substring(2, 4));
        Member member;
        switch (reservaState[numButton-9]) {
            case 0: //si no esta reservada
                //System.out.println("Libre");
                if(utilData.isLogged()){//si estas loggeado -> Reservar
                    member = club.getMemberByCredentials(utilData.getLogin(), utilData.getPassword());//obtiene el miembro logeado actualmente
                    if(member != null){
                        LocalTime fromtime = LocalTime.of(numButton,0);//obtiene la hora de la reserva
                        safeRegisterBooking(LocalDateTime.now(), utilData.getSelectedDate(), fromtime , member.checkHasCreditInfo(), court, member);//intenta registrar una reserva
                        updateButtonState();
                    }
                }else{//si no estas loggeado -> Notificar
                    utilData.showScene("Login");
                }
            break;
            case 1: //si esta reservada por otro
                
                //System.out.println("Reservada por otro");
                updateButtonState();
            break;
            case 2: //si esta reservada por ti
                if(!utilData.isLogged()){break;}//Se verifica que estes logeado por si acaso
                
                List<Booking> reservasDelDia; 
                try {
                    reservasDelDia = Club.getInstance().getCourtBookings(court.getName(), madeForDay);
                    LocalTime fromtime = LocalTime.of(numButton,0);//obtiene la hora de la reserva
                    Booking reserva;
                    for(int i = 0; i<reservasDelDia.size();i++){
                        //System.out.println("Reservada por ti");
                        reserva = reservasDelDia.get(i);
                        if(reserva.getFromTime().equals(fromtime)){
                            club.removeBooking(reserva);
                            updateButtonState();
                            System.out.println("Reserva cancelada");
                            break;
                        }
                    }
                } catch (ClubDAOException | IOException ex) {
                    Logger.getLogger(FXMLpistaBoxController.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
        }
    }
    //State:
    //columna 0 estados 0 1 2 : 0 libre 1 reservado 2 reservado por mi
    //columna 1 estados 0 1   : 0 posicion normal   1 animacion activa
    private void setStyleOnContext(Button node, int state){
        switch (state) {
            case 0:
                //libre//
                node.setId("buttonLibre");
                break;
            case 1:
                //reservado por otro//
                node.setId("buttonReservado");
                break;
            case 2:
                //reservado por ti//
                node.setId("buttonReservadoMi");
                break;
        }
    }
    
    private void setTextOnContext(Button node, boolean[] hoveredState, int i){
        if(node.getId().equals("buttonLibre")){
            if(hoveredState[i]){
                node.setText("Reservar");
            }else{
                node.setText("Libre");
            }
        }else if(node.getId().equals("buttonReservado")){
            List<Booking> reservasDelDia = club.getCourtBookings(court.getName(), madeForDay); //obtiene las reservas del dia
            Member miembro = null;
            for(Booking reserva: reservasDelDia){ //recorre las reservas
                if(reserva.getFromTime().equals(LocalTime.of(i+9, 0))){
                    miembro = reserva.getMember();
                    break;
                }
            }
            if(hoveredState[i]){
                node.setText(miembro.getNickName());  
            }else{
                node.setText("Ocupado Por: " + miembro.getNickName()); 
            }
        }else{
            if(hoveredState[i]){
                node.setText("Cancelar");
            }else{
                node.setText("Reservado");
            }
        }
        hoveredState[i] = !hoveredState[i];
    }

    @FXML
    private void OnB_09(ActionEvent event) {
        onCalendarButtons(9);
    }

    @FXML
    private void onB_10(ActionEvent event) {
        onCalendarButtons(10);
    }

    @FXML
    private void onB_11(ActionEvent event) {
        onCalendarButtons(11);
    }

    @FXML
    private void onB_12(ActionEvent event) {
        onCalendarButtons(12);
    }

    @FXML
    private void onB_13(ActionEvent event) {
        onCalendarButtons(13);
    }

    @FXML
    private void onB_14(ActionEvent event) {
        onCalendarButtons(14);
    }

    @FXML
    private void onB_15(ActionEvent event) {
        onCalendarButtons(15);
    }

    @FXML
    private void onB_16(ActionEvent event) {
        onCalendarButtons(16);
    }

    @FXML
    private void onB_17(ActionEvent event) {
        onCalendarButtons(17);
    }

    @FXML
    private void onB_18(ActionEvent event) {
        onCalendarButtons(18);
    }

    @FXML
    private void onB_19(ActionEvent event) {
        onCalendarButtons(19);
    }

    @FXML
    private void onB_20(ActionEvent event) {
        onCalendarButtons(20);
    }

    @FXML
    private void onB_21(ActionEvent event) {
        onCalendarButtons(21);
    }
}
