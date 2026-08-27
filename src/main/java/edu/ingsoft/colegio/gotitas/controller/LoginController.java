
package main.java.edu.ingsoft.colegio.gotitas.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import main.java.edu.ingsoft.colegio.gotitas.config.DataBaseConnection;
import main.java.edu.ingsoft.colegio.gotitas.service.AuthService;
import main.java.edu.ingsoft.colegio.gotitas.util.SceneManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import main.java.edu.ingsoft.colegio.gotitas.dto.request.LoginRequest;
import main.java.edu.ingsoft.colegio.gotitas.dto.response.LoginResponse;

public class LoginController implements Initializable {
    //Atributos
    private final AuthService authService;
    private final SceneManager sceneManager;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldPass;

    public LoginController(AuthService authService, SceneManager sceneManager){
        this.authService = authService;
        this.sceneManager = sceneManager;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("La vista ha sido creada");
        // TODO
    }    
            
    //metodos
    public void handleLogin() throws Exception {
        if(txtFieldEmail.getText().isEmpty() || txtFieldPass.getText().isEmpty()) {
            sceneManager.showInfoAlert("Campos faltantes", "Revisar información", "uno o más campos vacios", Alert.AlertType.WARNING);
           throw new RuntimeException("No puedes dejar campos vacios.");
            }else{
                try{
                    
            LoginResponse responseService = authService.login(new LoginRequest(txtFieldEmail.getText(), txtFieldPass.getText()));
            LoginResponse userLogged = new LoginResponse(responseService.getNombre(), responseService.getApellido());       
           sceneManager.showInfoAlert("Bienvenido a Gotitas del Saber", "Inicio exitoso", "Bienvenido" + userLogged.getNombre(), Alert.AlertType.INFORMATION);
             }catch(RuntimeException e) {
             
           sceneManager.showInfoAlert("Datos incorrectos", "Revisa tu información", "Intenta de nuevo", Alert.AlertType.INFORMATION);
                   }
                }
            
        }
         
     
}
