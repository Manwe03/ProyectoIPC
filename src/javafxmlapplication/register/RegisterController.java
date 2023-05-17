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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void volverAInicioSesion(ActionEvent event) {
        
    }

    @FXML
    private void registrarCuenta(ActionEvent event) throws ClubDAOException, IOException {
        if(fieldNombre.getText().equals("")) { errorNombre.setVisible(true); }
        if(fieldApellido.getText().equals("")) { errorApellido.setVisible(true); }
        if(fieldUsuario.getText().equals("")) { errorUsuario.setText("Campo obligatorio"); errorUsuario.setVisible(true); }
        if(fieldContraseña.getText().equals("")) { errorContraseña.setVisible(true); }        
        
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

    @FXML
    private void volverAMenuPrincipal(ActionEvent event) {
        
    }

    @FXML
    private void compruebaErrores(KeyEvent event) throws ClubDAOException, IOException {
        if (Club.getInstance().existsLogin(fieldUsuario.getText())) {
            errorUsuario.setText("Usuario ya existente");
        } else { errorUsuario.setVisible(false);}
        if (
                fieldTarjeta.getText().length() == 0 && fieldCVC.getText().length() != 0 ||
                fieldTarjeta.getText().length() != 0 && fieldCVC.getText().length() == 0 ||
                fieldTarjeta.getText().length() != 16 || fieldCVC.getText().length() != 3                
            ) { errorTarjeta.setVisible(true);
        } else { errorTarjeta.setVisible(false); }
    }
        
            
            
        

    /*@FXML
    private void compruebaErrores(MouseEvent event) throws ClubDAOException, IOException {
        if (Club.getInstance().existsLogin(fieldUsuario.getText())) {
            errorUsuario.setText("Usuario ya existente");
        } else { errorUsuario.setVisible(false);}
        if (
                fieldTarjeta.getText().length() == 0 && fieldCVC.getText().length() != 0 ||
                fieldTarjeta.getText().length() != 0 && fieldCVC.getText().length() == 0 ||
                fieldTarjeta.getText().length() != 16 || fieldCVC.getText().length() != 3                
            ) { errorTarjeta.setVisible(true);
        } else { errorTarjeta.setVisible(false); }
        } 
    } */ 

    
    
}
