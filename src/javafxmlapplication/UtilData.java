/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmlapplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafxmlapplication.login.LoginController;
import javafxmlapplication.miniTarjeta.MiniTarjetaController;
import javafxmlapplication.misReservas.FXMLReservasBoxController;
import javafxmlapplication.perfil.FXMLPerfilController;
import javafxmlapplication.pistaCalendario.FXMLpistaBoxController;
import javafxmlapplication.pistas.FXMLPistasController;
import javafxmlapplication.reservas.FXMLReservasController;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * No se usa de momento
 * @author manub
 */
public class UtilData {
    private static UtilData INSTANCE = null;
    
    private double dpi;
    
    private String login;
    
    private String password;
    
    private LocalDate selectedDate; //guarda el dia que estas viendo en la vista de pistas
    
    private boolean registrarse;    //un boolean de si el usuario quiere o no registrarse, se utiliza para saber si hay que mandarlo a login o a register cuando se pulsa MiPerfil
    
    private FXMLDocumentController mainController;
    
    private FXMLPerfilController perfilController;
    
    private FXMLReservasController reservasController;
    
    private FXMLPistasController pistasController;
    
    private FXMLpistaBoxController pistaBoxController;
    
    private FXMLReservasBoxController reservasBoxController;
    
    private MiniTarjetaController miniTarjetaController;
    
    private LoginController loginController;
    
    public static HashMap<String,Scene> escenas = new HashMap<>();
    
    public int ventanaMode; //0:editardatos // 1:registrarse // 2:hacer reserva //3:nada //4: cancelar reserva // 5: registrar con la tarjeta // 6: confirmar pagar con tajeta // 7: confirmar cancelar reserva
    
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
        
        scene = new Scene(FXMLLoader.load(getClass().getResource("/javafxmlapplication/login/login.fxml")));
        escenas.put("Login", scene);
        
        scene = new Scene(FXMLLoader.load(getClass().getResource("/javafxmlapplication/pistas/FXMLPistas.fxml")));
        escenas.put("Pistas", scene);
        
        scene = new Scene(FXMLLoader.load(getClass().getResource("/javafxmlapplication/reservas/FXMLReservas.fxml")));
        escenas.put("Reservas", scene);
        
        scene = new Scene(FXMLLoader.load(getClass().getResource("/javafxmlapplication/perfil/FXMLPerfil.fxml")));
        escenas.put("Perfil", scene);
        
        //scene = new Scene(FXMLLoader.load(getClass().getResource("/javafxmlapplication/ventana/ventanaModal.fxml")));
        //escenas.put("Ventana", scene);
    }
    
    public void showScene(String nombre){
        Scene escena = escenas.get(nombre);

        stage.setScene(escena);
        
        stage.setMinHeight(dpi*6);
        stage.setMinWidth(dpi*7);
        stage.setTitle("GreenBall");
        stage.getIcons().clear();
        Image icono = null;
        try {
            icono = new Image(new FileInputStream("src/resources/images/tenis.png"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilData.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.getIcons().add(icono);
        stage.show();
        
        //stage.centerOnScreen();
    }
    
    public boolean isLogged(){
        try {
            Member m = Club.getInstance().getMemberByCredentials(this.login, this.password);
            return m!=null;
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
    /**Mueve un boton como si estuviera presionado
     * El estado: true = esta desplazado ; false = no esta desplazado
     * @param node
     * @param estado
     * @return byte
     */
    public static byte translatePressedButton(Node node, byte estado){
        TranslateTransition translate = new TranslateTransition(Duration.millis(100));
        translate.setNode(node);              
        if(estado == 0){
           translate.setToX(3);
           translate.setToY(3);
        }else{
           translate.setToX(0);
           translate.setToY(0);
        }
        translate.play();
        if(estado == 0){return 1;}
        else{return 0;}
    }
    
    //GET
    public double getDpi(){return this.dpi;}
    public String getLogin(){return this.login;}
    public String getPassword(){return this.password;}
    public LocalDate getSelectedDate(){return this.selectedDate;}
    public Stage getStage() {return this.stage;}
    public boolean getRegistrarse(){return this.registrarse;}
    
    public FXMLDocumentController getMainController(){return this.mainController;}
    public FXMLReservasController getReservasController(){return this.reservasController;}
    public FXMLPerfilController getPerfilController(){return this.perfilController;}
    public FXMLPistasController getPistasController(){return this.pistasController;}
    public FXMLpistaBoxController getPistaBoxController(){return this.pistaBoxController;}
    public FXMLReservasBoxController getReservasBoxController(){return this.reservasBoxController;}
    public MiniTarjetaController getMiniTarjetaController(){return this.miniTarjetaController;}
    public LoginController getLoginController(){return loginController; }

    
    //SET
    public void setDpi(double dpi){this.dpi = dpi;}
    public void setLogin(String login){this.login = login;}
    public void setPassword(String password){this.password = password;}
    public void setSelectedDate(LocalDate selectedDate){this.selectedDate = selectedDate;}
    public void setRegistrarse(boolean registrarse){this.registrarse = registrarse;}
    
    public void setMainController(FXMLDocumentController mainController){this.mainController = mainController;}
    public void setReservasController(FXMLReservasController reservasController){this.reservasController = reservasController;}
    public void setPerfilController(FXMLPerfilController perfilController){this.perfilController = perfilController;}
    public void setPistasController(FXMLPistasController pistasController){this.pistasController = pistasController;}
    public void setPistaBoxController(FXMLpistaBoxController pistaBoxController){this.pistaBoxController = pistaBoxController;}
    public void setReservasBoxController(FXMLReservasBoxController reservasBoxController){this.reservasBoxController = reservasBoxController;}
    public void setMiniTarjetaController(MiniTarjetaController miniTarjetaController){this.miniTarjetaController = miniTarjetaController;}
    public void setLoginController(LoginController loginController) {this.loginController = loginController;}
    
}
