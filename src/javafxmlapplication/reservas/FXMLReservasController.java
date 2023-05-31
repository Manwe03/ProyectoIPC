/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.reservas;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafxmlapplication.UtilData;
import javafxmlapplication.misReservas.FXMLReservasBoxController;
import model.Booking;
import model.Club;
import model.ClubDAOException;

/**
 * FXML Controller class
 *
 * @author manub
 */
public class FXMLReservasController implements Initializable {

    @FXML
    private Label nombreUsuarioReservas;
    @FXML
    private ScrollPane misReservasScrollPane;
    @FXML
    private VBox misReservasContainer;
    
    private int numReservas;
    
    private Club club;
    private UtilData utilData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {club = Club.getInstance();} catch (ClubDAOException | IOException ex) {}
        this.utilData = UtilData.getInstance();
        utilData.setReservasController(this);
        startReservas();
        
        misReservasContainer.maxWidthProperty().bind(misReservasScrollPane.widthProperty());
        misReservasContainer.minWidthProperty().bind(misReservasScrollPane.widthProperty());
        
    }
    
    public void startReservas(){
        numReservas = 0;
        utilData.setRegistrarse(false);//Siempre que salga de MiPerfil poner registrarse a false
        misReservasContainer.getChildren().clear();
        if(utilData.isLogged()){
            try {
                updateMisReservas();
                nombreUsuarioReservas.setText("Tus Reservas:");
            } catch (ClubDAOException | IOException ex) {
                Logger.getLogger(FXMLReservasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            nombreUsuarioReservas.setText("Reservas no disponibles, no estás autenticado");
        }
    }

    private void updateMisReservasVboxView(){
        misReservasContainer.setMaxHeight(100 * Math.min(numReservas, 10)/*Multiplo de la cantidad de reservas*/);
        misReservasContainer.setMinHeight(100 * Math.min(numReservas, 10)/*Multiplo de la cantidad de reservas*/);
    } 
    
    public void updateMisReservas() throws ClubDAOException, IOException{
        misReservasContainer.getChildren().clear();
        List<Booking> misReservas = club.getUserBookings(utilData.getLogin());
        this.numReservas = misReservas.size();
        updateMisReservasVboxView();
        for(int i = 0; i < numReservas && i < 10; i++){
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("/javafxmlapplication/misReservas/FXMLReservasBox.fxml")); //Gracias a chat gpt porque el path estaba mal
            Parent reservaBox = loader.load();
            FXMLReservasBoxController controler = loader.getController();
            controler.setData(misReservas.get(i), this);
            misReservasContainer.getChildren().add(reservaBox);     
        }
    }
}
