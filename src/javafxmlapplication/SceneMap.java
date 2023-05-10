/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * No se usa de momento
 * @author manub
 */
public class SceneMap {
    private static SceneMap INSTANCE = null;
    public static HashMap<String,Parent> tabs = new HashMap<>();
    
    private SceneMap(){}//Nada
    
    static synchronized SceneMap getInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new SceneMap();
        }
        return INSTANCE;
    }
    
    public void initialize() throws IOException{
        Parent view;
    
        view = FXMLLoader.load(getClass().getResource("FXMLReservas.fxml"));
        tabs.put("Reservas", view);
        
        view = FXMLLoader.load(getClass().getResource("FXMLPistas.fxml"));
        tabs.put("Pistas", view);
        
        view = FXMLLoader.load(getClass().getResource("FXMLPerfil.fxml"));
        tabs.put("Perfil", view);
    }
}
