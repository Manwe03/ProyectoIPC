/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.perfil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import javafxmlapplication.FXMLDocumentController;
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
 * @author manub
 */
public class FXMLPerfilController implements Initializable {

    @FXML
    private BorderPane miPerfilBorderPane;
    @FXML
    private Button loginButton;
    @FXML
    private HBox perfilBottomPane;
    @FXML
    private Button guardarCambiosButton;
    @FXML
    private Button cancelarCambiosButton;
    @FXML
    private Button perfilEditarButton;
    @FXML
    private Label nombreLabel;
    @FXML
    private TextField nombreField;
    @FXML
    private Label apellidosLabel;
    @FXML
    private TextField apellidosField;
    @FXML
    private Label telefonoLabel;
    @FXML
    private TextField telefonoField;
    @FXML
    private Label nickLabel;
    @FXML
    private TextField nickField;
    @FXML
    private Label contraseñaLabel;
    @FXML
    private PasswordField contraseñaField;
    @FXML
    private Label repetirContraseñaLabel;
    @FXML
    private PasswordField repetirContraseñaField;
    @FXML
    private Label nombreErrorLabel;
    @FXML
    private Label apellidosErrorLabel;
    @FXML
    private Label telefonoErrorLabel;
    @FXML
    private Label nickErrorLabel;
    @FXML
    private Label contraseñaErrorLabel;
    @FXML
    private Label repetirContraseñaErrorLabel;
    @FXML
    private Label numTarjetaLabel;
    @FXML
    private Label svcLabel;
    @FXML
    private Label numTarjetaErrorLabel;
    @FXML
    private TextField numTarjetaField;
    @FXML
    private Label svcErrorLabel;
    @FXML
    private TextField svcField;
    @FXML
    private Button subirImagen;
    @FXML
    private ImageView imagenPerfilRegistro;
    @FXML
    private Label miPerfilOpcionalLabel;

    private boolean nombreLabelUp = false;
    private boolean apellidosLabelUp = false;
    private boolean telefonoLabelUp = false;
    private boolean nickLabelUp = false;
    private boolean contraseñaLabelUp = false;
    private boolean repetirContraseñaLabelUp = false;
    private boolean numTarjetaLabelUp = false;
    private boolean svcLabelUp = false;
    
    private Label[] formularioErrordArray = new Label[6];
    
    private TextField[] formularioFieldArray = new TextField[6];
    
    private Club club;
    private UtilData utilData;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {club = Club.getInstance();} catch (ClubDAOException | IOException ex) {}
        this.utilData = UtilData.getInstance();
        utilData.setPerfilController(this);
        
        //LISTENERS para las animaciones de las labels en MiPerfil
        nombreField.focusedProperty().addListener((observable,oldVal,newVal)-> { nombreLabelUp = moveLabelIntoBorder(nombreLabel,nombreLabelUp);});
        apellidosField.focusedProperty().addListener((observable,oldVal,newVal)-> { apellidosLabelUp = moveLabelIntoBorder(apellidosLabel,apellidosLabelUp);});
        telefonoField.focusedProperty().addListener((observable,oldVal,newVal)-> { telefonoLabelUp = moveLabelIntoBorder(telefonoLabel,telefonoLabelUp);});
        nickField.focusedProperty().addListener((observable,oldVal,newVal)-> { nickLabelUp = moveLabelIntoBorder(nickLabel,nickLabelUp);});
        contraseñaField.focusedProperty().addListener((observable,oldVal,newVal)-> {
            contraseñaLabelUp = moveLabelIntoBorder(contraseñaLabel,contraseñaLabelUp);
            contraseñaField.setAccessibleRole(AccessibleRole.TEXT_FIELD);
        });
        repetirContraseñaField.focusedProperty().addListener((observable,oldVal,newVal)-> { 
            repetirContraseñaLabelUp = moveLabelIntoBorder(repetirContraseñaLabel,repetirContraseñaLabelUp);
        });
        numTarjetaField.focusedProperty().addListener((observable,oldVal,newVal)-> { 
            numTarjetaLabelUp = moveLabelIntoBorder(numTarjetaLabel,numTarjetaLabelUp); 
            comprobarTarjetaField();
        });
        svcField.focusedProperty().addListener((observable,oldVal,newVal)-> { svcLabelUp = moveLabelIntoBorder(svcLabel,svcLabelUp); });
        
        //LISTENERS para las comprobaciones de los textfields de mi perfil
        nombreField.textProperty().addListener((observable,oldVal,newVal)-> { 
            nombreErrorLabel.setVisible(false);
            clearErrorLabels(0);
        });
        apellidosField.textProperty().addListener((observable,oldVal,newVal)-> {
            apellidosErrorLabel.setVisible(false);
            comprobarFieldsVacios(1);
            clearErrorLabels(1);
        });
        telefonoField.textProperty().addListener((observable,oldVal,newVal)-> { 
            telefonoErrorLabel.setVisible(false);
            if(newVal != ""){
                String lastInput = newVal.substring(newVal.length()-1);
                if(!lastInput.equals("+") && !lastInput.equals(" ")){
                    try{
                        Integer.parseInt(lastInput);
                    }catch(NumberFormatException e){
                        telefonoField.setText(oldVal);
                    }
                }
            }
            comprobarFieldsVacios(2);
            clearErrorLabels(2);
        });
        nickField.textProperty().addListener((observable,oldVal,newVal)-> {
            nickErrorLabel.setVisible(false);
            if(!"".equals(newVal)){
                String lastInput = newVal.substring(newVal.length()-1);
                if(lastInput.equals(" ")){
                    nickField.setText(oldVal);   
                }
            }
            if(club.existsLogin(newVal)){
                nickErrorLabel.setText("Este usuario ya existe");
                nickErrorLabel.setVisible(true);
            }
            comprobarFieldsVacios(3);
            clearErrorLabels(3);
        });
        contraseñaField.textProperty().addListener((observable,oldVal,newVal)-> {
            contraseñaErrorLabel.setVisible(false);
            comprobarFieldsVacios(4);
            clearErrorLabels(4);
        });
        repetirContraseñaField.textProperty().addListener((observable,oldVal,newVal)-> {
            repetirContraseñaErrorLabel.setVisible(false);
            if(!contraseñaField.getText().equals(repetirContraseñaField.getText())){
                repetirContraseñaErrorLabel.setText("Contraseña diferente a la anterior");
                repetirContraseñaErrorLabel.setVisible(true);
            }
            if("".equals(repetirContraseñaField.getText())){
                repetirContraseñaErrorLabel.setText("Campo vacío");
                repetirContraseñaErrorLabel.setVisible(false);
            }
            comprobarFieldsVacios(5);
            clearErrorLabels(5);
        });
        numTarjetaField.textProperty().addListener((observable,oldVal,newVal)-> { 
            //Solo permite introducir numeros
            numTarjetaErrorLabel.setVisible(false);
                
            if(!"".equals(newVal)){
                try{
                    Integer.valueOf(newVal.substring(newVal.length()-1));
                }catch(NumberFormatException e){
                    numTarjetaField.setText(oldVal);
                }
            }  
        });
        svcField.textProperty().addListener((observable,oldVal,newVal)-> {

            if(newVal.length() < 3 && newVal.length() != 0){
                svcErrorLabel.setVisible(true);
            }else{
                svcErrorLabel.setVisible(false);
            }
            //Solo permite introducir numeros
            if(!"".equals(newVal)){
                try{
                    Integer.parseInt(newVal.substring(newVal.length()-1));
                }catch(NumberFormatException e){
                    svcField.setText(oldVal);
                }
            }
        });
        
        //Limitadores de tamaño
        addTextLimiter(svcField,3);
        addTextLimiter(numTarjetaField,16);
        
        formularioFieldArray[0] = nombreField;
        formularioFieldArray[1] = apellidosField;
        formularioFieldArray[2] = telefonoField;
        formularioFieldArray[3] = nickField;
        formularioFieldArray[4] = contraseñaField;
        formularioFieldArray[5] = repetirContraseñaField;
        
        formularioErrordArray[0] = nombreErrorLabel;
        formularioErrordArray[1] = apellidosErrorLabel;
        formularioErrordArray[2] = telefonoErrorLabel;
        formularioErrordArray[3] = nickErrorLabel;
        formularioErrordArray[4] = contraseñaErrorLabel;
        formularioErrordArray[5] = repetirContraseñaErrorLabel;
        
        startPerfil();
    }    

    public void startPerfil(){//ajusta el tamaño con respecto al dpi
        double dpi = utilData.getDpi();
        perfilBottomPane.setMaxHeight(dpi);
        perfilBottomPane.setMinHeight(dpi);
        
        if(!utilData.isLogged()){//si no esta logueado
            if(utilData.getRegistrarse()){//si quiere registrarse es decir a pasado por la pantalla de login
                hideErrorLabels();
                perfilEditMode(true);
                perfilEditarButton.setDisable(true);
                guardarCambiosButton.setVisible(true);
                cancelarCambiosButton.setVisible(true);
                cancelarCambiosButton.setDisable(true);
                loginButton.setVisible(true);
                nickField.setDisable(false);
            }else{
                utilData.showScene("Login");
            }
        }else{//si esta logueado
            updateMiPerfilLabelsInfo();
            hideErrorLabels();
            perfilEditMode(false);
            perfilEditarButton.setDisable(false);
            guardarCambiosButton.setVisible(false);
            cancelarCambiosButton.setDisable(false);
            cancelarCambiosButton.setVisible(false);
            loginButton.setVisible(false);
            nickField.setDisable(true);
        }
    }
        
    @FXML
    private void onButtonLogin(ActionEvent event) {
        utilData.setRegistrarse(false);
        utilData.showScene("Login");
    }

    @FXML
    private void onGuardarCambiosButton(ActionEvent event) {
        if(utilData.isLogged()){    //si esta loguedo, quiere editar
            comprobarFieldsVacios(6);
            editarDatos();
            contraseñaField.setText("");
            repetirContraseñaField.setText("");
        }else{                      //si NO esta loguedo, quiere registrarse
            comprobarFieldsVacios(6);//comprueba si te has dejado algo, en el caso de que todo este vacio esto es en seguro
            registrarse();
            contraseñaField.setText("");
            repetirContraseñaField.setText("");
        }  
    }

    @FXML
    private void onCancelarCambiosButton(ActionEvent event) {
        guardarCambiosButton.setVisible(false);
        cancelarCambiosButton.setVisible(false);
        perfilEditarButton.setDisable(false);
        hideErrorLabels();
        
        perfilEditMode(false);
        contraseñaField.setText("");
        repetirContraseñaField.setText("");
    }

    @FXML
    private void onPerfilEditarButton(ActionEvent event) {
        hideErrorLabels();
        perfilEditMode(true);
        
        guardarCambiosButton.setVisible(true);
        cancelarCambiosButton.setVisible(true);
        perfilEditarButton.setDisable(true);
        cancelarCambiosButton.requestFocus();
    }

    @FXML
    private void onSubirImagen(ActionEvent event) {
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
    
    private void comprobarTarjetaField(){
        if(numTarjetaField.getText().length() == 0 || numTarjetaField.getText().length() == 16){
            numTarjetaErrorLabel.setVisible(false);
        }else{
            numTarjetaErrorLabel.setVisible(true);
        }
    }            
    /**Comprueba se hay algun field vacio por encima de la posicion i, si lo hay muestra el error*/
    private void comprobarFieldsVacios(int i){
        for(int j = i-1;j >= 0;j--){
            if(formularioFieldArray[j].getText().isBlank()){
                formularioErrordArray[j].setText("Campo Obligatorio");
                formularioErrordArray[j].setVisible(true);
            }
        }
    }
    
    /**Elimina los mensajes de error por campo vacio por debajo de i*/

    private void clearErrorLabels(int i){
        for(int j = i+1;j < 6;j++){
            if(formularioErrordArray[j].getText().contains("Campo Obligatorio")){
                formularioErrordArray[j].setText("Campo vacío");
                formularioErrordArray[j].setVisible(false);
            }
        }
    }
    
    private void registrarse(){
        if(!nombreErrorLabel.isVisible() && !apellidosErrorLabel.isVisible() && !telefonoErrorLabel.isVisible() 
            && !nickErrorLabel.isVisible() && !contraseñaErrorLabel.isVisible() && !repetirContraseñaErrorLabel.isVisible() 
            && !numTarjetaErrorLabel.isVisible() && !svcErrorLabel.isVisible()){    //comprueba si hay errores
            int svc = 0;
            if(!"".equals(svcField.getText())){
                svc = Integer.parseInt(svcField.getText());
            }
            try {
                //Crear nuevo usuario
                club.registerMember(nombreField.getText(), apellidosField.getText(), telefonoField.getText(),nickField.getText(), contraseñaField.getText(), numTarjetaField.getText(), svc , imagenPerfilRegistro.getImage());
                guardarCambiosButton.setVisible(false);
                cancelarCambiosButton.setVisible(false);
                perfilEditarButton.setDisable(false);
                utilData.setRegistrarse(false);
                perfilEditMode(false);
                
                utilData.setPassword(contraseñaField.getText());
                utilData.setLogin(nickField.getText());
                
                utilData.showScene("Login");
            } catch (ClubDAOException ex) {
                //Cosas chungas no se puede crear
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            //no se puede registrar
        }
    }
    private void editarDatos(){
        if(!nombreErrorLabel.isVisible() && !apellidosErrorLabel.isVisible() && !telefonoErrorLabel.isVisible() 
            && !nickErrorLabel.isVisible() && !contraseñaErrorLabel.isVisible() && !repetirContraseñaErrorLabel.isVisible() 
            && !numTarjetaErrorLabel.isVisible() && !svcErrorLabel.isVisible()){    //comprueba si hay errores
            //obtiene el miembro
            Member miembro = club.getMemberByCredentials( utilData.getLogin(), utilData.getPassword());
            //Modifica los datos
            miembro.setName(nombreField.getText());
            miembro.setSurname(apellidosField.getText());
            miembro.setTelephone(telefonoField.getText());
            miembro.setPassword(contraseñaField.getText());
            if(numTarjetaField.getText().isBlank() || svcField.getText().isBlank()){
                
            }else{
                miembro.setCreditCard(numTarjetaField.getText());
                if(!"".equals(svcField.getText())){
                    miembro.setSvc(Integer.parseInt(svcField.getText()));
                }
            }
            
            
            utilData.setPassword(contraseñaField.getText());
            
            guardarCambiosButton.setVisible(false);
            cancelarCambiosButton.setVisible(false);
            perfilEditarButton.setDisable(false);
            
            perfilEditMode(false);
        }
    }
    
    /**Obtiene y pone la informacion en las labels de mi perfil*/
    private void updateMiPerfilLabelsInfo() {
        if(utilData.isLogged()){//Si estas logueado
            Member member = club.getMemberByCredentials(utilData.getLogin(), utilData.getPassword());
            if(member == null){
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                utilData.getLogin();
                utilData.getPassword();
            }
            nombreField.setText(member.getName());
            apellidosField.setText(member.getSurname());
            telefonoField.setText(member.getTelephone());
            nickField.setText(member.getNickName());
            if(member.checkHasCreditInfo()){
                String creditcard = member.getCreditCard();
                numTarjetaField.setPromptText("#######" + creditcard.substring(creditcard.length()-4,creditcard.length()));
                svcField.setPromptText("###");
            }else{
                numTarjetaField.setPromptText("");
                svcField.setPromptText("");
            }
            contraseñaField.setText("");
            repetirContraseñaField.setText("");
            numTarjetaField.setText("");
            svcField.setText("");      
        }
    }
    
    /**cambia entre modo edición y modo vista normal*/
    private void perfilEditMode(boolean edit){
        contraseñaLabel.setVisible(edit);
        repetirContraseñaLabel.setVisible(edit);
        numTarjetaLabel.setVisible(edit);
        svcLabel.setVisible(edit);
        
        nombreField.setDisable(!edit);
        apellidosField.setDisable(!edit);
        telefonoField.setDisable(!edit);
        
        contraseñaField.setVisible(edit);
        
        repetirContraseñaField.setVisible(edit);
        numTarjetaField.setVisible(edit);
        svcField.setVisible(edit); 
        miPerfilOpcionalLabel.setVisible(edit);
        
    }
    /**Oculta las labels de error*/
    private void hideErrorLabels(){
        nombreErrorLabel.setVisible(false);
        apellidosErrorLabel.setVisible(false);
        telefonoErrorLabel.setVisible(false);
        nickErrorLabel.setVisible(false);
        contraseñaErrorLabel.setVisible(false);
        repetirContraseñaErrorLabel.setVisible(false);
        numTarjetaErrorLabel.setVisible(false);
        svcErrorLabel.setVisible(false);
    }

    private boolean moveLabelIntoBorder(Node nodo, boolean globalState){ //https://www.youtube.com/watch?v=UdGiuDDi7Rg no me puedo creer que javafx no permita animaciones/transiciones en css :( vaya M, full serio, increible
        boolean state;
        TranslateTransition translateR = new TranslateTransition();
        translateR.setNode(nodo);
        translateR.setDuration(Duration.millis(200));
        if(globalState){
            translateR.setToX(nombreField.getLayoutY()-23);//Puede que haya que escalarlo con el dpi
            state = false;
        }
        else{
            translateR.setToX(nombreField.getLayoutY()-15);
            state = true;
        }
        translateR.play();
        return state;
    }
    
    public static void addTextLimiter(final TextField tf, final int maxLength) {// Codigo epico gracias https://stackoverflow.com/questions/15159988/javafx-2-2-textfield-maxlength
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }
    
}
