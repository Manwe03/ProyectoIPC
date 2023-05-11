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
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrollPane.setFitToWidth(true);     //el scroll pane aparece cuando se pasa de altura no de ancho
        tabPane.getSelectionModel().select(2);//pone la tab pistas como seleccion inicial
        
        scrollPane.widthProperty().addListener((observable,oldVal,newVal)-> {//on withpropertie changed
            double with = scrollPane.getWidth()-15;
            
            int colums = (int) Math.floor(with/317);
            if(colums < 6){
                flowPane.setMaxWidth(colums*317);
                flowPane.setMinWidth(colums*317);
            }else{
                flowPane.setMaxWidth(6*317);
                flowPane.setMinWidth(6*317);
            }
        });
    }
}
