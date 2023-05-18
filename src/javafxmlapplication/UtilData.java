/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.Club;
import model.ClubDAOException;

/**
 * No se usa de momento
 * @author manub
 */
public class UtilData {
    private static UtilData INSTANCE = null;
    
    private double dpi;
    
    private String login;
    
    private String password;
    
    private LocalDate selectedDate;
    
    private FXMLDocumentController mainController;
    
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
        
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        //loader.load()
        scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")));
        escenas.put("Main", scene);
        
        scene = new Scene(FXMLLoader.load(getClass().getResource("login/login.fxml")));
        escenas.put("Login", scene);
        
        scene = new Scene(FXMLLoader.load(getClass().getResource("register/register.fxml")));
        escenas.put("Register", scene);
    }
    
    public void showScene(String nombre){
        Scene escena = escenas.get(nombre);
        if(nombre == "Main"){//Una movida, si se carga main mostrar la pantalla de pistas no la otra, se ve que no hay manera de ponmer una tab default (no se)
            mainController.triggerOnButtonPistas();
        }
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
    
    public boolean isLogged(){
        try {
            Club.getInstance().getMemberByCredentials(this.login, this.password);
            return true;
        } catch (NullPointerException elBicho){
            return false;
        } catch (ClubDAOException | IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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
    public FXMLDocumentController getMainController(){return this.mainController;}
    //SET
    public void setDpi(double dpi){this.dpi = dpi;}
    public void setLogin(String login){this.login = login;}
    public void setPassword(String password){this.password = password;}
    public void setSelectedDate(LocalDate selectedDate){this.selectedDate = selectedDate;}
    public void setMainController(FXMLDocumentController mainController){this.mainController = mainController;}
}
