/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import model.Club;
import model.Member;
import model.Booking;
import model.ClubDAOException;
import java.util.Collections;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;



/**
 * FXML Controller class
 *
 * @author manub
 */
public class FXMLDocumentController implements Initializable {

<<<<<<< Updated upstream
=======
    double dpi;
    String nickname = "Nick del usuario"; //Cambiar esta variable cuando el usuario inicie sesión
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/mm/yyyy");
    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("hh:mm:ss");
    
>>>>>>> Stashed changes
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private FlowPane flowPane;
    @FXML
<<<<<<< Updated upstream
    private TabPane tabPane;
    @FXML
    private Pane calendarioRightPane;
    @FXML
    private Pane calendarioLeftPane;
    @FXML
    private VBox vBoxPista1;
 
    double dpi;
=======
    private TabPane tabPane;    
    @FXML
    private VBox vBoxPista1; 
>>>>>>> Stashed changes
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
    private Pane calendarioRightPane;
    @FXML
    private Pane calendarioLeftPane;
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
        
<<<<<<< Updated upstream
        //DEFINICIÓN DEL CLUB GREENBALL
               
        try {
            Club greenBall = Club.getInstance();            
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
=======
        inicializarMisReservas(); //Para que aparezcan en su ventana según la base de datos actual       
>>>>>>> Stashed changes
        
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
<<<<<<< Updated upstream
=======
    
    public void inicializarMisReservas() { //llamar a este método cada vez que se entra a Mis Reservas
        
<<<<<<< Updated upstream
=======
        this.numReservas = misReservas.size();
        updateMisReservasVbox();
        
        for(int i = 0; i < numReservas && i < 10; i++){
            
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("misReservas/FXMLReservas.fxml"));
            Parent reservaBox = loader.load();
            //System.out.println("FXML file location: " + loader.getLocation());
            FXMLReservasController controler = loader.getController();
            controler.setData(misReservas.get(i));
            
            misReservasContainer.getChildren().add(reservaBox);  
            
        }
        //TODO
    }
    @FXML
    private void onButtonPistas(Event event) throws IOException, ClubDAOException{
        dpBookingDay.setDayCellFactory((DatePicker picker) -> {//Desabilita los dias en el pasado en el datPicker
            return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) < 0 );
                }
            };
        });
        
        updatePistasView(); //se encarga de actualizar la vista
    }
    
    private void updatePistasView(){
        UtilData.getInstance().setSelectedDate(dpBookingDay.getValue());//Actualiza el dia seleccionado en el datePicker
        //Elimina y actualiza los pistaBox
        flowPane.getChildren().clear();
        
        for(int i = 1; i <= 6; i++){
            try {
                
                FXMLLoader loader = new  FXMLLoader(getClass().getResource("pistaCalendario/FXMLpistaC.fxml"));
                Parent pistaBox = loader.load();
                FXMLpistaCController controler = loader.getController();
                controler.setData(Club.getInstance().getCourt("Pista " + i),dpBookingDay.getValue());  
                flowPane.getChildren().add(pistaBox);
                
            } catch (ClubDAOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        antButton.setDisable(dpBookingDay.getValue().isEqual(LocalDate.now()));//desabilita el boton de ir al dia anterior si estas en el dia actual
    }
    

    @FXML
    private void onAntButton(ActionEvent event) {
        dpBookingDay.setValue(dpBookingDay.getValue().minusDays(1));
    }

    @FXML
    private void onPosButton(ActionEvent event) {
        dpBookingDay.setValue(dpBookingDay.getValue().plusDays(1));
    }

    @FXML
    private void onButtonMiPerfil(Event event) {
        updateMiPerfilLabelsInfo();
        hideErrorLabels();
        perfilEditMode(false);
        
        perfilEditarButton.setDisable(false);
        guardarCambiosButton.setVisible(false);
        cancelarCambiosButton.setVisible(false);        
    }

    @FXML
    private void onPerfilEditarButton(ActionEvent event) {
        hideErrorLabels();
        perfilEditMode(true);
        
        clearPerfilFields();
        
        guardarCambiosButton.setVisible(true);
        cancelarCambiosButton.setVisible(true);
        perfilEditarButton.setDisable(true);
    }

    @FXML
    private void onGuardarCambiosButton(ActionEvent event) {
        guardarCambiosButton.setVisible(false);
        cancelarCambiosButton.setVisible(false);
        perfilEditarButton.setDisable(false);
        perfilEditMode(false);
    }

    @FXML
    private void onCancelarCambiosButton(ActionEvent event) {
        guardarCambiosButton.setVisible(false);
        cancelarCambiosButton.setVisible(false);
        perfilEditarButton.setDisable(false);
        perfilEditMode(false);
    }
    
    /**Obtiene y pone la informacion en las labels de mi perfil*/
    private void updateMiPerfilLabelsInfo() {
>>>>>>> Stashed changes
        try {
            Club club = Club.getInstance();
            List<Booking> reservasUsuario = club.getUserBookings("user1");
            int reservasTotales = reservasUsuario.size();
            System.out.println(reservasTotales);
            nombreUsuarioReservas.setText(nickname);
            
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
            
            Collections.reverse(reservasUsuario); 
            String fechaString;
            String horaString;
            LocalDate fecha;
            LocalTime hora;

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
                      
            } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    }

    
>>>>>>> Stashed changes
}
