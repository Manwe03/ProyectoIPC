/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;

/**
 * No se usa de momento
 * @author manub
 */
public class UtilData {
    private static UtilData INSTANCE = null;
    
    private double dpi;
    
    private String login;
    
    private String password;
    
    
    private UtilData(){}//Nada
    
    public static synchronized UtilData getInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new UtilData();
        }
        return INSTANCE;
    }
    
    public void initialize() throws IOException{
        
        /*
        Parent view;
    
        view = FXMLLoader.load(getClass().getResource("FXMLReservas.fxml"));
        tabs.put("Reservas", view);
        
        view = FXMLLoader.load(getClass().getResource("FXMLPistas.fxml"));
        tabs.put("Pistas", view);
        
        view = FXMLLoader.load(getClass().getResource("FXMLPerfil.fxml"));
        tabs.put("Perfil", view);
        */
    }
    
    //GET
    public double getDpi(){return this.dpi;}
    public String getLogin(){return this.login;}
    public String getPassword(){return this.password;}
    //SET
    public void setDpi(double dpi){this.dpi = dpi;}
    public void setLogin(String login){this.login = login;}
    public void setPassword(String password){this.password = password;}
   
}
