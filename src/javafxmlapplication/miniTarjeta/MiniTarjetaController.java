/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.miniTarjeta;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static javafxmlapplication.perfil.FXMLPerfilController.addTextLimiter;
import model.Booking;

/**
 * FXML Controller class
 *
 * @author manub
 */
public class MiniTarjetaController implements Initializable {

    @FXML
    private TextField numTarjetaField;
    @FXML
    private TextField svcField;
    @FXML
    private Label numTarjetaLabel;
    @FXML
    private Label svcLabel;
    @FXML
    private Label numTarjetaErrorLabel;
    @FXML
    private Label svcErrorLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        numTarjetaField.focusedProperty().addListener((observable,oldVal,newVal)-> { });
        svcField.focusedProperty().addListener((observable,oldVal,newVal)-> { });
        
        numTarjetaErrorLabel.setVisible(false);
        svcErrorLabel.setVisible(false);
        
        numTarjetaField.textProperty().addListener((observable,oldVal,newVal)-> { 
            if(newVal.length() < 16 && newVal.length() != 0){
                numTarjetaErrorLabel.setVisible(true);
            }else{
                numTarjetaErrorLabel.setVisible(false);
            }
            if(!"".equals(newVal)){
                try{
                    Integer.valueOf(newVal.substring(newVal.length()-1));
                }catch(NumberFormatException e){
                    numTarjetaField.setText(oldVal);
                }
            }  
        });
        
        svcField.textProperty().addListener((observable,oldVal,newVal)-> {
            if(newVal.length() < 3 && newVal.length() != 0){
                svcErrorLabel.setVisible(true);
            }else{
                svcErrorLabel.setVisible(false);
            }
            //Solo permite introducir numeros
            if(!"".equals(newVal)){
                try{
                    Integer.parseInt(newVal.substring(newVal.length()-1));
                }catch(NumberFormatException e){
                    svcField.setText(oldVal);
                }
            }
        });
        
        addTextLimiter(svcField,3);
        addTextLimiter(numTarjetaField,16);
    }    
    
    public TextField getSvcField(){
        return this.svcField;
    }
    public TextField getNumTarjetaField(){
        return this.numTarjetaField;
    }
    public boolean numTarjetaErrorLabelIsVisible(){
        return numTarjetaErrorLabel.isVisible();
    }
    public boolean svcErrorLabelIsVisible(){
        return svcErrorLabel.isVisible();
    }
}
