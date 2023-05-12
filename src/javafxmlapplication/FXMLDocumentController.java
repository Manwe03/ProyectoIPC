/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import model.Club;
import model.ClubDAOException;


/**
 * FXML Controller class
 *
 * @author manub
 */
public class FXMLDocumentController implements Initializable {

    double dpi;
    
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
    private Button b1_9;
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
    private Button b2_9;
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
    private Button b3_9;
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
    private Button b4_9;
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
    private Button b5_9;
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
    private Button b6_9;
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
        
        //DEFINICIÓN DEL CLUB GREENBALL
        
        try {
            Club greenBall = Club.getInstance();    
            
            
            System.out.println(Club.getInstance().getCourts().get(0).getName());
            
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

    @FXML
    private void Pb1_9(ActionEvent event) {
        event.getSource();
    }
}
