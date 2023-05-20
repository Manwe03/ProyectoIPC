/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafxmlapplication.misReservas.FXMLReservasController;
import javafxmlapplication.pistaCalendario.FXMLpistaCController;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Member;



/**
 * FXML Controller class
 *
 * @author manub
 */
public class FXMLDocumentController implements Initializable {
    
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/mm/yyyy");
    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("hh:mm:ss");
    
    @FXML
    private TabPane tabPane;
    @FXML
    private Button buttonMisreservas;
    @FXML
    private Label nombreUsuarioReservas;
    @FXML
    private ScrollPane misReservasScrollPane;
    @FXML
    private VBox misReservasContainer;
    @FXML
    private Button buttonPistas;
    @FXML
    private DatePicker dpBookingDay;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private FlowPane flowPane;
    
    private int numReservas = 0;
    @FXML
    private Button antButton;
    @FXML
    private Button posButton;
    @FXML
    private HBox perfilBottomPane;
    @FXML
    private Button perfilEditarButton;
    @FXML
    private Button guardarCambiosButton;
    @FXML
    private Button cancelarCambiosButton;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidosField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField nickField;
    @FXML
    private PasswordField contraseñaField;
    @FXML
    private PasswordField repetirContraseñaField;
    @FXML
    private TextField numTarjetaField;
    @FXML
    private TextField svcField;
    
    private TextField[] formularioFieldArray = new TextField[6];
    
    @FXML
    private Label repetirContraseñaLabel;
    @FXML
    private Label numTarjetaLabel;
    @FXML
    private Label svcLabel;
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
    private Label numTarjetaErrorLabel;
    
    private Label[] formularioErrordArray = new Label[6]; //{nombreErrorLabel,apellidosErrorLabel,telefonoErrorLabel,nickErrorLabel,contraseñaErrorLabel,repetirContraseñaErrorLabel};
    
    @FXML
    private Label nombreLabel;
    @FXML
    private Label apellidosLabel;
    @FXML
    private Label telefonoLabel;
    @FXML
    private Label nickLabel;
    @FXML
    private Label contraseñaLabel;
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
    
    private Club club;
    private UtilData utilData;

    @FXML
    private Label svcErrorLabel;
    @FXML
    private Button subirImagen;
    @FXML
    private ImageView imagenPerfilRegistro;
    @FXML
    private Button loginButton;
    @FXML
    private BorderPane miPerfilBorderPane;
    @FXML
    private Button miPerfilButton;
    @FXML
    private Tab miPerfilTab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {club = Club.getInstance();} catch (ClubDAOException | IOException ex) {}
        utilData = UtilData.getInstance();
        utilData.setMainController(this);
        
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
                
        scrollPane.widthProperty().addListener((observable,oldVal,newVal)-> {//on withpropertie changed
            updateFlowPane();
            updateMisReservasVboxView();
        });
        
        dpBookingDay.valueProperty().addListener((observable,oldVal,newVal)-> {//on valueProperty changed
            utilData.setSelectedDate(newVal);//actualiza el dia selecionado en UtilData
            updatePistasView();
        });
        
        
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
            if(newVal != ""){
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
        
        
                        //INICIALIZACIÓN PARA TESTING//
        ////////////////////////////////////////////////////////////////////////
        try {
            club.setInitialData(); //Resetea la base de datos al iniciar
            //club.addSimpleData();
            
            club.registerMember("Fernando", "Alonso", "99999999", "1", "1", "0000000000000000", 000, null); //registra un miembro de prueba
            //utilData.setLogin("pepe");
            //utilData.setPassword("pipo");
            
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ////////////////////////////////////////////////////////////////////////
        
        //INICIALIZACIÓN DE LA PRIMERA PANTALLA || Estes orden es muy importante
        dpBookingDay.setValue(LocalDate.now());
        
        utilData.setDpi(Screen.getPrimary().getDpi());
        
        scrollPane.setFitToWidth(true);     //el scroll pane aparece cuando se pasa de altura no de ancho
        tabPane.getSelectionModel().select(2);//pone la tab pistas como seleccion inicial
        
        //Limitadores de tamaño
        addTextLimiter(svcField,3);
        addTextLimiter(numTarjetaField,16);

        triggerOnButtonPistas();
        
        utilData.setSelectedDate(dpBookingDay.getValue());
        
        updateFlowPane();
        iniMiPerfilTab();
    }
    
    private void iniMiPerfilTab(){//ajusta el tamaño con respecto al dpi
        double dpi = utilData.getDpi();
        perfilBottomPane.setMaxHeight(dpi);
        perfilBottomPane.setMinHeight(dpi);
        
        //nombreLabel.toFront();
    }
    
    private void updateMisReservasVboxView(){
        misReservasContainer.setMaxWidth(misReservasScrollPane.getWidth()-31);
        misReservasContainer.setMinWidth(misReservasScrollPane.getWidth()-31);
        misReservasContainer.setMaxHeight(100 * Math.min(numReservas, 10)/*Multiplo de la cantidad de reservas*/);
        misReservasContainer.setMinHeight(100 * Math.min(numReservas, 10)/*Multiplo de la cantidad de reservas*/);
    }
    
    public void updateMisReservas() throws ClubDAOException, IOException{
        misReservasContainer.getChildren().clear();
        List<Booking> misReservas = club.getUserBookings(utilData.getLogin());
        this.numReservas = misReservas.size();
        updateMisReservasVboxView();
        for(int i = 0; i < numReservas && i < 10; i++){
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("misReservas/FXMLReservas.fxml")); //Gracias a chat gpt porque el path estaba mal
            Parent reservaBox = loader.load();
            FXMLReservasController controler = loader.getController();
            controler.setData(misReservas.get(i),this);
            misReservasContainer.getChildren().add(reservaBox);     
        }
    }
    
    private void updateFlowPane(){
        double dpi = utilData.getDpi();
        
        double width = scrollPane.getWidth()- dpi * 0.15;
        
        double vBoxWidth = ((3.71)*dpi); //Tamaño de la pista
        
        int columns = (int) Math.floor(width/vBoxWidth);
        if(columns < 6){
            flowPane.setMaxWidth(columns*vBoxWidth+2);
            flowPane.setMinWidth(columns*vBoxWidth+2);
        }else{
            flowPane.setMaxWidth(6*vBoxWidth+1);
            flowPane.setMinWidth(6*vBoxWidth+1);
        }
    }
    
    @FXML
    private void onButtonMisreservas(Event event) throws IOException, ClubDAOException {
        tabPane.getSelectionModel().select(1);
        utilData.setRegistrarse(false);//Siempre que salga de MiPerfil poner registrarse a false
        if(utilData.isLogged()){
            updateMisReservas();
        }else{
            nombreUsuarioReservas.setText("Reservas no disponibles, no estas autenticado");
        }
    }
    
    /**Metodo para activar onButtonPistas que es como si se pulsara el boton*/
    public void triggerOnButtonPistas(){
        try {
            onButtonPistas(new Event(EventType.ROOT));
        } catch (IOException | ClubDAOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void onButtonPistas(Event event) throws IOException, ClubDAOException{
        tabPane.getSelectionModel().select(2);
        dpBookingDay.setDayCellFactory((DatePicker picker) -> {//Desabilita los dias en el pasado en el datPicker
            return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) < 0 );
                }
            };
        });
        utilData.setRegistrarse(false);//Siempre que salga de MiPerfil poner registrarse a false
        updatePistasView(); //se encarga de actualizar la vista
    }
    
    private void updatePistasView(){
        utilData.setSelectedDate(dpBookingDay.getValue());//Actualiza el dia seleccionado en el datePicker
        //Elimina y actualiza los pistaBox
        flowPane.getChildren().clear();
        for(int i = 1; i <= 6; i++){
            try {
                
                FXMLLoader loader = new  FXMLLoader(getClass().getResource("pistaCalendario/FXMLpistaC.fxml"));
                Parent pistaBox = loader.load();
                FXMLpistaCController controler = loader.getController();
                controler.setData(club.getCourt("Pista " + i),dpBookingDay.getValue());  
                
                flowPane.getChildren().add(pistaBox);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        antButton.setDisable(dpBookingDay.getValue().isEqual(LocalDate.now()));//desabilita el boton de ir al dia anterior si estas en el dia actual
    }
    

    @FXML
    private void onAntButton(ActionEvent event) {
        dpBookingDay.setValue(dpBookingDay.getValue().minusDays(1));
    }

    @FXML
    private void onPosButton(ActionEvent event) {
        dpBookingDay.setValue(dpBookingDay.getValue().plusDays(1));
    }
    
    /**Metodo para activar onButtonPistas que es como si se pulsara el boton*/
    public void triggerOnMiPerfil(){
        onButtonMiPerfil(new Event(EventType.ROOT));
    }
    @FXML
    private void onButtonMiPerfil(Event event) {
        tabPane.getSelectionModel().select(0);
        
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
    private void onPerfilEditarButton(ActionEvent event) {
        hideErrorLabels();
        perfilEditMode(true);
        
        guardarCambiosButton.setVisible(true);
        cancelarCambiosButton.setVisible(true);
        perfilEditarButton.setDisable(true);
        cancelarCambiosButton.requestFocus();
    }

    @FXML
    private void onGuardarCambiosButton(ActionEvent event) {
        if(utilData.isLogged()){    //si esta loguedo, quiere editar
            comprobarFieldsVacios(6);
            editarDatos();
        }else{                      //si NO esta loguedo, quiere registrarse
            comprobarFieldsVacios(6);//comprueba si te has dejado algo, en el caso de que todo este vacio esto es en seguro
            registrarse();
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
                triggerOnMiPerfil();
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
            miembro.setCreditCard(numTarjetaField.getText());
            if(!"".equals(svcField.getText())){
                miembro.setSvc(Integer.parseInt(svcField.getText()));
            }
            guardarCambiosButton.setVisible(false);
            cancelarCambiosButton.setVisible(false);
            perfilEditarButton.setDisable(false);
            perfilEditMode(false);
        }
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
    
    @FXML
    private void onCancelarCambiosButton(ActionEvent event) {
        guardarCambiosButton.setVisible(false);
        cancelarCambiosButton.setVisible(false);
        perfilEditarButton.setDisable(false);
        hideErrorLabels();
        updateMiPerfilLabelsInfo();
        perfilEditMode(false);
    }
    
    /**Obtiene y pone la informacion en las labels de mi perfil*/
    private void updateMiPerfilLabelsInfo() {
        if(utilData.isLogged()){//Si estas logueado
            Member member = club.getMemberByCredentials(utilData.getLogin(), utilData.getPassword());
            nombreField.setText(member.getName());
            apellidosField.setText(member.getSurname());
            telefonoField.setText(member.getTelephone());
            nickField.setText(member.getNickName());
            if(member.checkHasCreditInfo()){
                String creditcard = member.getCreditCard();
                numTarjetaField.setPromptText("------------" + creditcard.substring(creditcard.length()-4,creditcard.length()));
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
        repetirContraseñaLabel.setVisible(edit);
        numTarjetaLabel.setVisible(edit);
        svcLabel.setVisible(edit);
        
        nombreField.setDisable(!edit);
        apellidosField.setDisable(!edit);
        telefonoField.setDisable(!edit);
        //nickField.setDisable(!edit); quitar seguramente
        contraseñaField.setDisable(!edit);
        repetirContraseñaField.setVisible(edit);
        numTarjetaField.setVisible(edit);
        svcField.setVisible(edit); 
        miPerfilOpcionalLabel.setVisible(edit);
        if(edit){
            contraseñaField.setText("");
        }else{
            contraseñaField.setText("00000000000");
        }
        //miPerfilSeparator.setVisible(edit);
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

    @FXML
    private void onButtonLogin(ActionEvent event) {
        utilData.setRegistrarse(false);
        onButtonMiPerfil(new Event(EventType.ROOT));
    }
}    

