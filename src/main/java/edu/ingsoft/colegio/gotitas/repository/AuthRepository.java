
package main.java.edu.ingsoft.colegio.gotitas.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import main.java.edu.ingsoft.colegio.gotitas.config.DataBaseConnection;
import java.sql.SQLException;
import main.java.edu.ingsoft.colegio.gotitas.dto.request.LoginRequest;
import main.java.edu.ingsoft.colegio.gotitas.dto.response.LoginResponse;
public class AuthRepository {
    //atributo
    private boolean sqlStatus = false;
  
    /*
    los métodos: son acciones especificas 
    tareas individuales, algunos métodos
    realizan una tarea, pero no retornan nada
    son "void", otros métodos, realizan tareas, y retornan
    Divide y venceras: un método debe ser encargado
    de realizar unicamente una tarea especifica, el 
    nombre de ese método debe ser modular, directo.
    
    */
    //constructor
    public LoginResponse findUserByEmail(LoginRequest loginRequest) throws Exception {
      String sql = "select d.nombre, d.apellido, u.contrasena_hash from usuarios as u" +
" right join docentes as d" +
" on d.id_docente = u.id_docente" +
" where email = ? ";
                try(PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
                    pstm.setString(1, loginRequest.getEmail());
                    ResultSet rs = pstm.executeQuery();
                    if(rs.next()) {
                       return new LoginResponse(rs.getString("nombre"), rs.getString("apellido"), rs.getString("contrasena_hash"));
                    }
                }catch(SQLException e) {
                    System.out.println("error al encontrar el  EMAIL" + e.getMessage());
                }
                return null;
    }
}
