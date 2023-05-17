/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.register;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafxmlapplication.UtilData;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Miquel
 */
public class RegisterController implements Initializable {

    @FXML
    private ImageView imagenPerfilRegistro;
    @FXML
    private Label errorApellido;
    @FXML
    private Label errorUsuario;
    @FXML
    private Label errorContraseña;
    @FXML
    private Label errorTarjeta;
    @FXML
    private Label errorNombre;
    @FXML
    private TextField fieldNombre;
    @FXML
    private TextField fieldApellido;
    @FXML
    private TextField fieldUsuario;
    @FXML
    private TextField fieldContraseña;
    @FXML
    private TextField fieldTarjeta;
    @FXML
    private TextField fieldCVC;
    @FXML
    private TextField fieldTelefono;
    @FXML
    private Label errorTelefono;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void volverAInicioSesion(ActionEvent event) {
        UtilData.getInstance().showScene("Login");
    }
        
    @FXML
    private void volverAMenuPrincipal(ActionEvent event) {
        UtilData.getInstance().showScene("Main");
    }

    @FXML
    private void registrarCuenta(ActionEvent event) throws ClubDAOException, IOException {
        if(fieldNombre.getText().equals("")) { errorNombre.setVisible(true); }
        if(fieldApellido.getText().equals("")) { errorApellido.setVisible(true); }
        if(fieldUsuario.getText().equals("")) { errorUsuario.setVisible(true); }
        if(fieldContraseña.getText().equals("")) { errorContraseña.setVisible(true); }
        if (!soloNumeros(fieldTarjeta.getText()) || !soloNumeros(fieldCVC.getText()) 
            || (fieldTarjeta.getText().length() != 16 || fieldCVC.getText().length() != 3) &&
            ((fieldTarjeta.getText().length() != 0 || fieldCVC.getText().length() != 0))) {            
                errorTarjeta.setVisible(true);
        } else { errorTarjeta.setVisible(false); }  
        if(fieldTelefono.getText().length() != 9) {
            errorTelefono.setText("Número incorrecto");
            errorTelefono.setVisible(true);}        
        //Compruebo que no hay errores
        if(!errorNombre.isVisible() && !errorApellido.isVisible() &&  !errorUsuario.isVisible()
           && !errorContraseña.isVisible() && !errorTarjeta.isVisible() && !errorTelefono.isVisible()) {
            //Crear nuevo usuario
            //Club.getInstance().registerMember(fieldNombre.getText(), fieldApellido.getText(), );
        } 
    }    

    @FXML
    private void subirImagen(ActionEvent event) {
        
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(filter);

        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoImagen = fileChooser.getSelectedFile();
            try {
                BufferedImage imagenBuf = ImageIO.read(archivoImagen);
                Image imagen = SwingFXUtils.toFXImage(imagenBuf, null);
                imagenPerfilRegistro.setImage(imagen);                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }        
    }
    
    public boolean soloNumeros(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @FXML
    private void manejaClick(MouseEvent event) {
        int lNombre = fieldNombre.getText().length();
        int lApellido = fieldApellido.getText().length();
        int lTelefono = fieldTelefono.getText().length();
        int lUsuario = fieldUsuario.getText().length();
        int lContraseña = fieldContraseña.getText().length();
        int lTarjeta = fieldTarjeta.getText().length();
        int lCVC = fieldCVC.getText().length();
        
        if(lApellido > 0) {
            if(lNombre == 0) {errorNombre.setVisible(true);}
        }
        if(lTelefono > 0) {
            if(lNombre == 0) {errorNombre.setVisible(true);}
            if(lApellido == 0) {errorApellido.setVisible(true);}            
        }
        if(lUsuario > 0) {
            if(lNombre == 0) {errorNombre.setVisible(true);}
            if(lApellido == 0) {errorApellido.setVisible(true);}
            if(lTelefono == 0) {errorTelefono.setVisible(true);}            
        }
        if(lContraseña > 0) {
            if(lNombre == 0) {errorNombre.setVisible(true);}
            if(lApellido == 0) {errorApellido.setVisible(true);}
            if(lTelefono == 0) {errorTelefono.setVisible(true);}
            if(lUsuario == 0) {errorUsuario.setVisible(true);}
        }
        if(lTarjeta > 0 || lCVC > 0) {
            if(lNombre == 0) {errorNombre.setVisible(true);}
            if(lApellido == 0) {errorApellido.setVisible(true);}
            if(lTelefono == 0) {errorTelefono.setVisible(true);}
            if(lUsuario == 0) {errorUsuario.setVisible(true);}
            if(lContraseña == 0) {errorContraseña.setVisible(true);}
        }
    }
    
    @FXML
    private void manejaType(KeyEvent event) throws ClubDAOException, IOException {
        if(fieldNombre.getText().length() > 0) {errorNombre.setVisible(false);}
        if(fieldApellido.getText().length() > 0) {errorApellido.setVisible(false);}
        if(fieldTelefono.getText().length() > 0) {errorTelefono.setVisible(false); errorTelefono.setText("Campo obligatorio");}
        if(fieldUsuario.getText().length() > 0) {errorUsuario.setVisible(false);}
        if(fieldContraseña.getText().length() > 0) {errorContraseña.setVisible(false);}
        if(fieldTarjeta.getText().length() > 0) {errorTarjeta.setVisible(false);}
        if(fieldCVC.getText().length() > 0) {errorTarjeta.setVisible(false);}
        if(!soloNumeros(fieldTelefono.getText())) {
            errorTelefono.setText("Número incorrecto");
            errorTelefono.setVisible(true);
        } else { 
            errorTelefono.setVisible(false);
            errorTelefono.setText("Campo obligatorio");
        }
        if (Club.getInstance().existsLogin(fieldUsuario.getText())) {
            errorUsuario.setText("Usuario ya existente");
            errorApellido.setVisible(true);
        } else {
            errorUsuario.setText("Campo obligatorio");
        }   
        
    }
    
               
        
}

   

    

    

    
    

