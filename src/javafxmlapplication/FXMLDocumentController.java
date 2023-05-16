/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.util.Duration;
import javafxmlapplication.misReservas.FXMLReservasController;
import javafxmlapplication.pistaCalendario.FXMLpistaCController;
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
    private Tab buttonMisreservas;
    @FXML
    private Label nombreUsuarioReservas;
    @FXML
    private ScrollPane misReservasScrollPane;
    @FXML
    private VBox misReservasContainer;
    @FXML
    private Tab buttonPistas;
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
    private Tab buttonMiPerfil;
    @FXML
    private HBox perfilTopPane;
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
    @FXML
    private Separator miPerfilSeparator;
    @FXML
    private Label svcErrorLabel;
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        scrollPane.widthProperty().addListener((observable,oldVal,newVal)-> {//on withpropertie changed
            updateFlowPane();
            updateMisReservasVboxView();
        });
        
        dpBookingDay.valueProperty().addListener((observable,oldVal,newVal)-> {//on valueProperty changed
            UtilData.getInstance().setSelectedDate(newVal);//actualiza el dia selecionado en UtilData
            updatePistasView();
        });
        
        
        //LISTENERS para las animaciones de las labels en MiPerfil
        nombreField.focusedProperty().addListener((observable,oldVal,newVal)-> { nombreLabelUp = moveLabelIntoBorder(nombreLabel,nombreLabelUp); });
        apellidosField.focusedProperty().addListener((observable,oldVal,newVal)-> { apellidosLabelUp = moveLabelIntoBorder(apellidosLabel,apellidosLabelUp); });
        telefonoField.focusedProperty().addListener((observable,oldVal,newVal)-> { telefonoLabelUp = moveLabelIntoBorder(telefonoLabel,telefonoLabelUp); });
        nickField.focusedProperty().addListener((observable,oldVal,newVal)-> { nickLabelUp = moveLabelIntoBorder(nickLabel,nickLabelUp); });
        contraseñaField.focusedProperty().addListener((observable,oldVal,newVal)-> { 
            contraseñaLabelUp = moveLabelIntoBorder(contraseñaLabel,contraseñaLabelUp); 
            contraseñaField.setAccessibleRole(AccessibleRole.TEXT_FIELD);
        });
        repetirContraseñaField.focusedProperty().addListener((observable,oldVal,newVal)-> { repetirContraseñaLabelUp = moveLabelIntoBorder(repetirContraseñaLabel,repetirContraseñaLabelUp); });
        numTarjetaField.focusedProperty().addListener((observable,oldVal,newVal)-> { numTarjetaLabelUp = moveLabelIntoBorder(numTarjetaLabel,numTarjetaLabelUp); });
        svcField.focusedProperty().addListener((observable,oldVal,newVal)-> { svcLabelUp = moveLabelIntoBorder(svcLabel,svcLabelUp); });
        
        
                        //INICIALIZACIÓN PARA TESTING//
        ////////////////////////////////////////////////////////////////////////
        try {
            Club.getInstance().setInitialData(); //Resetea la base de datos al iniciar
            //Club.getInstance().addSimpleData();
            
            Club.getInstance().registerMember("Fernando", "Alonso", "99999999", "pepe", "pipo", "0000000000000000", 000, null); //registra un miembro de prueba
            UtilData.getInstance().setLogin("pepe");
            UtilData.getInstance().setPassword("pipo");
            
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ////////////////////////////////////////////////////////////////////////
        
        //INICIALIZACIÓN DE LA PRIMERA PANTALLA || Estes orden es muy importante
        dpBookingDay.setValue(LocalDate.now());
        
        UtilData.getInstance().setDpi(Screen.getPrimary().getDpi());
        
        scrollPane.setFitToWidth(true);     //el scroll pane aparece cuando se pasa de altura no de ancho
        tabPane.getSelectionModel().select(2);//pone la tab pistas como seleccion inicial
                
        UtilData.getInstance().setSelectedDate(dpBookingDay.getValue());
        
        updateFlowPane();
        iniMiPerfilTab();
    }
    
    private void iniMiPerfilTab(){//ajusta el tamaño con respecto al dpi
        double dpi = UtilData.getInstance().getDpi();
        perfilBottomPane.setMaxHeight(dpi);
        perfilBottomPane.setMinHeight(dpi);
        perfilTopPane.setMaxHeight(dpi);
        perfilTopPane.setMinHeight(dpi);
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
        List<Booking> misReservas = Club.getInstance().getUserBookings(UtilData.getInstance().getLogin());
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
        double dpi = UtilData.getInstance().getDpi();
        
        double width = scrollPane.getWidth()- dpi * 0.15;
        
        double vBoxWidth = ((2.2+0.3)*dpi);
        
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
        updateMisReservas();
    }
    
    @FXML
    private void onButtonPistas(Event event) throws IOException, ClubDAOException{
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
        
        updatePistasView(); //se encarga de actualizar la vista
    }
    
    private void updatePistasView(){
        UtilData.getInstance().setSelectedDate(dpBookingDay.getValue());//Actualiza el dia seleccionado en el datePicker
        //Elimina y actualiza los pistaBox
        flowPane.getChildren().clear();
        String styles = "-fx-background-color: #999999;" + "-fx-border-color: #ff0000;";
        for(int i = 1; i <= 6; i++){
            try {
                
                FXMLLoader loader = new  FXMLLoader(getClass().getResource("pistaCalendario/FXMLpistaC.fxml"));
                Parent pistaBox = loader.load();
                FXMLpistaCController controler = loader.getController();
                controler.setData(Club.getInstance().getCourt("Pista " + i),dpBookingDay.getValue());  
               
                pistaBox.setStyle(styles);
                
                flowPane.getChildren().add(pistaBox);
                
            } catch (ClubDAOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void onButtonMiPerfil(Event event) {
        updateMiPerfilLabelsInfo();
        hideErrorLabels();
        perfilEditMode(false);
        
        perfilEditarButton.setDisable(false);
        guardarCambiosButton.setVisible(false);
        cancelarCambiosButton.setVisible(false);        
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
        guardarCambiosButton.setVisible(false);
        cancelarCambiosButton.setVisible(false);
        perfilEditarButton.setDisable(false);
        perfilEditMode(false);
    }

    @FXML
    private void onCancelarCambiosButton(ActionEvent event) {
        guardarCambiosButton.setVisible(false);
        cancelarCambiosButton.setVisible(false);
        perfilEditarButton.setDisable(false);
        perfilEditMode(false);
    }
    
    /**Obtiene y pone la informacion en las labels de mi perfil*/
    private void updateMiPerfilLabelsInfo() {
        try {
            if(UtilData.getInstance().getLogin() == null || UtilData.getInstance().getPassword() == null){//Si no estas logueado
            }else {
                Member member = Club.getInstance().getMemberByCredentials(UtilData.getInstance().getLogin(), UtilData.getInstance().getPassword());
                
                nombreField.setText(member.getName());
                apellidosField.setText(member.getSurname());
                telefonoField.setText(member.getTelephone());
                nickField.setText(member.getNickName());
                if(member.checkHasCreditInfo()){
                    String creditcard = member.getCreditCard();
                    numTarjetaField.setPromptText("------------" + creditcard.substring(creditcard.length()-4,creditcard.length()));
                    svcField.setPromptText("###");
                }
            }

        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
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
        nickField.setDisable(!edit);
        contraseñaField.setDisable(!edit);
        repetirContraseñaField.setVisible(edit);
        numTarjetaField.setVisible(edit);
        svcField.setVisible(edit); 
        miPerfilOpcionalLabel.setVisible(edit);
        miPerfilSeparator.setVisible(edit);
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
    /**Elimina los datos de los campos de edición de mi prefil
    private void clearPerfilFields(){
        nombreField.setText("");
        apellidosField.setText("");
        telefonoField.setText("");
        nickField.setText("");
        contraseñaField.setText("");
        repetirContraseñaField.setText("");
        numTarjetaField.setText("");
        svcField.setText("");
    }*/
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
}
