import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Módulo de Gestión de Inventario - Evidencia: GA7-220501096-AA5-EV01
 * Aprendiz: Jair Fernando Hernández Sandoval
 */
@WebServlet("/ProductoServlet")
public class ProductoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Conexión unificada a la base de datos local
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "Luzemi07"; 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Captura de datos enviados desde el formulario web
        String nombre = request.getParameter("nombreProducto");
        String cantidadStr = request.getParameter("cantidadProducto");
        
        // Validación para evitar campos vacíos en el sistema
        if (nombre == null || cantidadStr == null || nombre.trim().isEmpty() || cantidadStr.trim().isEmpty()) {
            request.setAttribute("mensaje", "Error: Los campos no pueden estar vacíos.");
            request.getRequestDispatcher("formulario.jsp").forward(request, response);
            return;
        }

        int cantidad = Integer.parseInt(cantidadStr);
        
        // Consulta SQL para insertar el producto en la tabla
        String sql = "INSERT INTO productos (nombre, cantidad) VALUES (?, ?)";
        String mensajeRespuesta = "";

        try {
            // Carga del driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Conexión y preparación segura de la consulta
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                
                ps.setString(1, nombre);
                ps.setInt(2, cantidad);
                
                // Ejecución del insert
                int filas = ps.executeUpdate();
                if (filas > 0) {
                    mensajeRespuesta = "¡Producto '" + nombre + "' registrado con éxito!";
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Captura de errores de la base de datos
            mensajeRespuesta = "Error al conectar o guardar: " + e.getMessage();
        }

        // Envía el mensaje de vuelta y recarga la vista del formulario JSP
        request.setAttribute("mensaje", mensajeRespuesta);
        request.getRequestDispatcher("formulario.jsp").forward(request, response);
    }
}