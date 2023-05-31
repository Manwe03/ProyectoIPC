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
    
    int numButton;
    
    UtilData utilData;
    
    FXMLDocumentController mainController;
    
    public Booking reservaCancel;//reserva a cancelar

    @FXML
    private GridPane gridPane;

  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Pone el tamaño de los botones con respecto a los puntos por pulgada
        try {club = Club.getInstance();} catch (ClubDAOException | IOException ex) {}
        this.utilData = UtilData.getInstance();
        
        mainController = utilData.getMainController();
        
        utilData.setSize_DPI(gridPane,3.7,7);

        pressedState = new byte[13]; // 0 posicion normal // 1 animacion activa
        reservaState = new byte[17]; // 0 libre // 1 reservado // 2 reservado por mi // dos vacios al principio y al final
        hoveredState = new boolean[13];
        for(int i = 0; i<13; i++){
            pressedState[i] = 0;
            hoveredState[i] = false;
        }
        for(int i = 0; i<17; i++){
            reservaState[i] = 0;
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
        
        ////////////////////////////////////////////////////////////////////////
        //Desactivar las horas en el pasado
        ////////////////////////////////////////////////////////////////////////
        if(madeForDay.equals(LocalDate.now())){//si esta pista_box es de hoy
            disableReservasEnElPasado(b_09,9);
            disableReservasEnElPasado(b_10,10);
            disableReservasEnElPasado(b_11,11);
            disableReservasEnElPasado(b_12,12);
            disableReservasEnElPasado(b_13,13);
            disableReservasEnElPasado(b_14,14);
            disableReservasEnElPasado(b_15,15);
            disableReservasEnElPasado(b_16,16);
            disableReservasEnElPasado(b_17,17);
            disableReservasEnElPasado(b_18,18);
            disableReservasEnElPasado(b_19,19);
            disableReservasEnElPasado(b_20,20);
            disableReservasEnElPasado(b_21,21);
        }
        
    }
    
    private void disableReservasEnElPasado(Button boton, int hora){
        if(hora <= LocalTime.now().getHour()){
            boton.setDisable(true);
        }
    }
    
    public void updateButtonState(){ 
        //0 = no reservado || 1 = reservado || 2 = reservado por mi
        List<Booking> reservasDelDia = club.getCourtBookings(court.getName(), madeForDay); //obtiene las reservas del dia
        for(int i = 0; i < 17; i++){
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
                reservaState[reserva.getFromTime().getHour()-7] = 2;

            }
            else{ //si la reserva no es tuya
                reservaState[reserva.getFromTime().getHour()-7] = 1;
                
            }
        }
        updateButtonsStyle();
    }
    private void updateButtonsStyle(){
        setStyleOnContext(b_09,reservaState[2]);
        setStyleOnContext(b_10,reservaState[3]);
        setStyleOnContext(b_11,reservaState[4]);
        setStyleOnContext(b_12,reservaState[5]);
        setStyleOnContext(b_13,reservaState[6]);
        setStyleOnContext(b_14,reservaState[7]);
        setStyleOnContext(b_15,reservaState[8]);
        setStyleOnContext(b_16,reservaState[9]);
        setStyleOnContext(b_17,reservaState[10]);
        setStyleOnContext(b_18,reservaState[11]);
        setStyleOnContext(b_19,reservaState[12]);
        setStyleOnContext(b_20,reservaState[13]);
        setStyleOnContext(b_21,reservaState[14]);
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
    
    /** * Hace la reserva.Devuelve true si se puede hacer la reserva false si no.
     * @param bookingDate
     * @param madeForDay
     * @return exito*/
    public boolean safeRegisterBooking(LocalDateTime bookingDate, LocalDate madeForDay){
        Member member = club.getMemberByCredentials(utilData.getLogin(), utilData.getPassword());//obtiene el miembro logeado actualmente
        boolean paid = false;
        if(member.checkHasCreditInfo() && !member.getCreditCard().isBlank() && member.getSvc() != 0){
            paid = true;
        }
        
        try {
            List<Booking> reservasDelDia = Club.getInstance().getCourtBookings(court.getName(), madeForDay); //obtiene las reservas del dia
            Boolean reservaDuplicada = false;
            LocalTime fromHour = LocalTime.of(numButton,0);//obtiene la hora de la reserva
            for(Booking reserva: reservasDelDia){ //recorre las reservas buscando una con la misma hora que la que queremos reservar
                if(reserva.getFromTime().equals(fromHour)){ //si existe una, no se reserva y devuelve un fallo
                    reservaDuplicada = true;
                }
            } //si lo recorre entero sin encontrar ningun duplicado exito
            
            if(!reservaDuplicada){ //si se puede reservar a esa hora es dicir no hay una reserva a la misma hora, hace la reserva
                club.registerBooking(bookingDate, madeForDay, fromHour, false, court, member).setPaid(paid);
                
                
                updateButtonState();
                //updateButtonsText();
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
    
    private void onCalendarButtons(){
        //Button button = (Button) event.getSource();//obtiene el boton sobre el que se a realizado el evento
        //int numButton = Integer.parseInt( button.getId().substring(2, 4));
        utilData.setPistaBoxController(this);
        updateButtonState();
        switch (reservaState[numButton-7]) {
            case 0: //si no esta reservada
                //System.out.println("Libre");
                if(utilData.isLogged()){//si estas loggeado -> Reservar
                    ////////////////////////////////////////////////////////////
                    //Comprobar que no hay 3 reservas seguidas
                    boolean reservable = true;
                    int num = numButton-7;

                    //0%12= 0 a 12%12= 0
                    if(reservaState[num-1] == 2 && (reservaState[num-2] == 2 || reservaState[num+1] == 2)){
                        reservable = false; 
                    }
                    if(reservaState[num+1] == 2 && reservaState[num+2] == 2){
                        reservable = false; 
                    }

                    if(!reservable){//si no es reservable no se reserva informar al usuario
                        utilData.ventanaMode = 3;//modo de la ventana para que no haga nada
                        mainController.showVentana(true);//enseña la ventana
                        mainController.setVentanaInfo("Error","Aceptar");
                        mainController.ventanaAddNode(new Label("No se pueden reservar más de dos horas seguidas en una pista"));
                        break;
                    }
                    //mandarlo a la ventana
                    utilData.setPistaBoxController(this);//guarda el controlador para que la ventana modal sepa donde ejecutar el register
                    utilData.ventanaMode = 2;//modo de la ventana
                    mainController.showVentana(true);//enseña la ventana
                    //pone la información de la ventana
                    mainController.setVentanaConfirmar("Reservar pista","Reservar","Cancelar");
                    mainController.ventanaAddNode(new Label("¿Estás seguro de reservar una pista?\nLas reservas solo se pueden cancelar con más de 24H de antelación"));    
                    
                }else{//si no estas loggeado -> Notificar
                    utilData.showScene("Login");
                    utilData.getLoginController().startLoggin();
                }
            break;
            case 1: //si esta reservada por otro
                
                //System.out.println("Reservada por otro");
                updateButtonState();
            break;
            case 2: //si esta reservada por ti
                if(!utilData.isLogged()){break;}//Se verifica que estes logueado por si acaso
                
                List<Booking> reservasDelDia; 
                try {
                    reservasDelDia = Club.getInstance().getCourtBookings(court.getName(), madeForDay);
                    LocalTime fromtime = LocalTime.of(numButton,0);//obtiene la hora de la reserva
                    
                    if(LocalDate.now().equals(madeForDay)){//si se quiere cancelar una reserva para hoy, no se permite
                        utilData.ventanaMode = 3;//nada
                        mainController.showVentana(true);
                        mainController.setVentanaInfo("No se puede cancelar la reserva","Aceptar");
                        mainController.ventanaAddNode(new Label("No se pueden cancelar reservas con menos de 24H de antelación"));
                    }
                    else if(LocalDate.now().plusDays(1).equals(madeForDay) && (fromtime.compareTo(LocalTime.now()) < 0)){//si es para mañana y la reserva tiene una fecha posterior a la hora actual
                        utilData.ventanaMode = 3;//nada
                        mainController.showVentana(true);
                        mainController.setVentanaInfo("No se puede cancelar la reserva","Aceptar");
                        mainController.ventanaAddNode(new Label("No se pueden cancelar reservas con menos de 24H de antelación"));
                        
                    }
                    else{
                        for(int i = 0; i<reservasDelDia.size();i++){
                            if(reservasDelDia.get(i).getFromTime().equals(fromtime)){
                                reservaCancel = reservasDelDia.get(i); //poner la reserva que hay que cancelar

                                utilData.ventanaMode = 4;//modo cancelar reserva
                                mainController.showVentana(true);
                                mainController.setVentanaConfirmar("Cancelar reserva","Aceptar","Cancelar");
                                mainController.ventanaAddNode(new Label("¿Estás seguro de que quieres cancelar la reserva?"));
                                break;
                            }
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
                node.setText("Ocupado por: " + miembro.getNickName()); 
            }
        }else{
            if(hoveredState[i]){
                node.setText("Cancelar reserva");
            }else{
                node.setText("Reservado");
            }
        }
        hoveredState[i] = !hoveredState[i];
    }

    @FXML
    private void OnB_09(ActionEvent event) {
        numButton = 9;
        onCalendarButtons();
    }

    @FXML
    private void onB_10(ActionEvent event) {
        numButton = 10;
        onCalendarButtons();
    }

    @FXML
    private void onB_11(ActionEvent event) {
        numButton = 11;
        onCalendarButtons();
    }

    @FXML
    private void onB_12(ActionEvent event) {
        numButton = 12;
        onCalendarButtons();
    }

    @FXML
    private void onB_13(ActionEvent event) {
        numButton = 13;
        onCalendarButtons();
    }

    @FXML
    private void onB_14(ActionEvent event) {
        numButton = 14;
        onCalendarButtons();
    }

    @FXML
    private void onB_15(ActionEvent event) {
        numButton = 15;
        onCalendarButtons();
    }

    @FXML
    private void onB_16(ActionEvent event) {
        numButton = 16;
        onCalendarButtons();
    }

    @FXML
    private void onB_17(ActionEvent event) {
        numButton = 17;
        onCalendarButtons();
    }

    @FXML
    private void onB_18(ActionEvent event) {
        numButton = 18;
        onCalendarButtons();
    }

    @FXML
    private void onB_19(ActionEvent event) {
        numButton = 19;
        onCalendarButtons();
    }

    @FXML
    private void onB_20(ActionEvent event) {
        numButton = 20;
        onCalendarButtons();
    }

    @FXML
    private void onB_21(ActionEvent event) {
        numButton = 21;
        onCalendarButtons();
    }
}
