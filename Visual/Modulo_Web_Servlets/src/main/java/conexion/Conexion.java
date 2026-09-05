package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // URL CORREGIDA para Bogotá y evitar bloqueos
    private static final String URL = "jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=America/Bogota&allowPublicKeyRetrieval=true";
    private static final String USER = "root"; 
    private static final String PASSWORD = "Luzemi07"; 

    public static Connection conectar() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("¡Conexión establecida con éxito!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Error: No se encontró el Driver de MySQL.");
            System.out.println("Revisa que tengas el archivo mysql-connector-j-9.7.0.jar en la carpeta WEB-INF/lib");
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
            System.out.println("Revisa: que MySQL esté encendido, que la base de datos 'mydb' exista, y que la contraseña sea correcta");
        }
        return conn;
    }
}