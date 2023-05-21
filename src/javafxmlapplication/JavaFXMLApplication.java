/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class JavaFXMLApplication extends Application {
    
    
    @Override
    public void start(Stage stage) throws Exception {
        UtilData utilData = UtilData.getInstance();
        
        utilData.initialize(stage);
        
        utilData.setDpi(Screen.getPrimary().getDpi());
        
        utilData.showScene("Main");
        utilData.getMainController().triggerOnPistasButton();
  
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
