/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * No se usa de momento
 * @author manub
 * Para cambiar de escena: UtilData.getInstance().showScene("Main");
 */
public class UtilData {
    private static UtilData INSTANCE = null;
    
    private double dpi;
    
    private String login;
    
    private String password;
    
    private LocalDate selectedDate;
    
    public static HashMap<String,Scene> escenas = new HashMap<>();
    
    Stage stage;
    
    private UtilData(){}//Nada
    
    public static synchronized UtilData getInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new UtilData();
        }
        return INSTANCE;
    }
    
    public void initialize(Stage stage) throws IOException{
        this.stage = stage;
        Scene scene;
        scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")));
        escenas.put("Main", scene);
        
        scene = new Scene(FXMLLoader.load(getClass().getResource("login/login.fxml")));
        escenas.put("Login", scene);
        
        scene = new Scene(FXMLLoader.load(getClass().getResource("register/register.fxml")));
        escenas.put("Register", scene);
        
        scene = new Scene(FXMLLoader.load(getClass().getResource("cuentaCreada/cuentaCreada.fxml")));
        escenas.put("CuentaCreada", scene);
    }
    
    public void showScene(String nombre){
        Scene escena = escenas.get(nombre);
        stage.setScene(escena);
        
        String css = this.getClass().getResource("BaseStyleSheet.css").toExternalForm();
        escena.getStylesheets().add(css);
        
        stage.setHeight(dpi*7);
        stage.setWidth(dpi*10);
        stage.setMinHeight(dpi*6);
        stage.setMinWidth(dpi*7);
        stage.setTitle("GreenBall");
        stage.show();
    }
    
    public void setSize_DPI(Region nodo,double width,double height){
        nodo.setMaxWidth(dpi*width);
        nodo.setMinWidth(dpi*width);
        nodo.setMaxHeight(dpi*height);
        nodo.setMinHeight(dpi*height);
    }
    
    //GET
    public double getDpi(){return this.dpi;}
    public String getLogin(){return this.login;}
    public String getPassword(){return this.password;}
    public LocalDate getSelectedDate(){return this.selectedDate;}
    public Stage getStage() {return this.stage;}
    //SET
    public void setDpi(double dpi){this.dpi = dpi;}
    public void setLogin(String login){this.login = login;}
    public void setPassword(String password){this.password = password;}
    public void setSelectedDate(LocalDate selectedDate){this.selectedDate = selectedDate;}
   
}
