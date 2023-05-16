/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class JavaFXMLApplication extends Application {
    
    
    @Override
    public void start(Stage stage) throws Exception {
        //======================================================================
        // 1- creación del grafo de escena a partir del fichero FXML
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        String css = this.getClass().getResource("BaseStyleSheet.css").toExternalForm();
        scene.getStylesheets().add(css);
        //System.out.println(Screen.getPrimary().getDpi());
        //======================================================================
        // 3- asiganación de la escena al Stage que recibe el metodo 
        //     - configuracion del stage
        //     - se muestra el stage de manera no modal mediante el metodo show()
        double dpi = Screen.getPrimary().getDpi();
        stage.setScene(scene);
        stage.setHeight(dpi*7);
        stage.setWidth(dpi*10);
        stage.setMinHeight(dpi*6);
        stage.setMinWidth(dpi*7);
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
