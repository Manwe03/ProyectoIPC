/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXMLApplication extends Application {
    
    private static HashMap<String,Parent> tabs = new HashMap<>();
    
    
    public static Parent getTab(String clave){
        return tabs.get(clave);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        //======================================================================
        // 1- creación del grafo de escena a partir del fichero FXML
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        //Scene scene = new Scene(root);
        
        final double rem = javafx.scene.text.Font.getDefault().getSize();
        
        Scene scene = new Scene(root, 25 * rem, 33.33 * rem);
        
        //======================================================================
        // 3- asiganación de la escena al Stage que recibe el metodo 
        //     - configuracion del stage
        //     - se muestra el stage de manera no modal mediante el metodo show()
        stage.setScene(scene);
        stage.setHeight(720);
        stage.setWidth(1280);
        stage.setMinHeight(840);
        stage.setMinWidth(349);
        stage.setTitle("GreenBall");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
