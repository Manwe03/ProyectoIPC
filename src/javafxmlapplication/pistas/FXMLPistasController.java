/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.pistas;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafxmlapplication.FXMLDocumentController;
import javafxmlapplication.UtilData;
import javafxmlapplication.pistaCalendario.FXMLpistaBoxController;
import model.Club;
import model.ClubDAOException;

/**
 * FXML Controller class
 *
 * @author manub
 */
public class FXMLPistasController implements Initializable {

    @FXML
    private Button antButton;
    @FXML
    private DatePicker dpBookingDay;
    @FXML
    private Button posButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private FlowPane flowPane;
    
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/mm/yyyy");
    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("hh:mm:ss");
    
    private Club club;
    private UtilData utilData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {club = Club.getInstance();} catch (ClubDAOException | IOException ex) {}
        this.utilData = UtilData.getInstance();
        utilData.setPistasController(this);
                
        scrollPane.widthProperty().addListener((observable,oldVal,newVal)-> {//on withpropertie changed
            updateFlowPane();
            //updateMisReservasVboxView(); Es importante creo
        });
        
        dpBookingDay.valueProperty().addListener((observable,oldVal,newVal)-> {//on valueProperty changed
            utilData.setSelectedDate(newVal);//actualiza el dia selecionado en UtilData
            updatePistasView();
        });
        
        dpBookingDay.setValue(LocalDate.now());
        
        scrollPane.setFitToWidth(true);     //el scroll pane aparece cuando se pasa de altura no de ancho
        
        utilData.setSelectedDate(dpBookingDay.getValue());
        
        
        
        startPistas();
    }    

    public void startPistas(){
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
        updateFlowPane();
        utilData.setRegistrarse(false);//Siempre que salga de MiPerfil poner registrarse a false
    }
            
    private void updateFlowPane(){
        double dpi = utilData.getDpi();
        
        double width = scrollPane.getWidth()- dpi * 0.15;
        
        double vBoxWidth = ((3.71)*dpi); //Tamaño de la pista
        
        int columns = (int) Math.floor(width/vBoxWidth);
        if(columns < 6){
            flowPane.setMaxWidth(columns*vBoxWidth+2);
            flowPane.setMinWidth(columns*vBoxWidth+2);
        }else{
            flowPane.setMaxWidth(6*vBoxWidth+1);
            flowPane.setMinWidth(6*vBoxWidth+1);
        }
    }
    
    private void updatePistasView(){
        utilData.setSelectedDate(dpBookingDay.getValue());//Actualiza el dia seleccionado en el datePicker
        //Elimina y actualiza los pistaBox
        flowPane.getChildren().clear();
        for(int i = 1; i <= 6; i++){
            try {
                
                FXMLLoader loader = new  FXMLLoader(getClass().getResource("/javafxmlapplication/pistaCalendario/FXMLpistaBox.fxml"));
                Parent pistaBox = loader.load();
                FXMLpistaBoxController controler = loader.getController();
                controler.setData(club.getCourt("Pista " + i),dpBookingDay.getValue());  
                
                flowPane.getChildren().add(pistaBox);
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
    
}
