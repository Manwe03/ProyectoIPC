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
import javafx.stage.Screen;


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
 
    double dpi;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dpi = Screen.getPrimary().getDpi();
        scrollPane.setFitToWidth(true);     //el scroll pane aparece cuando se pasa de altura no de ancho
        tabPane.getSelectionModel().select(2);//pone la tab pistas como seleccion inicial
        
        updateFlowPane();
        setvBoxPistaInitialSize();
        
        
        scrollPane.widthProperty().addListener((observable,oldVal,newVal)-> {//on withpropertie changed
            updateFlowPane();
        });
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
}
