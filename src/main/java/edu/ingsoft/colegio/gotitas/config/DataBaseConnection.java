package main.java.edu.ingsoft.colegio.gotitas.config;

import java.sql.Connection;
import java.sql.DriverManager;

// Clase con patron de diseño singleton.

public class DataBaseConnection {
    //atributos
    private static Connection connection;
    /* el constructor tiene que ser privado, esto para 
    evitar que se creen instancias de la clase
    */
    
    private DataBaseConnection() {
         }
        
        //metodos
     public static Connection getConnectionDataBase() throws Exception {
            if(connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(Credentials.URL_DB, Credentials.USER_DB, Credentials.PASS_DB);
            }
            return connection;
        }
}
