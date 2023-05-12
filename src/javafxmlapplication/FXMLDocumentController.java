/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
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
    private Button b1_09;
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
    private Button b2_09;
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
    private Button b3_09;
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
    private Button b4_09;
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
    private Button b5_09;
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
    private Button b6_09;
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
}
