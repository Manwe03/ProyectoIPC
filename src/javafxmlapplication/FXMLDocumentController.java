/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
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
public class FXMLDocumentController implements Initializable {

    double dpi;
    
    String login;
    
    String password;
    
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/mm/yyyy");
    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("hh:mm:ss");
    
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private FlowPane flowPane;
    @FXML
    private TabPane tabPane;
    
    
    
    @FXML
    private VBox vBoxPista1;
 
    @FXML
    private VBox vBoxPista2;
    @FXML
    private VBox vBoxPista3;
    @FXML
    private VBox vBoxPista4;
    @FXML
    private VBox vBoxPista5;
    @FXML
    private VBox vBoxPista6;
    @FXML
    private Button b1_10;
    @FXML
    private Button b1_11;
    @FXML
    private Button b1_12;
    @FXML
    private Button b1_13;
    @FXML
    private Button b1_14;
    @FXML
    private Button b1_15;
    @FXML
    private Button b1_16;
    @FXML
    private Button b1_17;
    @FXML
    private Button b1_18;
    @FXML
    private Button b1_19;
    @FXML
    private Button b1_20;
    @FXML
    private Button b1_21;
    
    @FXML
    private HBox r1;
    @FXML
    private HBox r2;
    @FXML
    private HBox r3;
    @FXML
    private HBox r4;
    @FXML
    private HBox r5;
    @FXML
    private HBox r6;
    @FXML
    private HBox r7;
    @FXML
    private HBox r8;
    @FXML
    private HBox r9;
    @FXML
    private HBox r10;
    @FXML
    private Label nombreUsuarioReservas;
    @FXML
    private Label f1;
    @FXML
    private Label h1;
    @FXML
    private Label pi1;
    @FXML
    private Button p1;
    @FXML
    private Button c1;
    @FXML
    private Label f2;
    @FXML
    private Label h2;
    @FXML
    private Label pi2;
    @FXML
    private Button p2;
    @FXML
    private Button c2;
    @FXML
    private Label f3;
    @FXML
    private Label h3;
    @FXML
    private Label pi3;
    @FXML
    private Button p3;
    @FXML
    private Button c3;
    @FXML
    private Label f4;
    @FXML
    private Label h4;
    @FXML
    private Label pi4;
    @FXML
    private Button p4;
    @FXML
    private Button c4;
    @FXML
    private Label f5;
    @FXML
    private Label h5;
    @FXML
    private Label pi5;
    @FXML
    private Button p5;
    @FXML
    private Button c5;
    @FXML
    private Label f6;
    @FXML
    private Label h6;
    @FXML
    private Label pi6;
    @FXML
    private Button p6;
    @FXML
    private Button c6;
    @FXML
    private Label f7;
    @FXML
    private Label h7;
    @FXML
    private Label pi7;
    @FXML
    private Button p7;
    @FXML
    private Button c7;
    @FXML
    private Label f8;
    @FXML
    private Label h8;
    @FXML
    private Label pi8;
    @FXML
    private Button p8;
    @FXML
    private Button c8;
    @FXML
    private Label f9;
    @FXML
    private Label h9;
    @FXML
    private Label pi9;
    @FXML
    private Button p9;
    @FXML
    private Button c9;
    @FXML
    private Label f10;
    @FXML
    private Label h10;
    @FXML
    private Label pi10;
    @FXML
    private Button p10;
    @FXML
    private Button c10;
    @FXML
    private Button b2_10;
    @FXML
    private Button b2_11;
    @FXML
    private Button b2_12;
    @FXML
    private Button b2_13;
    @FXML
    private Button b2_14;
    @FXML
    private Button b2_15;
    @FXML
    private Button b2_16;
    @FXML
    private Button b2_17;
    @FXML
    private Button b2_18;
    @FXML
    private Button b2_19;
    @FXML
    private Button b2_20;
    @FXML
    private Button b2_21;
    @FXML
    private Button b3_10;
    @FXML
    private Button b3_11;
    @FXML
    private Button b3_12;
    @FXML
    private Button b3_13;
    @FXML
    private Button b3_14;
    @FXML
    private Button b3_15;
    @FXML
    private Button b3_16;
    @FXML
    private Button b3_17;
    @FXML
    private Button b3_18;
    @FXML
    private Button b3_19;
    @FXML
    private Button b3_20;
    @FXML
    private Button b3_21;
    @FXML
    private Button b4_10;
    @FXML
    private Button b4_11;
    @FXML
    private Button b4_12;
    @FXML
    private Button b4_13;
    @FXML
    private Button b4_14;
    @FXML
    private Button b4_15;
    @FXML
    private Button b4_16;
    @FXML
    private Button b4_17;
    @FXML
    private Button b4_18;
    @FXML
    private Button b4_19;
    @FXML
    private Button b4_20;
    @FXML
    private Button b4_21;
    @FXML
    private Button b5_10;
    @FXML
    private Button b5_11;
    @FXML
    private Button b5_12;
    @FXML
    private Button b5_13;
    @FXML
    private Button b5_14;
    @FXML
    private Button b5_15;
    @FXML
    private Button b5_16;
    @FXML
    private Button b5_17;
    @FXML
    private Button b5_18;
    @FXML
    private Button b5_19;
    @FXML
    private Button b5_20;
    @FXML
    private Button b5_21;
    @FXML
    private Button b6_10;
    @FXML
    private Button b6_11;
    @FXML
    private Button b6_12;
    @FXML
    private Button b6_13;
    @FXML
    private Button b6_14;
    @FXML
    private Button b6_15;
    @FXML
    private Button b6_16;
    @FXML
    private Button b6_17;
    @FXML
    private Button b6_18;
    @FXML
    private Button b6_19;
    @FXML
    private Button b6_20;
    @FXML
    private Button b6_21;
    @FXML
    private Button b1_09;
    @FXML
    private Button b2_09;
    @FXML
    private Button b3_09;
    @FXML
    private Button b4_09;
    @FXML
    private Button b5_09;
    @FXML
    private Button b6_09;
    @FXML
    private Tab buttonMisreservas;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //INICIALIZACIÓN DE LA PRIMERA PANTALLA
        
        dpi = Screen.getPrimary().getDpi();
        scrollPane.setFitToWidth(true);     //el scroll pane aparece cuando se pasa de altura no de ancho
        tabPane.getSelectionModel().select(2);//pone la tab pistas como seleccion inicial
        
        updateFlowPane();
        setvBoxPistaInitialSize();    
        
        
        scrollPane.widthProperty().addListener((observable,oldVal,newVal)-> {//on withpropertie changed
            updateFlowPane();
        });
        
  
        try {
            Club.getInstance().setInitialData(); //Resetea la base de datos al iniciar
            //Club.getInstance().addSimpleData();
            
            Club.getInstance().registerMember("pepe", "pipo", "9999999", "pepe", "pipo", "999", 000, null); //registra un miembro de prueba
        
            
            
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setvBoxPistaInitialSize(){
        vBoxPista1.setMaxWidth(dpi * 2.5);
        vBoxPista1.setMinWidth(dpi * 2.5);        
        vBoxPista2.setMaxWidth(dpi * 2.5);
        vBoxPista2.setMinWidth(dpi * 2.5);
        vBoxPista3.setMaxWidth(dpi * 2.5);
        vBoxPista3.setMinWidth(dpi * 2.5);
        vBoxPista4.setMaxWidth(dpi * 2.5);
        vBoxPista4.setMinWidth(dpi * 2.5);
        vBoxPista5.setMaxWidth(dpi * 2.5);
        vBoxPista5.setMinWidth(dpi * 2.5);
        vBoxPista6.setMaxWidth(dpi * 2.5);
        vBoxPista6.setMinWidth(dpi * 2.5);

    }
    
    private void updateFlowPane(){
        double width = scrollPane.getWidth()-15;
        double pistaWidth = vBoxPista1.getWidth();
  
        int columns = (int) Math.floor(width/pistaWidth);
        if(columns < 6){
            flowPane.setMaxWidth(columns*pistaWidth+1);
            flowPane.setMinWidth(columns*pistaWidth+1);
        }else{
            flowPane.setMaxWidth(6*pistaWidth+1);
            flowPane.setMinWidth(6*pistaWidth+1);
        }
    }
    
    /**Hace la reserva. Devuelve true si se puede hacer la reserva false si no.*/
    private boolean safeRegisterBooking(LocalDateTime bookingDate, LocalDate madeForDay, LocalTime fromHour, boolean paid, Court court, Member member){
        try {
            List<Booking> reservasDelDia = Club.getInstance().getCourtBookings(court.getName(), madeForDay);
            Boolean reservaDuplicada = false;
            for(Booking reserva: reservasDelDia){
                if(reserva.getFromTime().equals(fromHour)){
                    reservaDuplicada = true;
                }
            }
            
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
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;//fallo
    }

    @FXML
    private void onCalendarButton(ActionEvent event) throws ClubDAOException, IOException {
        Object testBoton = event.getSource();
        if(testBoton instanceof Button){
            Button boton = (Button) testBoton;
            Court court = Club.getInstance().getCourt("Pista " + boton.getId().substring(1, 2));//Pista sobre la que se a hecho click
            //System.out.println(boton.getId().substring(3, 5));
            int horaSeleccionada = Integer.valueOf(boton.getId().substring(3, 5));
            Member usuario = Club.getInstance().getMemberByCredentials("pepe", "pipo");
            
            
            if(Club.getInstance().hasCreditCard(usuario.getNickName())){//si tiene tarjeta de credito
                safeRegisterBooking(LocalDateTime.now(), LocalDate.of(2023, 1, 1), LocalTime.of(horaSeleccionada, 0), true, court, usuario);
                
            }
            else{//si no tiene 
                
            }
        }
        
    }
    
    @FXML
    private void onButtonMisreservas(Event event) {
        updateMisReservas();
    }
    
    public void updateMisReservas() { //llamar a este método cada vez que se entra a Mis Reservas
        try {
            List<Booking> reservasUsuario = Club.getInstance().getUserBookings("pepe");
            int reservasTotales = reservasUsuario.size();
            System.out.println(reservasTotales);
            nombreUsuarioReservas.setText(login);

            r1.setVisible(false); //Opacidad de las reservas a 0
            
            r2.setVisible(false); //Poner la opacidad a 100 si el
            r3.setVisible(false); //usuario ha hecho al menos ese
            r4.setVisible(false); //número de reservas y modificar
            r5.setVisible(false); //el texto según las últimas reservas
            r6.setVisible(false);
            r7.setVisible(false);
            r8.setVisible(false);
            r9.setVisible(false);
            r10.setVisible(false);  
            
            //Collections.reverse(reservasUsuario); 
            String fechaString;
            String horaString;
            LocalDate fecha;
            LocalTime hora;
            
            switch(reservasTotales){
                case 1:
                    r1.setVisible(true);
                    break;
                case 2:
                    r2.setVisible(true);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                        
            }
         
                
           /*
            if (reservasTotales >= 1) {
                r1.setVisible(true);
                fecha = reservasUsuario.get(0).getMadeForDay();
                fechaString = fecha.format(formatoFecha);
                f1.setText(fechaString);
                hora = reservasUsuario.get(0).getFromTime();
                horaString = hora.format(formatoHora);
                h1.setText(horaString);
                pi1.setText(reservasUsuario.get(0).getCourt().getName());                
            }
            
            if (reservasTotales >= 2) {
                r2.setVisible(true);
                fecha = reservasUsuario.get(1).getMadeForDay();
                fechaString = fecha.format(formatoFecha);
                f2.setText(fechaString);
                hora = reservasUsuario.get(1).getFromTime();
                horaString = hora.format(formatoHora);
                h2.setText(horaString);
                pi2.setText(reservasUsuario.get(1).getCourt().getName());
            }
            if (reservasTotales >= 3) {
                r3.setVisible(true);
                fecha = reservasUsuario.get(2).getMadeForDay();
                fechaString = fecha.format(formatoFecha);
                f3.setText(fechaString);
                hora = reservasUsuario.get(2).getFromTime();
                horaString = hora.format(formatoHora);
                h3.setText(horaString);
                pi3.setText(reservasUsuario.get(2).getCourt().getName());  
            }
            if (reservasTotales >= 4) {
                r4.setVisible(true);
                fecha = reservasUsuario.get(3).getMadeForDay();
                fechaString = fecha.format(formatoFecha);
                f4.setText(fechaString);
                hora = reservasUsuario.get(3).getFromTime();
                horaString = hora.format(formatoHora);
                h4.setText(horaString);
                pi4.setText(reservasUsuario.get(3).getCourt().getName());  
            }
            if (reservasTotales >= 5) {
                r5.setVisible(true);
                fecha = reservasUsuario.get(4).getMadeForDay();
                fechaString = fecha.format(formatoFecha);
                f5.setText(fechaString);
                hora = reservasUsuario.get(4).getFromTime();
                horaString = hora.format(formatoHora);
                h5.setText(horaString);
                pi5.setText(reservasUsuario.get(4).getCourt().getName());  
            }
            if (reservasTotales >= 6) { 
                r6.setVisible(true);
                fecha = reservasUsuario.get(5).getMadeForDay();
                fechaString = fecha.format(formatoFecha);
                f6.setText(fechaString);
                hora = reservasUsuario.get(5).getFromTime();
                horaString = hora.format(formatoHora);
                h6.setText(horaString);
                pi6.setText(reservasUsuario.get(5).getCourt().getName());
            }
            if (reservasTotales >= 7) {
                r7.setVisible(true);
                fecha = reservasUsuario.get(6).getMadeForDay();
                fechaString = fecha.format(formatoFecha);
                f7.setText(fechaString);
                hora = reservasUsuario.get(6).getFromTime();
                horaString = hora.format(formatoHora);
                h7.setText(horaString);
                pi7.setText(reservasUsuario.get(6).getCourt().getName());  
            }
            if (reservasTotales >= 8) {
                r8.setVisible(true);
                fecha = reservasUsuario.get(7).getMadeForDay();
                fechaString = fecha.format(formatoFecha);
                f8.setText(fechaString);
                hora = reservasUsuario.get(7).getFromTime();
                horaString = hora.format(formatoHora);
                h8.setText(horaString);
                pi8.setText(reservasUsuario.get(7).getCourt().getName());  
            }
            if (reservasTotales >= 9) {
                r9.setVisible(true);
                fecha = reservasUsuario.get(8).getMadeForDay();
                fechaString = fecha.format(formatoFecha);
                f9.setText(fechaString);
                hora = reservasUsuario.get(8).getFromTime();
                horaString = hora.format(formatoHora);
                h9.setText(horaString);
                pi9.setText(reservasUsuario.get(8).getCourt().getName());  
            }
            if (reservasTotales >= 10) {
                r10.setVisible(true);
                fecha = reservasUsuario.get(9).getMadeForDay();
                fechaString = fecha.format(formatoFecha);
                f10.setText(fechaString);
                hora = reservasUsuario.get(9).getFromTime();
                horaString = hora.format(formatoHora);
                h10.setText(horaString);
                pi10.setText(reservasUsuario.get(9).getCourt().getName());  
            }          
            */
        } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    
    
}
