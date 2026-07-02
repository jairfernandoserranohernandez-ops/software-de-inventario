import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Principal {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "Luzemi07"; 

    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBA DEL MÓDULO DE INVENTARIO ---");
        ejecutarPrueba();
    }

    public static void ejecutarPrueba() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            
            boolean existeUsuario = false;
            String sqlCheckUser = "SELECT id_usuario FROM usuarios WHERE id_usuario = 1";
            try (PreparedStatement psCheck = con.prepareStatement(sqlCheckUser);
                 ResultSet rs = psCheck.executeQuery()) {
                if (rs.next()) {
                    existeUsuario = true;
                }
            }

            if (!existeUsuario) {
                String sqlInsUser = "INSERT INTO usuarios (id_usuario, username, email, password) VALUES (1, 'jair_admin', 'jair@mail.com', '12345')";
                try (PreparedStatement psUser = con.prepareStatement(sqlInsUser)) {
                    psUser.executeUpdate();
                }
            }

            String sqlProd = "INSERT INTO productos (nombre_producto, precio, id_usuario, usuarios_id_usuario) VALUES (?, ?, 1, 1)";
            try (PreparedStatement psProd = con.prepareStatement(sqlProd)) {
                psProd.setString(1, "Producto Prueba");
                psProd.setDouble(2, 25000.0);
                
                int filas = psProd.executeUpdate();
                if (filas > 0) {
                    System.out.println("¡Producto insertado con éxito!");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en el proceso: " + e.getMessage());
        }
    }
}