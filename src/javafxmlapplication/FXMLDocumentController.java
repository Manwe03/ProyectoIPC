/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 *
 * @author manub
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private FlowPane flowPane;
    @FXML
    private TabPane tabPane;
    @FXML
    private Pane calendarioRightPane;
    @FXML
    private Pane calendarioLeftPane;
    @FXML
    private VBox vBoxPista1;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrollPane.setFitToWidth(true);     //el scroll pane aparece cuando se pasa de altura no de ancho
        tabPane.getSelectionModel().select(2);//pone la tab pistas como seleccion inicial
        
        scrollPane.widthProperty().addListener((observable,oldVal,newVal)-> {//on withpropertie changed
            double width = scrollPane.getWidth()-15;
            double pistaWidth = vBoxPista1.getWidth();
            
            int columns = (int) Math.floor(width/pistaWidth);
            if(columns < 6){
                flowPane.setMaxWidth(columns*pistaWidth);
                flowPane.setMinWidth(columns*pistaWidth);
            }else{
                flowPane.setMaxWidth(6*pistaWidth);
                flowPane.setMinWidth(6*pistaWidth);
            }
        });
    }
}
