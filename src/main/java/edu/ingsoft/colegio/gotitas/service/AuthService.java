
package main.java.edu.ingsoft.colegio.gotitas.service;

import main.java.edu.ingsoft.colegio.gotitas.dto.request.LoginRequest;
import main.java.edu.ingsoft.colegio.gotitas.dto.response.LoginResponse;
import main.java.edu.ingsoft.colegio.gotitas.repository.AuthRepository;
import main.java.edu.ingsoft.colegio.gotitas.security.jbcrypt.BCrypt;


public class AuthService {
    //atributo
    private final AuthRepository authRepository;
    private boolean status = false;
    //constructor
    public AuthService(AuthRepository authRepository)
    {
        this.authRepository = authRepository;
    }
    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        if(loginRequest == null) {
            throw new RuntimeException("Credenciales vaicas.");
        }else if(loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            throw new RuntimeException("el correo o la contraseña no pueden estar vacios.");
        }
        LoginResponse response = authRepository.findUserByEmail(loginRequest);
        
        if(response == null) {
            throw new RuntimeException("usuario no encontrado");   
        }
        
        String contrasenaHashed = response.getContrasena_hash();
        
        if(contrasenaHashed == null) {
            throw new RuntimeException("contrasena invalida.");
        }else {
            if(BCrypt.checkpw(loginRequest.getPassword(), contrasenaHashed)) {
            return response;
              }
            }
        return null;
    }
}
