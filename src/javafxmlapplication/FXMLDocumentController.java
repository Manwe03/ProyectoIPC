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
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafxmlapplication.misReservas.FXMLReservasController;
import javafxmlapplication.pistaCalendario.FXMLpistaCController;
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
    
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/mm/yyyy");
    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("hh:mm:ss");
    
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab buttonMisreservas;
    @FXML
    private Label nombreUsuarioReservas;
    @FXML
    private ScrollPane misReservasScrollPane;
    @FXML
    private VBox misReservasContainer;
    @FXML
    private Tab buttonPistas;
    @FXML
    private DatePicker dpBookingDay;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private FlowPane flowPane;
    
    private int numReservas = 0;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //INICIALIZACIÓN DE LA PRIMERA PANTALLA || Estes orden es muy importante
        dpBookingDay.setValue(LocalDate.now());
        
        UtilData.getInstance().setDpi(Screen.getPrimary().getDpi());
        
        scrollPane.setFitToWidth(true);     //el scroll pane aparece cuando se pasa de altura no de ancho
        tabPane.getSelectionModel().select(2);//pone la tab pistas como seleccion inicial
                
        UtilData.getInstance().setSelectedDate(dpBookingDay.getValue());
        
        updateFlowPane();

        scrollPane.widthProperty().addListener((observable,oldVal,newVal)-> {//on withpropertie changed
            updateFlowPane();
            updateMisReservasVbox();
        });
        
        dpBookingDay.valueProperty().addListener((observable,oldVal,newVal)-> {//on valueProperty changed
            UtilData.getInstance().setSelectedDate(newVal);//actualiza el dia selecionado en UtilData
        });
  
        try {
            Club.getInstance().setInitialData(); //Resetea la base de datos al iniciar
            //Club.getInstance().addSimpleData();
            
            Club.getInstance().registerMember("Fernando", "Alonso", "000000", "pepe", "pipo", "0000000", 000, null); //registra un miembro de prueba
            UtilData.getInstance().setLogin("pepe");
            UtilData.getInstance().setPassword("pipo");
            
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void updateMisReservasVbox(){
        misReservasContainer.setMaxWidth(misReservasScrollPane.getWidth()-31);
        misReservasContainer.setMinWidth(misReservasScrollPane.getWidth()-31);
        misReservasContainer.setMaxHeight(100 * Math.min(numReservas, 10)/*Multiplo de la cantidad de reservas*/);
        misReservasContainer.setMinHeight(100 * Math.min(numReservas, 10)/*Multiplo de la cantidad de reservas*/);
    }
    
    private void updateFlowPane(){
        double width = scrollPane.getWidth()-15;
        double pistaWidth = UtilData.getInstance().getDpi() * 2.5;
  
        int columns = (int) Math.floor(width/pistaWidth);
        if(columns < 6){
            flowPane.setMaxWidth(columns*pistaWidth+1);
            flowPane.setMinWidth(columns*pistaWidth+1);
        }else{
            flowPane.setMaxWidth(6*pistaWidth+1);
            flowPane.setMinWidth(6*pistaWidth+1);
        }
    }
    
    @FXML
    private void onButtonMisreservas(Event event) throws IOException, ClubDAOException {
        misReservasContainer.getChildren().clear();
        List<Booking> misReservas = Club.getInstance().getUserBookings(UtilData.getInstance().getLogin());
        
        this.numReservas = misReservas.size();
        updateMisReservasVbox();
        
        for(int i = 0; i < numReservas && i < 10; i++){
            
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("misReservas/FXMLReservas.fxml")); //Gracias a chat gpt porque el path estaba mal
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
    
    private void updatePistasView() throws IOException, ClubDAOException{
        UtilData.getInstance().setSelectedDate(dpBookingDay.getValue());//Actualiza el dia seleccionado en el datePicker
        //Elimina y actualiza los pistaBox
        flowPane.getChildren().clear();
        
        for(int i = 1; i <= 6; i++){
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("pistaCalendario/FXMLpistaC.fxml"));
            Parent pistaBox = loader.load();
            FXMLpistaCController controler = loader.getController();
            controler.setData(Club.getInstance().getCourt("Pista " + i),dpBookingDay.getValue());
            flowPane.getChildren().add(pistaBox);  
        }
        
    }

    
}
